package escaperoom.escaperoomelement;

public abstract class EscapeRoomElement {
    private String name;

    public EscapeRoomElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
