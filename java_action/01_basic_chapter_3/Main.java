import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BiFunction;;

public class Main {
    public static void main(String[] args) {
        // List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // Consumer<Integer> c = (Integer i) -> System.out.println("Value " + i);

        // forEach(list, c);

        // List<String> strings = List.of("lamanda", "in", "action", "cool", "Sweden",
        // "Uppsala");
        // List<Integer> lengths = map(strings, (String s) -> s.length());
        // System.out.println(lengths);

        // List<String> valid = validName(strings, Main::isValidName);

        // Supplier<Apple> c1 = Apple::new;

        // System.out.println(c1.get().toString());

        // Function<Integer, Apple> c2 = Apple::new;
        // Apple a2 = c2.apply(30);

        // System.out.println(a2.toString());

        // BiFunction<Integer, String, Apple> c3 = Apple::new;

        // a2 = c3.apply(50, "Green");
        // System.out.println(a2.toString());

        // try {
        // String oneLine = processFile((BufferedReader br) -> br.readLine());
        // System.out.println(oneLine);

        // String twoLine = processFile((br) -> br.readLine() + br.readLine());

        // System.out.println(twoLine);
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // TriFunction<Integer, Integer, Integer, RGB> colorFactoy = RGB::new;
        // RGB color = colorFactoy.apply(10, 20, 30);
        // color.print();

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g);

        int result = h.apply(1);
        System.out.println(result);
    }

    private static boolean isValidName(String st) {

        return Character.isUpperCase(st.charAt(0));
    }

    public static List<String> validName(List<String> list, Predicate<String> p) {
        System.out.println("-------------------------");
        List<String> result = new ArrayList<>();

        for (String string : list) {

            if (p.test(string)) {
                result.add(string);
            }
        }
        return result;
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> fun) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(fun.apply(t));
        }

        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }
}
