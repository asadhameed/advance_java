import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Comparator;

public class TransDomain {

        public static void start() {
                Trader raoul = new Trader("Raoul", "Cambridge");
                Trader mario = new Trader("Mario", "Milan");
                Trader alan = new Trader("Alan", "Cambridge");
                Trader brian = new Trader("Brian", "Cambridge");
                List<Transaction> transactions = Arrays.asList(
                                new Transaction(brian, 2011, 300),
                                new Transaction(raoul, 2012, 1000),
                                new Transaction(raoul, 2011, 400),
                                new Transaction(mario, 2012, 710),
                                new Transaction(mario, 2012, 700),
                                new Transaction(alan, 2012, 950));

                System.out.println(transactions);

                System.out.println("1------");
                System.out.println("Find all transactions in the year 2011 and sort them by value (small to high).");

                List<Transaction> year2011Sorted = transactions.stream().filter(t -> t.getYear() == 2011)
                                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

                System.out.println(year2011Sorted);

                System.out.println("2------");
                System.out.println("What are all the unique cities where the traders work?.");
                List<String> uniqueCities = transactions.stream().map(t -> t.getTrader().getCity()).distinct()
                                .collect(Collectors.toList());

                System.out.println(uniqueCities);

                System.out.println("3------");
                System.out.println("Find all traders from Cambridge and sort them by name.");
                List<Trader> tradersCombridge = transactions.stream().map(Transaction::getTrader)
                                .filter(t -> t.getCity().equals("Cambridge")).distinct()
                                .sorted(Comparator.comparing(Trader::getName))
                                .collect(Collectors.toList());

                System.out.println(tradersCombridge);

                System.out.println("4------");
                System.out.println("Return a string of all traders’ names sorted alphabetically.");
                // List<String> tradersName = transactions.stream().map(Transaction::getTrader)
                // .map(Trader::getName).distinct().sorted(Comparator.comparing(String::toString))
                // .collect(Collectors.toList());
                // List<String> tradersName =
                // transactions.stream().map(Transaction::getTrader).distinct()
                // .sorted(Comparator.comparing(Trader::getName))
                // .map(Trader::getName).collect(Collectors.toList());

                // Book Soluation
                // String tradersName = transactions.stream().map(t -> t.getTrader().getName())
                // .distinct().sorted().reduce("", (n1, n2) -> n1 + "," + n2);

                String tradersName = transactions.stream().map(t -> t.getTrader().getName())
                                .distinct().sorted().collect(Collectors.joining(", "));
                System.out.println(tradersName);

                System.out.println("5------");
                System.out.println("Are any traders based in Milan.");
                Optional<Transaction> basedMilan = transactions.stream()
                                .filter(t -> t.getTrader().getCity().equals("Milan")).findAny();

                if (basedMilan.isPresent()) {
                        System.out.println(" Base of Milan " + basedMilan.get());
                } else {
                        System.out.println(" Base of Milan  is not exist");
                }

                // book soultion

                boolean milanBase = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
                System.out.println(milanBase);

                System.out.println("6------");
                System.out.println("Print the values of all transactions from the traders living in Cambridge.");
                transactions.stream()
                                .filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue)
                                .forEach(System.out::println);

                System.out.println("7------");
                System.out.println("What’s the highest value of all the transactions.");
                int maxValue = transactions.stream().mapToInt(Transaction::getValue).max().orElse(0);
                System.out.println(" Max Value is " + maxValue);

                // book Method

                Optional<Integer> maxOpValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
                if (maxOpValue.isPresent()) {
                        System.out.println(" Max Value is " + maxOpValue.get());
                }

                System.out.println("8------");
                System.out.println("Find the transaction with the smallest value.");
                Optional<Transaction> withSmallest = transactions.stream()
                                .min(Comparator.comparing(Transaction::getValue));

                if (withSmallest.isPresent()) {
                        System.out.println("--- With smalled transaction " + withSmallest.get());
                }
                // .filter(t -> t.getCity().equals("Cambridge")).forEach(System.out::println);

        }

}
