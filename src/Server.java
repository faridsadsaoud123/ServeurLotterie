import AutreEvent.*;

import java.io.*;
import java.util.*;

public class Server {
    private int n, k, t, duree;
    private ArrayList<Integer> listNumbers;
    private boolean loterieEnCours;
    private Timer timer;
    private AutreEventNotifieur notifieur = new AutreEventNotifieur();
    private ArrayList<Integer> listeNumGagnants;
    private Random random;

    public Server(int n, int k, int t, int duree){
        this.n = n;
        this.k = k;
        this.t = t;
        this.duree = duree;
        this.listNumbers = new ArrayList<>();
        this.listeNumGagnants = new ArrayList<>();
        random  = new Random();
        for(int i = 0; i < n; i++){
            listNumbers.add(random.nextInt(99));
        }
    }

    public void demarrerLoterie() {
        loterieEnCours = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loterieEnCours = false;
                tirerNumerosGagnants();
                //TODO: Strop all threads
            }
        }, duree * 1000);
    }

    private void tirerNumerosGagnants() {
        for(int i = 0; i < k; i++){
            listeNumGagnants.add(listNumbers.get(random.nextInt(99)));
        }
        notifieur.diffuserAutreEvent(new AutreEvent(this, listeNumGagnants));
        timer.cancel();
    }

    public void addAutreEventListener(AutreEventListener listener) {
        notifieur.addAutreEventListener(listener);
    }
    public void removeAutreEventListener(AutreEventListener listener) {
        notifieur.removeAutreEventListener(listener);
    }

    public void vendreBillet(Joueur joueur, int number, int category, ArrayList<ArrayList<Integer>> numbresSouhaite){
        Thread acheter = new Thread(new Vendre(joueur, number, category, numbresSouhaite));
        acheter.start();
    }

    class Vendre implements Runnable{
        private int number;
        private int category;
        private ArrayList<Billet> listBillet;
        private ArrayList nombresSouhaite;
        private Joueur joueur;
        public Vendre(Joueur joueur, int number, int category, ArrayList<ArrayList<Integer>> nombresSouhaite){
            this.number = number;
            this.category = category;
            this.joueur = joueur;
            this.nombresSouhaite = nombresSouhaite;
            listBillet = new ArrayList<>();
        }
        public void serialiser(Billet billet) throws IOException {
            FileOutputStream fos = new FileOutputStream("Billets");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(billet);
        }
        public List<Billet> deserialiser(){
            List<Billet> listBillets = new ArrayList<>();
            try{
                FileInputStream fis = new FileInputStream("Billet");
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true){
                    try{
                        Billet billet = (Billet)ois.readObject();
                        listBillets.add(billet);
                    }catch(EOFException eof){
                        break;
                    }
                }
            }
            catch(IOException io){
                System.out.println("Erreur");
            }catch (ClassNotFoundException cnf){
                System.out.println("Error");
            }
            return null;
        }
        public void run(){
            if(category == 1){
                for(int i = 0; i < number; i++){
                    Billet billet = new Billet(1, k, null);
                    try{
                        serialiser(billet);
                    }
                    catch(IOException io){
                        System.out.println("Error");
                    }
                    //TODO: serialize billet
                    listBillet.add(billet);
                }
            }else{
                for(int i = 0; i < number; i++){
                    Billet billet = new Billet(2, k, (ArrayList<Integer>) nombresSouhaite.get(i));
                    try{
                        serialiser(billet);
                    }
                    catch(IOException io){
                        System.out.println("Error");
                    }
                    //TODO: serialize billet
                    listBillet.add(billet);
                }
            }
            joueur.billetAchete = listBillet;
        }
    }

}
