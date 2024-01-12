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
    private ArrayList<Billet> billetVendu;
    public Server(int n, int k, int t, int duree){
        this.n = n;
        this.k = k;
        this.t = t;
        this.duree = duree;
        this.listNumbers = new ArrayList<>();
        this.listeNumGagnants = new ArrayList<>();
        this.billetVendu = new ArrayList<>();
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
            listeNumGagnants.add(listNumbers.get(random.nextInt(90)));
        }
        ArrayList<Billet> billets  = (ArrayList<Billet>) deserialiser();
        ArrayList<Billet> billetsGagnants = new ArrayList<Billet>();
        for(Billet billet: billets){
            int count =0;
            for (int i=0 ;i<billet.getNumerosChoisis().toArray().length;i++){
                ArrayList<Integer> numChoisis = billet.getNumerosChoisis();
                if(listeNumGagnants.contains(numChoisis.get(i))){
                    count++;
                }
            }
                if (count>=t){
                    billetsGagnants.add(billet);
                }
        }
        notifieur.diffuserAutreEvent(new AutreEvent(this, billetsGagnants));
        timer.cancel();
    }
    public void addAutreEventListener(AutreEventListener listener) {
        notifieur.addAutreEventListener(listener);
    }
    public void removeAutreEventListener(AutreEventListener listener) {
        notifieur.removeAutreEventListener(listener);
    }

    public List<Billet> deserialiser() {
        List<Billet> listBillets = null;
        try (FileInputStream fis = new FileInputStream("Billets");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
//            while (true) {
//                try {
//                    Billet billet = (Billet) ois.readObject();
//                    listBillets.add(billet);
//                } catch (EOFException eof) {
//                    break;
//                }
//            }
            listBillets = (ArrayList<Billet>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(listBillets.size());
        return listBillets;
    }

    public void vendreBillet(Joueur joueur, int number, int category, ArrayList<ArrayList<Integer>> numbresSouhaite){
        Thread acheter = new Thread(new Vendre(joueur, number, category, numbresSouhaite));
        acheter.start();
    }
    public void serialiser(ArrayList<Billet> list) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("Billets");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        }
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
            this.listBillet = new ArrayList<>();
        }

        public void run(){
            if(category == 1){
                for(int i = 0; i < number; i++){
                    Billet billet = new Billet(1, k, null);
                    try{
                        synchronized (billetVendu){
                            billetVendu.add(billet);
                            serialiser(billetVendu);
                        }
                    }
                    catch(IOException io){
                        System.out.println("Error");
                    }

                    listBillet.add(billet);
                }
            }else{
                for(int i = 0; i < number; i++){
                    Billet billet = new Billet(2, k, (ArrayList<Integer>) nombresSouhaite.get(i));
                    try{
                        synchronized (billetVendu){
                            billetVendu.add(billet);
                            serialiser(billetVendu);
                        }
                    }
                    catch(IOException io){
                        System.out.println("Error");
                    }
                    listBillet.add(billet);
                }
            }
            joueur.billetAchete = listBillet;
        }
    }

}
