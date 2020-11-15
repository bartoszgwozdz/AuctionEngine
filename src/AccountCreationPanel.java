import java.util.Scanner;
import java.util.regex.Pattern;

        /**
         * An abstract class of an accountCreationPanel.
         * It creates new accounts.
         * It will be used as a parent for userCreationPanel and adminCreationPanel account.
         */

public abstract class AccountCreationPanel {
    static Scanner scanner= new Scanner(System.in);
    private final LoginEngine loginEngine;

    public AccountCreationPanel(LoginEngine loginEngine){
        this.loginEngine = loginEngine;
    }

    protected abstract <T extends Account>  T createAccountAndAddToEngine(String mail, byte[] hash);


    public void createAccount() {
        System.out.println("Welcome in account creation panel! \n -------------------");
        String mail, password;
        mail = enterNewMail();
        password = enterNewPassword();
        Account account = createAccount(mail, password);
        System.out.println(account.getClass().getSimpleName() + " account " + mail + " created successfully. Check your email to verify your account.");
    }

    public <T extends Account> T createAccount(String mail, String password){
        byte[] hash = PasswordHasher.passwordHash(password);
        return createAccountAndAddToEngine(mail, hash);
    }

    private String enterNewMail() {
        String mail;
        while (true) {
            mail = enterMail();
            boolean mailValid = mailValidate(mail);
            if (!mailValid) {
                System.out.println("Wrong e-mail format. Please type correct one.\n");
            } else {
                boolean mailExists = loginEngine.mailExistenceCheck(mail);
                if (mailExists) {
                    System.out.println("This e-mail already exists. Cannot create account\n");
                } else {
                    return mail;
                }
            }
        }
    }

    private boolean mailValidate(String mail) {
        String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]{2,}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(mail).matches();
    }

    private String enterNewPassword() {
        String password;
        while (true) {
            System.out.println("Password should be at least 8 characters long. Must contain at least one UpperCase, one digit. ");
            password = enterPassword();
            boolean passwordCorrect = passwordCorrectnessCheck(password);
            if (!passwordCorrect) {
                System.out.println("Password too weak. Plz type stronger password.\n");
            } else {
                return password;
            }
        }
    }

    private boolean passwordCorrectnessCheck(String password) {
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    private String enterMail() {
        System.out.println("Enter e-mail: ");
        return scanner.nextLine();

    }

    private String enterPassword() {
        System.out.println("Enter password: ");
        return scanner.nextLine();
    }
}
