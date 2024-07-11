package escaperoom.components;

import java.util.Objects;

public class Clue extends Component {
    private static int counter = 0;
    private static final int tax = 10;
    private float price;
    private int estimatedTime;
    private String theme;

    public Clue(String name, float basePrice, int estimatedTime, String theme) {
        super(name);
        Clue.counter++;
        this.id = counter;
        this.price = basePrice + (basePrice * tax / 100);
        this.estimatedTime = estimatedTime;
        this.theme = theme;
    }

    public float getPrice() {
        return price;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public String getTheme() {
        return theme;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clue that = (Clue) o;
        if (!this.name.equals(that.name)) return false;
        if (this.estimatedTime != that.estimatedTime) return false;
        return theme.equals(that.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, estimatedTime, theme);
    }

    @Override
    public String toString() {
        return "Clue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", estimatedTime=" + estimatedTime +
                ", theme='" + theme + '\'' +
                '}';
    }
}
