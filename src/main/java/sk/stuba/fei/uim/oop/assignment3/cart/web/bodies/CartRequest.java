package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartElement;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {

    private Long productId;

    private Integer amount;

    public CartRequest(CartElement cartElement) {
        this.productId = cartElement.getProduct().getId();
        this.amount = cartElement.getAmount();
    }
}
