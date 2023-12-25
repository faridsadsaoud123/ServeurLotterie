import java.util.*;

public class Server {
    private int n, k, t, duree;
    private ArrayList<Integer> listNumbers;
    private boolean loterieEnCours;
    private Timer timer;
    public Server(int n, int k, int t, int duree){
        this.n = n;
        this.k = k;
        this.t = t;
        this.duree = duree;
        this.listNumbers = new ArrayList<>(n);
        Random random  = new Random();
        for(int i = 0; i <= n; i++){
            listNumbers.set(i, random.nextInt(99));
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
            }
        }, duree * 1000);
    }

    private void tirerNumerosGagnants() {

    }

}
