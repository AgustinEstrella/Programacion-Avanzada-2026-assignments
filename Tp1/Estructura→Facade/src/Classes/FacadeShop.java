package Classes;

public class FacadeShop{
    //This class is the bridge between the app brain and the customer
    
    Shop theShop;

    public FacadeShop(Shop sh){
        this.theShop = sh;
    }

    public void openShop(){
        theShop.openDoors();
    }

    public void makePurchase(){
        theShop.takeOrder();
    }

    public void closeShop(){
        theShop.cleanFloor();
        theShop.closeDoors();
    }
    
}