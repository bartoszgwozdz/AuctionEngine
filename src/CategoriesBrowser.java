import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class CategoriesBrowser {
    public static void viewCategories(Function<List<Category>, List<String>> function){
        List<Category> categories  = AuctionEngine.getCategories();
        List<String> categoriesNames = function.apply(categories);
        viewNames(categoriesNames);
    }

    public static Function<List<Category>, List<String>> byName() {
         return categories -> {
             return categories.stream()
                     .map(Category::getName)
                     .sorted()
                     .collect(Collectors.toUnmodifiableList());
         };
    }

    public static Function<List<Category>, List<String>> byNameReversed() {
        return categories -> {
            return categories.stream()
                    .map(Category::getName)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toUnmodifiableList());
        };
    }

    public static Function<List<Category>, List<String>> startsWith(String prefix) {
        return categories -> {
            return categories.stream()
                    .map(Category::getName)
                    .sorted(Comparator.reverseOrder())
                    .filter(s -> s.startsWith(prefix))
                    .collect(Collectors.toUnmodifiableList());
        };
    }

    private static void viewNames(List<String> names){
        names.stream()
                .forEach(System.out::println);
    }

}
