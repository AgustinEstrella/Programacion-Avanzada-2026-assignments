package Classes;

public abstract class Template {

    public final void generateShow(){
        setStage();
        setMics();
        setInstruments();
    }

    private void setStage(){
        System.out.println("Stage check!");
    }

    protected abstract void setMics();
    protected abstract void setInstruments();
}
