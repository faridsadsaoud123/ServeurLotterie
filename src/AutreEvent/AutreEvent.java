package AutreEvent;

public class AutreEvent extends java.util.EventObject {
    private Object donnee;
    public AutreEvent(Object source, Object donnee) {
        super(source);
        this.donnee = donnee;
    }
    public Object getDonnee() {
        return this.donnee;
    }
}
