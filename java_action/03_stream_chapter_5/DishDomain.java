import java.util.List;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Comparator;
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

                List<Dish> sortedDishes = menu.stream().sorted(Comparator.comparingInt(Dish::getCalories))
                                .collect(Collectors.toList());

                System.out.println("------------------SortedList------");
                System.out.println(sortedDishes);

                System.out.println("*********************************************************");
                System.out.println("-------Sorted list filter ------");
                System.out.println("Go through all element in the list");
                System.out.println("*********************************************************");

                List<String> dishes = sortedDishes.stream().filter(dish -> {
                        System.out.println(" --Filter---" + dish.getName());
                        return dish.getCalories() < 320;

                }).map(dish -> {
                        System.out.println("--Map--" + dish.getName());
                        return dish.getName();
                }).collect(Collectors.toList());

                System.out.println(dishes);

                System.out.println("*********************************************************");
                System.out.println("-------Sorted list filter with takewhile(function)------");
                System.out.println("it stops once it has found an element that fails to match");
                System.out.println("*********************************************************");

                List<String> dishesTakeWhile = sortedDishes.stream().takeWhile(dish -> {
                        System.out.println(" --Filter---" + dish.getName());
                        return dish.getCalories() < 320;

                }).map(dish -> {
                        System.out.println("--Map--" + dish.getName());
                        return dish.getName();
                }).collect(Collectors.toList());

                System.out.println(dishesTakeWhile);

                System.out.println("*********************************************************");
                System.out.println("---------Sorted List filter with dropwhile(function) ------");
                System.out.println("The dropWhile operation is the completed of takeWhile.");
                System.out.println("It throws away the element set the start where the predicate is false");
                System.out.println("*********************************************************");

                List<String> dishesDropWhile = sortedDishes.stream().dropWhile(dish -> {
                        System.out.println(" --Filter---" + dish.getName());
                        return dish.getCalories() < 320;

                }).map(dish -> {
                        System.out.println("--Map--" + dish.getName());
                        return dish.getName();
                }).collect(Collectors.toList());

                System.out.println(dishesDropWhile);

                System.out.println("*********************************************************");
                System.out.println("How would you use streams to filter the first two meat dishes");
                System.out.println("*********************************************************");
                List<Dish> firstTwoMeat = sortedDishes.stream().filter(s -> s.getType().equals(Dish.Type.MEAT)).limit(2)
                                .collect(Collectors.toList());
                System.out.println(firstTwoMeat);

                System.out.println("*********************************************************");
                System.out
                                .println("Givena list of words, you’d like to return a list of the number of characters for each word");
                System.out.println("*********************************************************");

                List<Integer> wordLenght = sortedDishes.stream().map(dish -> dish.getName().length())
                                .collect(Collectors.toList());
                List<Integer> wordLenght2 = sortedDishes.stream().map(Dish::getName).map(String::length)
                                .collect(Collectors.toList());
                System.out.println(wordLenght);
                System.out.println(wordLenght2);

                System.out.println("*********************************************************");
                System.out.println("Given a list of numbers, how would you return a list of the square of each number");
                System.out.println("For example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25].");
                System.out.println("*********************************************************");

                List<Integer> number = Arrays.asList(1, 2, 3, 4, 5);

                List<Integer> squreNumber = number.stream().map(a -> a *
                                a).collect(Collectors.toList());
                System.out.println(squreNumber);

                System.out.println("*********************************************************");
                System.out.println("Given two lists of numbers, how would you return all pairs of numbers");
                System.out.println(
                                "For example, given a list [1, 2, 3] and a list [3, 4] you should return [(1,3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].");
                System.out.println("*********************************************************");

                List<Integer> numbers1 = Arrays.asList(1, 2, 3);
                List<Integer> numbers2 = Arrays.asList(3, 4);

                List<int[]> pair = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
                                .collect(Collectors.toList());

                for (int[] a : pair) {
                        System.out.println(a[0] + " " + a[1]);
                }

                System.out.println("*********************************************************");
                System.out
                                .println("How would you extend the previous example to return only pairs whose sum isdivisible by 3?");

                System.out.println("*********************************************************");

                List<int[]> pairDivied3 = numbers1.stream()
                                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0)
                                                .map(j -> new int[] { i, j }))
                                .collect(Collectors.toList());

                for (int[] a : pairDivied3) {
                        System.out.println(a[0] + " " + a[1]);
                }
                System.out.println("----");
                List<int[]> pairDivied_3 = numbers1.stream()
                                .flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
                                .filter(p -> (p[0] + p[1]) % 3 == 0)
                                .collect(Collectors.toList());

                for (int[] a : pairDivied_3) {
                        System.out.println(a[0] + " " + a[1]);
                }

                System.out.println("*********************************************************");
                System.out.println("Summing the elements With Reduce and sum");
                System.out.println("*********************************************************");
                Integer sum = menu.stream().mapToInt(Dish::getCalories).reduce(0, (a, b) -> a
                                + b);

                Integer sum1 = menu.stream().mapToInt(Dish::getCalories).reduce(0,
                                Integer::sum);
                Integer sum2 = menu.stream().mapToInt(Dish::getCalories).sum();

                System.out.println(sum);
                System.out.println(sum1);
                System.out.println(sum2);

                System.out.println("*********************************************************");
                System.out.println("NO INITIAL VALUE");
                System.out.println("*********************************************************");

                List<Integer> noValues = new ArrayList<>();
                Optional<Integer> sumNoValues = noValues.stream().reduce((a, b) -> a + b);

                if (sumNoValues.isPresent()) {
                        System.out.println(sumNoValues);
                } else {
                        System.out.println("The list is empyth");
                }

                System.out.println("*********************************************************");
                System.out.println(
                                "How would you count the number of dishes in a stream using the map and reduce methods?");
                System.out.println("*********************************************************");

                Integer numberDish = menu.stream().map(d -> 1).reduce(0, Integer::sum);
                long numberDishCount = menu.stream().count();
                System.out.println(numberDish);
                System.out.println(numberDishCount);

        }

        public static void numericStream() {

                IntStream intStream = menu.stream().mapToInt(Dish::getCalories);

                Stream<Integer> stream = intStream.boxed();

                // stream.forEach(System.out::println);
                /*
                 * if you uncomment the upper line then the
                 * following stream isn't working because the stream is close.
                 */
                OptionalInt max = stream.mapToInt(Integer::intValue).max();

                if (max.isPresent()) {
                        System.out.println("--max--" + max.getAsInt());
                }

                IntStream evenNumbers = IntStream.rangeClosed(0, 100).filter(n -> n % 2 == 0);
                evenNumbers.forEach(System.out::println);

                Stream<int[]> pythagoStream = IntStream.rangeClosed(1, 100).boxed()
                                .flatMap(a -> IntStream.rangeClosed(a, 100).boxed().filter(b -> Math.sqrt(a *
                                                a + b * b) % 1 == 0)
                                                .map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));
                pythagoStream.forEach(a -> System.out.println(Arrays.toString(a)));
                /****
                 * The Upper solution isn’t optimal because you calculate the square root twice.
                 * One
                 * possible way to make your code more compact is to generate all triples of the
                 * form (a*a, b*b, a*a+b*b) and then filter the ones that match your criteria
                 */

                Stream<double[]> pythagoStreamDouble = IntStream.rangeClosed(1, 100).boxed()
                                .flatMap(a -> IntStream.rangeClosed(a, 100).boxed()
                                                .map(b -> new double[] { a, b, Math.sqrt(a * a + b * b) })
                                                .filter(t -> t[2] %
                                                                1 == 0));
                pythagoStreamDouble.forEach(a -> System.out.println(Arrays.toString(a)));

        }

        public static void buildingStream() {
                Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action ");
                stream.map(String::toUpperCase).forEach(System.out::print);
                int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
                IntStream nums = Arrays.stream(numbers);
                nums.forEach(System.out::print);

                try {
                        Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
                        // long uniqueWordsCount = lines.flatMap(line -> Arrays.stream(line.split("
                        // "))).distinct().count();

                        // System.out.println("-----Unique words in data.txt file ---- " +
                        // uniqueWordsCount);

                        List<String> uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct()
                                        .collect(Collectors.toList());

                        uniqueWords.forEach(System.out::println);

                        lines.forEach(System.out::println);
                } catch (Exception e) {
                        // TODO: handle exception
                }

                Stream.iterate(new int[] { 0, 1 }, n -> new int[] { n[1], n[0] + n[1] }).limit(20).map(t -> t[0])
                                .forEach(System.out::println);

                System.out.println("In Java 9, the iterate method was enhanced with support for a predicate.----");
                IntStream.iterate(0, n -> n < 100, n -> n + 4).forEach(System.out::println);

                /*
                 * The following is not working
                 * Unfortunately that isn’t the case. In fact, this code wouldn’t terminate! The
                 * reason is that there’s no way to know in the filter that the numbers continue
                 * to increase, so it keeps on filtering them infinitel
                 */

                // IntStream.iterate(0, n -> n + 4).filter(n -> n <
                // 100).forEach(System.out::println);
        }
}
