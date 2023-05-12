package sk.stuba.fei.uim.oop.assignment3.cart.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartElementRepository extends JpaRepository<CartElement, Long> {

    CartElement findCartEntryById(Long id);
}
