public class Apple {
    private int size;
    private String color;

    public Apple() {
        this.size = 0;
        this.color = "RED";
    }

    public Apple(int size) {
        this.color = "RED";
        this.size = size;
    }

    public Apple(int size, String color) {
        this.size = size;
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return this.size;
    }

    public String getColor() {
        return this.color;
    }

    public String toString() {
        return "Apple weight " + this.size + "g and color " + this.color;
    }
}
