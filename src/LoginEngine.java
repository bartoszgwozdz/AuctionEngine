import java.util.*;

public final class LoginEngine<T extends Account> {

    private final AccountsData<T> accountsData;

    public LoginEngine() {
        accountsData = new AccountsData<T>();
    }

    public T login(String mail, String password) {
        byte[] hash = PasswordHasher.passwordHash(password);
        byte[] hashReference = getUserHashedPassword(mail);
        if (Arrays.equals(hash, hashReference)) {
            return getAccount(mail);
        } else
            return null;
    }

    private byte[] getUserHashedPassword(String mail) {
        return accountsData.getUserHashedPasswordCopy(mail);
    }

    private T getAccount(String mail) {
        return accountsData.getAccount(mail);
    }

    public void addAccountToEngine(T account, String mail, byte[] hash) {
        if(accountsData.passwordsByMail.containsKey(mail)){
            System.out.println("This mail already exists");
        } else {
            accountsData.passwordsByMail.put(mail, hash);
            accountsData.accountsByMail.put(mail, account);
            accountsData.accounts.add(account);
        }
    }

    public boolean mailExistenceCheck(String mail) {
        for (String existingMail : accountsData.accountsByMail.keySet()) {
            if (mail.equals(existingMail))
                return true;
        }
        return false;
    }

    public void showAccounts() {
        for (T ac : accountsData.accounts) {
            System.out.println(ac);
        }
    }

    public void showMails() {
        for (String mail : accountsData.accountsByMail.keySet()) {
            System.out.println(mail);
        }
    }

    static class AccountsData <T extends Account> {
        private final Map<String, byte[]> passwordsByMail;
        private final Map<String, T> accountsByMail;
        private final List<T> accounts;

        public AccountsData() {
            passwordsByMail = new LinkedHashMap<>();
            accountsByMail = new TreeMap<>();
            accounts = new ArrayList<>();
        }

        public byte[] getUserHashedPasswordCopy(String mail) {
            byte[] hashHave = passwordsByMail.get(mail);
            byte[] hashReturn = new byte[hashHave.length];
            System.arraycopy(hashHave, 0, hashReturn, 0, hashReturn.length);
            return hashReturn;
        }

        public T getAccount(String name) {
            return accountsByMail.get(name);
        }


    }
}
