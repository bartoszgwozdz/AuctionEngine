import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AuctionEngine {

    private static Scanner scanner = new Scanner(System.in);
    private final static List<Auction> auctions = new ArrayList<>();
    private final static List<Category> categories = new ArrayList<>();
    private final static Map<String, Category> categoriesByNames = new HashMap<>();


    public static boolean createCategory(String name){
        if(categoryExists(name)){

        }
    }

    public static boolean categoryExists(String wantedName){
        if(categoriesByNames.containsKey(wantedName)){
            return true;
        }
        return false;
    }


    public static boolean addCategory(Category category){
        if(category == null) {
            return false;
        }
        categories.add(category);
        return true;
    }


    public static boolean addAuction (Auction auction){
        if(auction == null) return false;
        if(auctionExists(auction)) return false;
        auctions.add(auction);
        System.out.println("Auction added successfully to system.");
        return true;
    }
    public static Category getCategory(String wantedName){
        if(categoryExists(wantedName)){
            return categoriesByNames.get(wantedName);
        }
        else
            return null;
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



    public static void loadTestCategories(Admin admin) {

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
