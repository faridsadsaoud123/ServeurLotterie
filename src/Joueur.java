import AutreEvent.AutreEvent;
import AutreEvent.AutreEventListener;

public class Joueur implements AutreEventListener {
    @Override
    public void actionADeclancher(AutreEvent event) {
        if (event.getSource() instanceof Server && event.getDonnee() instanceof String){
            //pensee = (String)event.getDonnee();
        }
    }
}
