import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class Main {

    public static void main(String[] args) {

        Predicate<Dish> highCalo = x -> (x.getCalories() > 300);
        Predicate<Dish> lowCalo = x -> (x.getCalories() <= 300);

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        // List<String> threeDish =
        // menu.stream().filter(highCalo).map(Dish::getName).collect(Collectors.toList());

        List<String> threeDish = menu.stream().filter(dish -> {
            System.out.println(" --Filter---" + dish.getName());
            return dish.getCalories() > 300;
        }).limit(3).map(dish -> {
            System.out.println("--Map--" + dish.getName());
            return dish.getName();
        }).collect(Collectors.toList());
        List<String> lowDish = menu.stream().filter(lowCalo).map(Dish::getName).collect(Collectors.toList());

        // Map<Dish.Type, List<Dish>> dishedByType =
        // menu.stream().collect(Collectors.groupingBy(Dish::getType));

        // for (Map.Entry<Dish.Type, List<Dish>> entry : dishedByType.entrySet()) {
        // System.out.println(entry.getKey());
        // System.out.println("-------------------------");
        // System.out.println(entry.getValue());
        // System.out.println("-------------------------");
        // }

        System.out.println(threeDish);
        System.out.println(lowDish);

        System.out.println();
    }

}