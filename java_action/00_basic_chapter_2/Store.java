import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Apple> apples;

    public Store() {
        apples = new ArrayList<>();
    }

    public void saveAppleStore() {
        Apple apple = new Apple(Color.GREEN, 30);
        Apple apple1 = new Apple(Color.YELLO, 10);
        Apple apple2 = new Apple(Color.YELLO, 20);
        Apple apple3 = new Apple(Color.RED, 30);
        Apple apple4 = new Apple(Color.GREEN, 20);
        Apple apple5 = new Apple(Color.RED, 10);

        apples.add(apple);
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        apples.add(apple5);
    }

    public void showApple() {
        for (Apple a : apples) {
            System.out.println(a.toString());
        }
    }

    public List<Apple> getInventry() {
        return this.apples;
    }

    public List<Apple> filterApples(Color color) {
        List<Apple> filter = new ArrayList<>();
        for (Apple a : apples) {
            if (a.getColor().equals(color)) {
                filter.add(a);
            }

        }

        return filter;
    }

    public List<Apple> filterApples(ApplePredicate applePredicate) {
        List<Apple> filter = new ArrayList<>();

        for (Apple a : this.apples) {
            if (applePredicate.test(a)) {
                filter.add(a);
            }
        }
        return filter;
    }

    public List<Apple> filterByWeight(int size) {
        List<Apple> filter = new ArrayList<>();
        for (Apple a : apples) {
            if (a.getSize() > size) {
                filter.add(a);
            }

        }

        return filter;
    }

    public void showApple(AppleFunncyFormate formate) {
        for (Apple a : apples) {
            String output = formate.accept(a);
            System.out.println(output);
        }
    }

}