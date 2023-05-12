package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {

    private Long productId;

    private Integer amount;

    public CartRequest(CartEmpty cartEmpty) {
        this.productId = cartEmpty.getProduct().getId();
        this.amount = cartEmpty.getAmount();
    }
}
