import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] argv){
        Server server = new Server(90, 5, 2, 5);
        server.demarrerLoterie();
        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();
        Joueur joueur3 = new Joueur();
        Joueur joueur4 = new Joueur();
        server.addAutreEventListener(joueur1);
        server.addAutreEventListener(joueur2);
        server.addAutreEventListener(joueur3);
        server.addAutreEventListener(joueur4);

        joueur1.acheterBillet(server, 100, 1, null);
        joueur2.acheterBillet(server, 4, 1, null);

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(22, 32, 55, 99, 0)));
        list.add(new ArrayList<>(Arrays.asList(12, 32, 64, 88, 37)));
        joueur3.acheterBillet(server, 2, 2, list);

        list.clear();
        list.add(new ArrayList<>(Arrays.asList(44, 62, 34, 63, 82)));
        list.add(new ArrayList<>(Arrays.asList(43, 52, 26, 64, 77)));
        list.add(new ArrayList<>(Arrays.asList(95, 41, 13, 11, 9)));
        joueur4.acheterBillet(server, 3, 2, list);
    }
}
