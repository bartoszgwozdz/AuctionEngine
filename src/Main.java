import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(LocalDateTime.now());

        LoginEngine<User> userLoginEngine = new LoginEngine<>();
        LoginEngine<Admin> adminLoginEngine = new LoginEngine<>();

        UserCreationPanel userCreationPanel = new UserCreationPanel(userLoginEngine);
        AdminCreationPanel adminCreationPanel = new AdminCreationPanel(adminLoginEngine);

        LoginPanel userLoginPanel = new LoginPanel(userLoginEngine);
        LoginPanel adminLoginPanel = new LoginPanel(adminLoginEngine);

        loadTestAccounts(userCreationPanel);
        loadTestAccounts(adminCreationPanel);
        userLoginEngine.showAccounts();
        adminLoginEngine.showAccounts();

//        Admin admin = adminLoginPanel.login();
//        System.out.println(admin);

        AuctionEngine.loadTestCategories();
        CategoriesBrowser.viewCategories(CategoriesBrowser.byName());

    }

    public static void loadTestAccounts(AccountCreationPanel creationPanel) {
        creationPanel.createAccount("qwe@o2.pl", "Zaq12wsx");
        creationPanel.createAccount("qwe@o2.pl", "Zaq12wsx");
        creationPanel.createAccount("asd@o2.pl", "Zaq12wsx");
        creationPanel.createAccount("zxc@o2.pl", "Zaq12wsx");
        creationPanel.createAccount("wer@o2.pl", "Zaq12wsx");

    }
}
