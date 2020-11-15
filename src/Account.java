import java.util.Objects;
import java.util.regex.Pattern;

public abstract class Account implements Comparable <Account> {

    /**
    * An abstract class of an account.
    * It will be used as a parent for user and admin account.
    */

    private static int counter=0;
    private int Id;
    private String login;
    private final String mail;

    public Account (String mail){
            this.Id = ++counter;
            this.login = mail;
            this.mail = mail;
    }

    @Override
    public int compareTo(Account o) {
        return this.mail.compareTo(o.getMail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return mail.equals(account.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    public int getId(){
        return Id;
    }

    public String getLogin() {
        return login;
    }

    public String getMail() {
        return mail;
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " id: " + getId() +", Name: " + getLogin() + ", mail: " + getMail();
    }


}
