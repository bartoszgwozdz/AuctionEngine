import java.util.Scanner;

public class LoginPanel {
    private final static Scanner scanner = new Scanner(System.in);
    private final LoginEngine loginEngine;

    public LoginPanel (LoginEngine loginEngine){
        this.loginEngine = loginEngine;
    }


    public <T extends Account> T login() {
        String mail;
        System.out.println("Welcome in Login panel! \n ------------------");
        int attempts = 3;
        mail = enterMail();
        T account = null;
        if (!mailExistenceCheck(mail)) {
            System.out.println("Couldn't find account assigned to that mail. Create new account. ");
        } else {
            while (attempts > 0) {
                String password = enterPassword();
                account = login(mail, password);
                if (account == null) {
                    attempts--;
                    System.out.println("Wrong password, enter correct one. " + attempts + " attempts remaining.");
                }else {
                    return account;
                }
            }
        }
        return account;
    }

    private <T extends Account> T login(String mail, String password){
        return (T) loginEngine.login(mail, password);
    }
    private boolean mailExistenceCheck(String mail) {
        return loginEngine.mailExistenceCheck(mail);
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
