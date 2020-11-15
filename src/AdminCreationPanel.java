public final class AdminCreationPanel extends AccountCreationPanel {
    private final LoginEngine loginEngine;

    public AdminCreationPanel(LoginEngine loginEngine){
        super(loginEngine);
        this.loginEngine = loginEngine;
    }

    @Override
    protected Admin createAccountAndAddToEngine(String mail, byte[] hash) {
        Admin newAdmin = new Admin(mail);
        loginEngine.addAccountToEngine(newAdmin, mail, hash);
        return newAdmin;
    }
}
