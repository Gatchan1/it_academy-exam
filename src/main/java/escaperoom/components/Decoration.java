package escaperoom.components;

import java.util.Objects;

public class Decoration extends Component {
    private static int counter = 0;
    private static final int tax = 21;
    private float price;
    private String material;

    public Decoration(String name, float basePrice, String material) {
        super(name);
        Decoration.counter++;
        this.id = counter;
        this.price = basePrice + (basePrice * tax / 100);
        this.material = material;
    }

    public float getPrice() {
        return price;
    }

    public String getMaterial() {
        return material;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", material='" + material + '\'' +
                '}';
    }
}
