import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AuctionEngine {

    private final static List<Auction> auctions = new ArrayList<>();
    private final static List<Category> categories = new ArrayList<>();
    private final static Map<String, Category> categoriesByNames = new HashMap<>();


    public static List<Category> getCategories (){
        return Collections.unmodifiableList(categories);
    }

    public static Category getCategory(String wantedName){
        return categoriesByNames.getOrDefault(wantedName, null);
    }

    public static boolean createCategory(String name){
        if(categoryExists(name)){
            return false;
        }
        Category newCategory = new Category(name);
        return addCategory(newCategory);
    }

    private static boolean categoryExists(String wantedName){
        return categoriesByNames.containsKey(wantedName);
    }


    private static boolean addCategory(Category category){
        if(category == null) {
            return false;
        }
        categories.add(category);
        categoriesByNames.put(category.getName(), category);
        return true;
    }




    public static boolean addAuction (Auction auction){
        if(auction == null) {
            return false;
        }
        if(auctionExists(auction)) {
            return false;
        }
        auctions.add(auction);
        System.out.println("Auction added successfully to system.");
        return true;
    }



    public static boolean auctionExists(Auction auction){
        return auctions.contains(auction);
    }




    Function<List<Category>, List<String>> categoriesByName = listOfCategories -> {
                return listOfCategories.stream()
                        .map(Category::getName)
                        .collect(Collectors.toList());
            };



    public static void showAuctions(){
        for(Auction auction: auctions){
            System.out.println(auction);
        }
    }

    public static void showCategories(){
        System.out.println("Categories: \n");
        for (Category category : categories){
            System.out.println(category);
        }
    }



    public static void loadTestCategories() {

    }

//    public static void loadTestAuctions(){
//        Account account = AccountsData.login("qwe@o2.pl", "Zaq12wsx");
//        Auction newAuction =  new Auction.Builder()
//                .seller((User)account).title("First auction")
//                .price(1234).durationInDays(7)
//                .description("This is my auction").build();
//        AuctionEngine.addAuction(newAuction);
//    }
}
