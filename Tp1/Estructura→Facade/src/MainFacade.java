import Classes.FacadeShop;
import Classes.Shop;

public class MainFacade {
    public static void main(String[] args) throws Exception {
        Shop shop = new Shop();

        System.out.println("Welcome user, Im taking your order!");

        FacadeShop candyShop = new FacadeShop(shop);    
        candyShop.openShop();
        candyShop.makePurchase();
        candyShop.closeShop();
    }
}