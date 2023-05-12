package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartElement;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartElementRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.data.ShoppingCart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.ShoppingCartRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Autowired
    private IProductService productService;

    @Autowired
    CartElementRepository cartEntryRepository;

    @Override
    public ShoppingCart create() {
        return this.repository.save(new ShoppingCart());
    }

    @Override
    public ShoppingCart getById(long id) throws NotFoundException {
        ShoppingCart cart = this.repository.findShoppingCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public ShoppingCart addProduct(long id, CartRequest body) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = this.getUnpaid(id);
        this.productService.removeAmount(body.getProductId(), body.getAmount());

        Optional<CartElement> existingEntry = cart.getShoppingList()
                .stream()
                .filter(entry -> entry.getProduct().getId() == body.getProductId())
                .findFirst();

        if (existingEntry.isPresent()) {
            existingEntry.get().setAmount(existingEntry.get().getAmount() + body.getAmount());
            this.cartEntryRepository.save(existingEntry.get());
        } else {
            CartElement cartEntry = cartEntryRepository.save(new CartElement());
            cartEntry.setAmount(body.getAmount());
            cartEntry.setProduct(productService.getById(body.getProductId()));
            cart.getShoppingList().add(cartEntryRepository.save(cartEntry));
        }

        return this.repository.save(cart);
    }


    @Override
    public double payCart(long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = this.getUnpaid(id);
        double sum = cart.getShoppingList().stream().mapToDouble(item -> item.getAmount() * item.getProduct().getPrice()).sum();
        cart.setPayed(true);
        this.repository.save(cart);
        return sum;
    }

    private ShoppingCart getUnpaid(long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = this.getById(id);
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        return cart;
    }
}
