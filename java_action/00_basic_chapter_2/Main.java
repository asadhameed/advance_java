import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String arg[]) {

        Store st = new Store();
        st.saveAppleStore();

        List<Apple> greenApples = st.filterApples(Color.GREEN);
        for (Apple a : greenApples) {
            System.out.println(a.toString());
        }
        System.out.println("------After weight----");
        List<Apple> weightApples = st.filterByWeight(10);
        for (Apple a : weightApples) {
            System.out.println(a.toString());
        }

        System.out.println("------After Predicate Green Apple----");
        greenApples = st.filterApples(new AppleColore());
        for (Apple a : greenApples) {
            System.out.println(a.toString());
        }

        System.out.println("------After Predicate Apple size----");
        weightApples = st.filterApples(new AppleWeight());
        for (Apple a : weightApples) {
            System.out.println(a.toString());
        }

        System.out.println("------Formate");
        st.showApple(new AppleFunncyFormate());

        System.out.println("------After Lamad with Red---");
        greenApples = st.filterApples((Apple aple) -> Color.RED.equals(aple.getColor()));
        for (Apple a : greenApples) {
            System.out.println(a.toString());
        }

        System.out.println("------After Lamad with equalt to 10---");
        greenApples = st.filterApples((Apple ap) -> ap.getSize() == 10);
        for (Apple a : greenApples) {
            System.out.println(a.toString());
        }

        PredicateFilter predicateFilter = new PredicateFilter();

        List<Apple> apples = st.getInventry();
        System.out.println("------After Generic Predicater with red colore---");
        greenApples = predicateFilter.filter(apples, (Apple a) -> Color.RED.equals(a.getColor()));

        for (Apple a : greenApples) {
            System.out.println(a.toString());
        }

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        List<Integer> even = predicateFilter.filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println(even);

        st.showApple();
        System.out.println("------After sort");
        // apples.sort((Apple a, Apple b) -> b.getSize() - a.getSize());
        apples.sort(Comparator.comparingInt(Apple::getSize).reversed());
        st.showApple();

    }
}