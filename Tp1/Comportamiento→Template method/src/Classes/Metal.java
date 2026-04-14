package Classes;

public class Metal extends Template{

    @Override
    protected void setMics(){
        System.out.println("Mics setted succesfully!");
    }
    
    @Override
    protected void setInstruments(){
        System.out.println("Guitars, bass and drums carefully placed");
    }
    
}
