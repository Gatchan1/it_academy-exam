package escaperoom.escaperoomelement;

public interface PricedItem {
    double getTotalValue();
    double calculatePrice(double basePrice);
}
