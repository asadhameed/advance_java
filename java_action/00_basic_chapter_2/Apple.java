public class Apple {
    private Color color;
    private int size;

    public Apple() {
        this.color = Color.GREEN;
        this.size = 0;
    }

    public Apple(Color col, int size) {
        this.color = col;
        this.size = size;
    }

    public void setColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        return this.color + " \t" + this.size;
    }

}
