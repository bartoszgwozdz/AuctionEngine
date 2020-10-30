import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Account {
    public static Scanner  scanner= new Scanner(System.in);

    public User(String login, String mail){
                super(login, mail);
    }


    public boolean createAuction() {
        System.out.println("You're creating new auction.");
        String title = enterTitle();
        double price = enterPrice();
        long durationInDays = enterDuration();
        String description = enterDescription();
        Auction newAuction =  new Auction.Builder().seller(this).title(title).price(price).durationInDays(enterDuration()).build();
        AuctionEngine.addAuction(newAuction);
        System.out.println("Auction created successfully.");
        System.out.println("Auction id: " + newAuction.getAuctionNr()+ " \"" + title + "\" : " + price + " that will least " + durationInDays  + " days.");
        return true;
    }


    private String enterTitle() {
        System.out.println("Please enter auction title: ");
        String title = scanner.nextLine();
        return title;
    }

    private double enterPrice(){
        boolean isPriceCorrect = false;
        double price =0;
        while (!isPriceCorrect) {
            System.out.println("Now enter price in PLN: ");
            try {
                price =scanner.nextInt();
                isPriceCorrect = true;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect price. Plz type again");
            } finally {
                scanner.nextLine();
            }
        }
        return price;
    }

    private long enterDuration() {
        boolean isDurationCorrect = false;
        long durationInDays =0;
        while (!isDurationCorrect) {
            System.out.println("Now enter duration of your announce in days: ");
            try {
                durationInDays = scanner.nextInt();
                isDurationCorrect = true;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect duration. Plz type again");
            } finally {
                scanner.nextLine();
            }
        }
        return durationInDays;
    }

    private String enterDescription() {
        System.out.println("Please enter auction description: ");
        String title = scanner.nextLine();
        return title;
    }

}
