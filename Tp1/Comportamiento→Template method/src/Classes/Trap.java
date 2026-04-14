package Classes;

public class Trap extends Template {

    @Override
    protected void setMics(){
        System.out.println("Mics checked, autotune set to: 100%");
    }

    @Override
    protected void setInstruments(){
        System.out.println("No instruments avaliable, just a synthetizer");
    }
    
}
