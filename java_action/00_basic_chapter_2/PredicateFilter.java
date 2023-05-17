import java.util.ArrayList;
import java.util.List;

public class PredicateFilter {
    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;

    }

}
