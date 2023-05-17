import java.util.List;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

public class DishDomain {
        private static List<Dish> menu = Arrays.asList(
                        new Dish("pork", false, 800, Dish.Type.MEAT),
                        new Dish("beef", false, 700, Dish.Type.MEAT),
                        new Dish("chicken", false, 400, Dish.Type.MEAT),
                        new Dish("french fries", true, 530, Dish.Type.OTHER),
                        new Dish("rice", true, 350, Dish.Type.OTHER),
                        new Dish("season fruit", true, 120, Dish.Type.OTHER),
                        new Dish("pizza", true, 550, Dish.Type.OTHER),
                        new Dish("prawns", false, 300, Dish.Type.FISH),
                        new Dish("salmon", false, 450, Dish.Type.FISH));

        public static void start() {
                long count = menu.stream().collect(Collectors.counting());
                System.out.println("-----Count----- " + count);

                Comparator<Dish> comparatorDish = Comparator.comparingInt(Dish::getCalories);

                Optional<Dish> maxDish = menu.stream().collect(Collectors.maxBy(comparatorDish));

                if (maxDish.isPresent()) {
                        System.out.println(maxDish.get().getName());
                }

                IntSummaryStatistics su = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

                System.out.println(su.getMin());
                System.out.println(su.getMax());
                System.out.println(su.getAverage());

                Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
                List<Integer> numbers = stream.reduce(
                                new ArrayList<Integer>(),
                                (List<Integer> l, Integer e) -> {
                                        l.add(e);
                                        return l;
                                },
                                (List<Integer> l1, List<Integer> l2) -> {
                                        l1.addAll(l2);
                                        return l1;
                                });
                System.out.println(numbers);

                System.out.println("-------Collection Framwrorkd Flexiblity--");

                int totalCalories = menu.stream()
                                .collect(Collectors.reducing(1000, Dish::getCalories, (x, y) -> y + 10));

                System.out.println("------ Sum of totlaCalories---- " + totalCalories);
                System.out.println("----- Sum ---- " + menu.stream().mapToInt(Dish::getCalories).sum());

        }

}
