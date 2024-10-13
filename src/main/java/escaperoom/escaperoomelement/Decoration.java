package escaperoom.escaperoomelement;

import java.util.Objects;

public class Decoration extends EscapeRoomElement implements PricedItem {
    private static int numDecorationsCreated;
    private final int id;
    private int quantity;
    private static final int TAX = 21;
    private double price;
    private String material;

    public Decoration(String name, int quantity, double basePrice, String material) {
        super(name);
        Decoration.numDecorationsCreated++;
        id = numDecorationsCreated;
        this.quantity = quantity;
        this.price = calculatePrice(basePrice);
        this.material = material;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice + (basePrice * TAX / 100);
    }

    @Override
    public double getTotalValue() {
        return quantity * price;
    }

    public void decreaseQuantity() {
        quantity -= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decoration that = (Decoration) o;
        if (!this.getName().equals(that.getName())) return false;
        return this.material.equals(that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, material);
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", quantity=" + quantity +
                ", totalValue=" + getTotalValue() +
                ", material='" + material + '\'' +
                '}';
    }
}
