package domain.verwaltung;

public class KundenVerwaltung extends Benutzer {

    private int kundenID;
    private String adresse;

    //Konstruktor für Kundenobjekt. ID und adresse werden hier initialisiert. Der rest wird an Benutzer-Konstruktor weitergereicht
    public KundenVerwaltung(String name, String benutzerkennung, String passwort, String adresse, int id) {
        super(name, benutzerkennung, passwort);
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

    public void inWarenkorbLegen() {
        //TODO
    }

    public void stueckzahlAendern() { //von Artikeln im Warenkorb
        //TODO
    }

    public void warenkorbLeeren() {
        //TODO
    }

    public void kaufen() {
        //TODO
    }
    
}
