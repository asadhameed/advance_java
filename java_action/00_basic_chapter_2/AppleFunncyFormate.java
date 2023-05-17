public class AppleFunncyFormate implements AppleFormatter {
    public String accept(Apple apple) {
        String chart = apple.getSize() > 20 ? "heavy" : "light";

        return "A " + chart + " " + apple.getColor() + " apple    " + apple.toString();
    }
}
