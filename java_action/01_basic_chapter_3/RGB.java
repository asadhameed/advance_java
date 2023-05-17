public class RGB {
    private int red;
    private int green;
    private int blue;

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void print() {
        System.out.println("The color is " + this.red + this.green + this.blue);
        ;
    }
}
