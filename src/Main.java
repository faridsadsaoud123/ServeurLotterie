import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] argv){
        Server server = new Server(90, 5, 2, 5);
        System.out.println("La loterie commence!");
        server.demarrerLoterie();
        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();
        Joueur joueur3 = new Joueur();
        Joueur joueur4 = new Joueur();
        Joueur joueur5 = new Joueur();
        Joueur joueur6 = new Joueur();
        Joueur joueur7 = new Joueur();
        Joueur joueur8 = new Joueur();
        Joueur joueur9 = new Joueur();

        server.addAutreEventListener(joueur1);
        server.addAutreEventListener(joueur2);
        server.addAutreEventListener(joueur3);
        server.addAutreEventListener(joueur4);
        server.addAutreEventListener(joueur5);
        server.addAutreEventListener(joueur6);
        server.addAutreEventListener(joueur7);
        server.addAutreEventListener(joueur8);
        server.addAutreEventListener(joueur9);

        joueur1.acheterBillet(server, 100, 1, null);
        joueur2.acheterBillet(server, 80, 1, null);
        joueur5.acheterBillet(server, 20, 1, null);
        joueur6.acheterBillet(server, 7, 1, null);
        joueur7.acheterBillet(server, 50, 1, null);

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(22, 32, 55, 99, 0)));
        list.add(new ArrayList<>(Arrays.asList(12, 32, 64, 88, 37)));
        joueur3.acheterBillet(server, 2, 2, list);

        list.clear();
        list.add(new ArrayList<>(Arrays.asList(44, 62, 34, 63, 82)));
        list.add(new ArrayList<>(Arrays.asList(43, 52, 26, 64, 77)));
        list.add(new ArrayList<>(Arrays.asList(95, 41, 13, 11, 9)));
        joueur4.acheterBillet(server, 3, 2, list);


        list.clear();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            List<Integer> randomList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                randomList.add(random.nextInt(100));
            }
            list.add((ArrayList<Integer>) randomList);
        }
        joueur8.acheterBillet(server, 50, 2, list);

        list.clear();
        for (int i = 0; i < 80; i++) {
            List<Integer> randomList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                randomList.add(random.nextInt(100));
            }
            list.add((ArrayList<Integer>) randomList);
        }
        joueur9.acheterBillet(server, 80, 2, list);


    }

}
