public final class UserCreationPanel extends AccountCreationPanel {
    private final LoginEngine loginEngine;

    public UserCreationPanel(LoginEngine loginEngine){
        super(loginEngine);
        this.loginEngine = loginEngine;
    }

    @Override
    protected User createAccountAndAddToEngine(String mail, byte[] hash) {
        User newUser = new User(mail);
        loginEngine.addAccountToEngine(newUser, mail, hash);
        return newUser;
    }
}
