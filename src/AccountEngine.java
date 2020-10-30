import java.util.*;
import java.util.regex.Pattern;

public final class AccountEngine {
    private static Map<String, byte[]> loginPasswords = new LinkedHashMap<>();
    private static Map<String, Account> accountsByMails = new TreeMap<>();
    private static List<Account> accounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public enum AccountType {
        ADMIN, USER;
    }

    public void showAccounts() {
        for (Account ac : accounts) {
            System.out.println(ac);
        }
    }

    public void showMails() {
        for (String mail : accountsByMails.keySet()) {
            System.out.println(mail);
        }
    }

    public static Account login() {
        String mail;
        int attempts = 3;
        mail = enterMail();
        Account account = null ;
        if (!mailExistenceCheck(mail)) {
            System.out.println("Couldn't find account assigned to that mail. Create new account. ");
        } else {
            while (attempts > 0) {
                 account =  login(mail, enterPassword());
                } if(account == null) {
                    attempts--;
                    System.out.println("Wrong password, enter correct one. " + attempts + " attempts remaining.");
                }
            }
        return account;
    }

    public static Account login(String mail, String password){
        byte[] hash = PasswordHasher.passwordHash(password);
        byte[] hashReference = loginPasswords.get(mail);
        if(Arrays.equals(hash, hashReference)){
            Account account = accountsByMails.get(mail);
            return account;
        }else
            return null;
    }

    public static void createAccount(AccountType accountType) {
        if (accountType == null) {
            throw new IllegalArgumentException("In AccountEngine.createAccount()");
        }
        String login, mail, password;
        mail = enterNewMail();
        login = mail;
        byte[] hash = PasswordHasher.passwordHash(enterNewPassword());
        Account newAccount = null;
        if (accountType == AccountType.ADMIN)
            newAccount = new Admin(login, mail);
        if( accountType == AccountType.USER)
            newAccount = new User(login, mail);
        accounts.add(newAccount);
        accountsByMails.put(mail, newAccount);
        loginPasswords.put(mail, hash);

    }


    public static void loadTestData() {
        Account newUser = new User("qwe@o2.pl", "qwe@o2.pl");
        accounts.add(newUser);
        accountsByMails.put("qwe@o2.pl", newUser);
        loginPasswords.put("qwe@o2.pl", PasswordHasher.passwordHash("Zaq12wsx"));
        newUser = new User("asd@o2.pl", "asd@o2.pl");
        accounts.add(newUser);
        accountsByMails.put("asd@o2.pl", newUser);
        loginPasswords.put("asd@o2.pl", PasswordHasher.passwordHash("Zaq12wsx"));
        newUser = new Admin("zxc@o2.pl", "zxc@o2.pl");
        accounts.add(newUser);
        accountsByMails.put("zxc@o2.pl", newUser);
        loginPasswords.put("zxc@o2.pl", PasswordHasher.passwordHash("Zaq12wsx"));
        newUser = new User("ert@o2.pl", "ert@o2.pl");
        accounts.add(newUser);
        accountsByMails.put("ert@o2.pl", newUser);
        loginPasswords.put("ert@o2.pl", PasswordHasher.passwordHash("Zaq12wsx"));
        newUser = new User("fgh@o2.pl", "fgh@o2.pl");
        accounts.add(newUser);
        accountsByMails.put("fgh@o2.pl", newUser);
        loginPasswords.put("fgh@o2.pl", PasswordHasher.passwordHash("Zaq12wsx"));
    }

    private static String enterNewPassword() {
        String password;
        while (true) {
            System.out.println("Password should be at least 8 characters long. Must contain at least one UpperCase, one digit. ");
            password = enterPassword();
            boolean passwordCorrect = passwordCorrectnessCheck(password);
            if (!passwordCorrect) {
                System.out.println("Password too weak. Plz type stronger password.");
            } else {
                return password;
            }
        }
    }

    private static boolean passwordCorrectnessCheck(String password) {
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }


    private static String enterNewMail() {
        String mail;
        while (true) {
            mail = enterMail();
            boolean mailValid = mailValidate(mail);
            if (!mailValid) {
                System.out.println("Wrong e-mail format. Please type correct one.");
            } else {
                boolean mailExists = mailExistenceCheck(mail);
                if (mailExists) {
                    System.out.println("This e-mail already exists. Cannot create account");
                } else {
                    return mail;
                }
            }
        }
    }

    private static String enterMail() {
        System.out.println("Enter e-mail: ");
        String input = scanner.nextLine();
        return input;
    }

    private static String enterPassword() {
        System.out.println("Enter password: ");
        String input = scanner.nextLine();
        return input;
    }

    private static boolean mailExistenceCheck(String mail) {
        for (String existingMail : loginPasswords.keySet()) {
            if (mail.equals(existingMail))
                return true;
        }
        return false;
    }

    private static boolean mailValidate(String mail) {
        String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]{2,}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(mail).matches();
    }


}
