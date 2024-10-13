package escaperoom.escaperoomelement;

import escaperoom.enums.Difficulty;
import escaperoom.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Room extends EscapeRoomElement {
    private static int numRoomsCreated;
    private final int id;
    private Difficulty difficulty;
    private double totalPrice;
    private final ArrayList<Clue> clues;
    private final ArrayList<Decoration> decorations;

    public Room(String name, Difficulty difficulty) {
        super(name);
        Room.numRoomsCreated++;
        id = numRoomsCreated;
        this.difficulty = difficulty;
        this.clues = new ArrayList<Clue>();
        this.decorations = new ArrayList<Decoration>();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<Clue> getClues() {
        return clues;
    }

    public ArrayList<Decoration> getDecorations() {
        return decorations;
    }

    private double calculateTotalPrice(Stream<? extends PricedItem> items) {
        return items
                .mapToDouble(PricedItem::getTotalValue)
                .sum();
    }

    public void calculateRoomTotalPrice() {
        totalPrice = calculateTotalPrice(clues.stream()) + calculateTotalPrice(decorations.stream());
    }

    public void addClue(Clue clue) throws DuplicateElementException {
        addItem(clues, clue, "Ya existe una pista igual en la sala");
        calculateRoomTotalPrice();
        System.out.println("Pista añadida correctamente");
    }

    public void addDecoration(Decoration decoration) throws DuplicateElementException {
        addItem(decorations, decoration, "Ya existe una decoración igual en la sala");
        calculateRoomTotalPrice();
        System.out.println("Decoración añadida correctamente");
    }

    private <T extends PricedItem> void addItem(List<T> items, T item, String errorMessage)
            throws DuplicateElementException {
        if (items.stream().anyMatch(item::equals)) {
            throw new DuplicateElementException(errorMessage);
        }
        items.add(item);
    }

    public void removeClue(Clue clue) {
        clues.remove(clue);
    }

    public void removeDecoration(Decoration decoration) {
        if (decoration.getQuantity() > 1) decoration.decreaseQuantity();
        else decorations.remove(decoration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room that = (Room) o;
        if (!this.getName().equals(that.getName())) return false;
        return difficulty == that.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), difficulty);
    }

    @Override
    public String toString() {
        calculateRoomTotalPrice();
        return "\nThemedRoom{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", difficulty=" + difficulty +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
