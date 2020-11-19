import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Auction implements Comparable<Auction>{
    private static int counter=0;
    private final int auctionNr;
    private final User seller;
    private Category category;
    private String title;
    private double price;
    private String description;
    private long  durationInDays;
    private LocalDateTime startDateTime;
    private LocalDateTime expirationDateTime;
    private FeeCalculateStrategy feeCalculator;
    private double fee;

    private Auction (Builder builder){
        auctionNr = counter ++;
        seller = builder.seller;
        category = builder.category;
        title = builder.title;
        price = builder.price;
        description = builder.description;
        startDateTime = builder.startDateTime;
        durationInDays = builder.durationInDays;
        expirationDateTime = builder.expirationDateTime;
        fee = builder.fee;

    }

    public static class Builder{
        private User seller;
        private Category category;
        private String title;
        private double price;
        private String description;
        private long  durationInDays;
        private LocalDateTime startDateTime;
        private LocalDateTime expirationDateTime;
        private FeeCalculateStrategy feeCalculator;
        private double fee;

        public Auction build(){
            this.startDateTime = LocalDateTime.now();
            this.expirationDateTime = expirationCalculator(this.startDateTime, this.durationInDays);
            this.feeCalculator = chooseFeeCalculator(this.durationInDays);
            this.fee = feeCalculator.calculateFee(this.price);
            Auction auction = new Auction(this);
            return auction;
        }

        public Builder seller(User seller){
            this.seller = seller;
            if(this.seller == null) throw new IllegalStateException("Builder.seller(). Null User value");
            return this;
        }

        public Builder title (String title){
            this.title = title;
            if(this.title.isBlank()) throw new IllegalStateException("Builder.title(). Empty title");
            return this;
        }

        public Builder price(double price){
            this.price = price;
            if(this.price < 0) throw new IllegalStateException("Builder.price(). Price less than zero");
            return this;
        }

        public Builder durationInDays(long duration){
            this.durationInDays = durationInDays;
            if(this.durationInDays < 1) throw new IllegalStateException("Builder.durationInDays(). Invalid value");
            return this;
        }

        public Builder category (Category  category){
            this.category = category;
            return this;
        }

        public Builder description (String description){
            this.description = description;
            if(this.description.isEmpty()) throw new IllegalStateException("Builder.description(). Empty description");
            return this;
        }
    }

    //  Here is fee strategy chooser
    private static FeeCalculateStrategy chooseFeeCalculator (long durationInDays) {
        if(durationInDays == 1) return new OneDayFee();
        if(durationInDays > 1 && durationInDays <=3) return new ThreeDayFee();
        else return new SevenDayFee();
    }

    private static LocalDateTime expirationCalculator (LocalDateTime dateTime, long durationInDays){
        LocalDateTime expirationDate = dateTime.plusDays(durationInDays);
        return expirationDate;
    }

    public int getAuctionNr() {
        return Integer.valueOf(auctionNr);
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public Category getCategory() {
        return category;
    }

    public static Comparator<Auction> compareByDateNewestOnTop = new Comparator<Auction>() {
        @Override
        public int compare(Auction auction1, Auction auction2) {
            double auction1Price = auction1.getPrice();
            double auction2Price = auction2.getPrice();
            return (int)(auction1Price - auction2Price);
        }
    };

    public static Comparator<Auction> compareByDateOldestOnTop = new Comparator<Auction>() {
        @Override
        public int compare(Auction auction1, Auction auction2) {
            double auction1Price = auction1.getPrice();
            double auction2Price = auction2.getPrice();
            return (int)(auction2Price - auction1Price);
        }
    };


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM);
        return "Id: " + auctionNr + " Title: " + title + "\t\tStarts: " + startDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return auctionNr == auction.auctionNr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionNr);
    }
    @Override
    public int compareTo(Auction o) {
        return this.title.compareTo(o.title);
    }
}
