import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Billet implements Serializable {
    private String numeroSerie;
    private ArrayList<Integer> numerosChoisis;
    private int k;
    private int category;
    int joueur;
    private int nbNgagant = 0;
    Billet(int joueur, int category, int k, ArrayList<Integer> list){
        this.numeroSerie = generateNumeroSerie();
        this.category = category;
        this.numerosChoisis = new ArrayList<>();
        this.k = k;
        this.joueur = joueur;
        if(category == 1){
            genererBillet1();
        }else{
            genererBillet2(list);
        }
    }
    public String toString() {
        return "Numero de serie: "+this.numeroSerie + "\n";
    }
    public void setNbNgagant(int i){
        this.nbNgagant = i;
    }
    public int getNbNgagant(){
        return this.nbNgagant;
    }
    public int getJoueur(){
        return this.joueur;
    }
    public static String generateNumeroSerie() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int SERIE_LENGTH = 20;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(SERIE_LENGTH);

        for (int i = 0; i < SERIE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }
    public void genererBillet1(){
        Random random = new Random();
        for(int i = 0; i < k; i++){
            numerosChoisis.add(random.nextInt(99));
        }
    }
    public void genererBillet2(ArrayList<Integer> list){
        for(int i = 0; i < k; i++){
            numerosChoisis.add(list.get(i));
        }
    }
    public ArrayList<Integer> getNumerosChoisis() {
        return numerosChoisis;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }


}
