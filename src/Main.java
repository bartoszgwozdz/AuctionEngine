import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AccountEngine engine = new AccountEngine();
//        engine.login();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(LocalDateTime.now());
        engine.loadTestData();
        engine.showMails();

//        Account ac =engine.login();
//        System.out.println(ac);
//
//        AuctionEngine.loadTestData((User) ac);
//        AuctionEngine.showAuctions();

        Account admin = engine.login();
        AuctionEngine.loadTestCategories((Admin)admin);

        AuctionEngine.showCategories();






    }
}
