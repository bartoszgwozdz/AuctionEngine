public class Admin extends Account{
    public Admin(String login, String mail){
        super(login, mail);
    }

    public void loadTestCategories(){
        AuctionEngine.loadTestCategories(this);
    }
}
