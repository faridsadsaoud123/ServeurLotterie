import AutreEvent.AutreEvent;
import AutreEvent.AutreEventListener;
import java.util.ArrayList;
import java.util.Random;

public class Joueur implements AutreEventListener {
    int numJoueur;
    ArrayList<Billet> billetAchete;

    public Joueur(){
        this.numJoueur = new Random().nextInt(9999);
    }

    @Override
    public void actionADeclancher(AutreEvent event) {
//        if (event.getSource() instanceof Server && event.getDonnee() instanceof ArrayList<?>){
//            ArrayList<Billet> listeBilletGagnant = (ArrayList<Billet>)event.getDonnee();
//            for (Billet billet:listeBilletGagnant){
//                if (billetAchete.contains(billet)){
//                    System.out.println("Je suis Gagnant");
//                }
//            }
//        }
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

    public int getNumJoueur() {
        return numJoueur;
    }
}
