package escaperoom.components;

import escaperoom.exceptions.DuplicateClueException;
import escaperoom.exceptions.NotFoundClueException;
import escaperoom.exceptions.NotFoundDecorationException;

import java.util.ArrayList;
import java.util.Objects;

public class ThemedRoom extends Component{
    private enum DifficultyLevel {
        EASY,
        MEDIUM,
        HARD,
        UNKNOWN
    }

    private static int counter = 0;
    private DifficultyLevel difficulty;
    private float totalPrice;
    private ArrayList<Clue> clues;
    private ArrayList<Decoration> decorations;

    public ThemedRoom(String name, int difficulty) {
        super(name);
        ThemedRoom.counter++;
        this.id = counter;
        this.difficulty = assignDifficulty(difficulty);
        this.clues = new ArrayList<Clue>();
        this.decorations = new ArrayList<Decoration>();
    }

    private DifficultyLevel assignDifficulty(int difficulty) {
        DifficultyLevel level = switch (difficulty) {
            case 1 -> DifficultyLevel.EASY;
            case 2 -> DifficultyLevel.MEDIUM;
            case 3 -> DifficultyLevel.HARD;
            default -> DifficultyLevel.UNKNOWN;
        };
        return level;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<Clue> getClues() {
        return clues;
    }

    public ArrayList<Decoration> getDecorations() {
        return decorations;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = assignDifficulty(difficulty);
    }

    public void addClue(Clue clue) throws DuplicateClueException {
        for (Clue c : clues) {
            if (c.equals(clue)) {
                throw new DuplicateClueException("Ya existe una pista igual en la sala");
            }
        }
        clues.add(clue);
        this.totalPrice += clue.getPrice();
        System.out.println("Pista añadida correctamente");
    }

    public void addDecoration(Decoration decoration) {
        decorations.add(decoration);
        this.totalPrice += decoration.getPrice();
        System.out.println("Decoración añadida correctamente");
    }

    public void removeClue(int id) throws NotFoundClueException {
        Clue clue = null;
        for (Clue c : clues) {
            if (c.id == id) {
                clue = c;
            }
        }
        if (clue == null) {
            throw new NotFoundClueException("No existe la pista solicitada.");
        }
        this.totalPrice -= clue.getPrice();
        clues.remove(clue);
        System.out.println("Pista eliminada correctamente");
    }

    public void removeDecoration(int id) throws NotFoundDecorationException {
        Decoration decoration = null;
        for (Decoration d : decorations) {
            if (d.id == id) {
                decoration = d;
            }
        }
        if (decoration == null) {
            throw new NotFoundDecorationException("No existe la decoración solicitada.");
        }
        this.totalPrice -= decoration.getPrice();
        decorations.remove(decoration);
        System.out.println("Decoración eliminada correctamente");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemedRoom that = (ThemedRoom) o;
        if (!this.name.equals(that.name)) return false;
        return difficulty == that.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, difficulty);
    }

    @Override
    public String toString() {
        return "\nThemedRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
