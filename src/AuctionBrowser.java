import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AuctionBrowser {
    public static void viewAuctions(Function<List<Auction>, List<Auction>> function) {
        List<Auction> auctions = AuctionEngine.getAuctions();
        List<Auction> auctionsNames = function.apply(auctions);
        viewNames(auctionsNames);
    }

    public static Function<List<Auction>, List<Auction>> titleContains(String wantedTitlePhrase) {
        return auctions -> auctions.stream()
                .sorted()
                .filter(auctionTitleContains(wantedTitlePhrase))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Function<List<Auction>, List<Auction>> priceHigherThan(double lowLimit){
        return auctions -> auctions.stream()
                .filter(auctionPriceHigherThan(lowLimit))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Function<List<Auction>, List<Auction>> priceLowerThan(double highLimit){
        return auctions -> auctions.stream()
                    .filter(auctionPriceLowerThan(highLimit))
                    .collect(Collectors.toUnmodifiableList());
    }

    public static Function<List<Auction>, List<Auction>> byName() {
        return auctions -> auctions.stream()
                    .sorted()
                    .collect(Collectors.toUnmodifiableList());
    }

    public static Function<List<Auction>, List<Auction>> byNameReversed() {
        return auctions ->  auctions.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toUnmodifiableList());
    }

    public static Function<List<Auction>, List<Auction>> byDateNewest(){
        return auctions -> auctions.stream()
                    .sorted(Auction.compareByDateNewestOnTop)
                    .collect(Collectors.toUnmodifiableList());
    }
    public static Function<List<Auction>, List<Auction>> byDateOldest(){
        return auctions -> auctions.stream()
                    .sorted(Auction.compareByDateOldestOnTop)
                    .collect(Collectors.toUnmodifiableList());
    }

    private static Predicate<Auction> auctionTitleContains(String wantedTitlePhrase){
        return auction -> {
            String title = auction.getTitle();
            String [] titleTab = title.split(" ");
            for(String titlePart: titleTab){
                if(titlePart.equalsIgnoreCase(wantedTitlePhrase)){
                    return true;
                }
            }
            return false;
        };
    }

    private static Predicate<Auction> auctionPriceHigherThan(double lowLimit){
        return auction -> {
            double price = auction.getPrice();
            return (price > lowLimit);
        };
    }
    
    private static Predicate<Auction> auctionPriceLowerThan(double highLimit){
        return auction -> {
            double price = auction.getPrice();
            return (price > highLimit);
        };
    }
    
    private static void viewNames(List<Auction> auctions) {
        auctions.stream()
                .map(Auction::getTitle)
                .forEach(System.out::println);
    }

}
    
