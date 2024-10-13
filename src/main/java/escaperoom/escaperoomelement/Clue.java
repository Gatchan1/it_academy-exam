package escaperoom.escaperoomelement;

import java.util.Objects;

public class Clue extends EscapeRoomElement implements PricedItem {
    private static int numCluesCreated;
    private final int id;
    private static final int TAX = 10;
    private final double price;
    private int estimatedTime;
    private String theme;

    public Clue(String name, double basePrice, int estimatedTime, String theme) {
        super(name);
        Clue.numCluesCreated++;
        id = numCluesCreated;
        price = calculatePrice(basePrice);
        this.estimatedTime = estimatedTime;
        this.theme = theme;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public String getTheme() {
        return theme;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice + (basePrice * TAX / 100);
    }

    @Override
    public double getTotalValue() {
        return getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clue that = (Clue) o;
        if (!this.getName().equals(that.getName())) return false;
        if (this.estimatedTime != that.estimatedTime) return false;
        return theme.equals(that.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), estimatedTime, theme);
    }

    @Override
    public String toString() {
        return "Clue{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", price=" + price +
                ", estimatedTime=" + estimatedTime +
                ", theme='" + theme + '\'' +
                '}';
    }
}
