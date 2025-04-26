package domain.verwaltung;

public class KundenVerwaltung extends Person{

    private int kundenID;
    private String adresse;

    public KundenVerwaltung(String name, int id, String adresse) {
        super(name);
        this.kundenID = id;
        this.adresse = adresse;
    }

    public int getKundenID() {
        return kundenID;
    }

    public void setKundenID(int kundenID) {
        this.kundenID = kundenID;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
