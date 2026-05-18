package gehege;

public class Savannengehege extends Gehege{

    /**
     * Erstellt ein neues Savannengehege-Objekt.
     *
     * @param maxTiere maximale Tiere des Geheges
     * @param fuetterungszeit Fuetterungszeit des Geheges
     */
    public Savannengehege(int maxTiere, String fuetterungszeit) {
        super(maxTiere, fuetterungszeit, "Savanne");
    }
}
