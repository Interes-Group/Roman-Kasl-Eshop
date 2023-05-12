package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEmpty;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

public interface ICartEmptyService {

    CartEmpty create();

    CartEmpty getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    CartEmpty save(CartEmpty cartEmpty);
}
