import Classes.*;

public class MainTemplateMethod {
    public static void main(String[] args) throws Exception {
     
        System.out.println("Setting a trap show");
        Template trapShow = new Trap();

        trapShow.generateShow();
        System.out.println("------------------");
        
        System.out.println("Setting a metal show");
        Template metalShow =  new Metal();

        metalShow.generateShow();
        System.out.println("------------------");
        
    }
}