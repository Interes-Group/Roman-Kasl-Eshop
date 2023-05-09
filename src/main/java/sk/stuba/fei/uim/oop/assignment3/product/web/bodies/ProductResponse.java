package sk.stuba.fei.uim.oop.assignment3.product.web.bodies;
import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
@Getter
public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Integer amount;
    private final  String unit;
    private final  Double price;

    public ProductResponse(Product a) {
        this.id = a.getId();
        this.name = a.getName();
        this.description = a.getDescription();
        this.amount = a.getAmount();
        this.unit = a.getUnit();
        this.price = a.getPrice();


    }


}
