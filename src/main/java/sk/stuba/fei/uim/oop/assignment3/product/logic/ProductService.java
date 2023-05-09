package sk.stuba.fei.uim.oop.assignment3.product.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import java.util.List;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
@Service

public class ProductService implements IProductService{
    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        return this.repository.save(new Product(request));
    }

    @Override
    public Product getById(long id) throws NotFoundException {
        Product a = this.repository.findProductById(id);
        if (a == null) {
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public Product update(long id, ProductRequest request) throws NotFoundException {
        Product a = this.getById(id);
        if (request.getName() != null) {
            a.setName(request.getName());
        }
        if (request.getDescription() != null) {
            a.setDescription(request.getDescription());
        }
        return this.repository.save(a);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(long id, int increment) throws NotFoundException {
        Product b = this.getById(id);
        b.setAmount(b.getAmount() + increment);
        this.repository.save(b);
        return b.getAmount();
    }
}
