import AutreEvent.AutreEvent;
import AutreEvent.AutreEventListener;
import java.util.ArrayList;

public class Joueur implements AutreEventListener {
    ArrayList<Billet> billetAchete;

    @Override
    public void actionADeclancher(AutreEvent event) {
        if (event.getSource() instanceof Server && event.getDonnee() instanceof ArrayList<?>){
            ArrayList<Integer> listeNumGagnant = (ArrayList<Integer>)event.getDonnee();
            //TODO: verify if i am Winner
        }
    }
    public void acheterBillet(Server server, int number, int category, ArrayList<ArrayList<Integer>> nombresSouhaite){
        server.vendreBillet(this, number, category, nombresSouhaite);
    }
    public void getBilletsAchete(){
        for (Billet billet : billetAchete) {
            ArrayList<Integer> numeros = billet.getNumerosChoisis();
            System.out.println("Numéro de série : " + billet.getNumeroSerie());
            System.out.print("Nombres choisis : ");
            for (int numero : numeros) {
                System.out.print(numero + " ");
            }
            System.out.println();
        }
    }

}
