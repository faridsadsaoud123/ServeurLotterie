import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

public class Billet implements Serializable {
    private String numeroSerie;
    private List<Integer> numerosChoisis;
    public Billet(){
        this.numeroSerie = generateNumeroSerie();
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
}
