package uj.jwzp.w2.e3.model;

import java.math.BigDecimal;

public class Item {
    private final String name;
    private final BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName();
    }
}
