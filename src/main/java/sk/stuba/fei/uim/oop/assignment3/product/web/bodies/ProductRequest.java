package sk.stuba.fei.uim.oop.assignment3.product.web.bodies;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;

    private String description;

    private Integer amount;
    private String unit;

    private Double price;

}
