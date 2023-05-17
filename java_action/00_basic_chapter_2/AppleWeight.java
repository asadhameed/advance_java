public class AppleWeight implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getSize() > 20;
    }
}
