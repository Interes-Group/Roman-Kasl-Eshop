package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEmpty;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEmptyRepository;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

@Service
public class CartEmptyService implements ICartEmptyService {

    @Autowired
    CartEmptyRepository repository;

    @Override
    public CartEmpty create() {
        return this.repository.save(new CartEmpty());
    }

    @Override
    public CartEmpty getById(long id) throws NotFoundException {
        CartEmpty cartEmpty = this.repository.findCartEntryById(id);
        if (cartEmpty == null)
            throw new NotFoundException();
        return cartEmpty;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public CartEmpty save(CartEmpty cartEmpty) {
        return this.repository.save(cartEmpty);
    }
}
