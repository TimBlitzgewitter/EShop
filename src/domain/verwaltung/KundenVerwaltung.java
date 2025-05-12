package domain.verwaltung;

import domain.bestand.ShoppingService;
import domain.bestand.Artikel;
import java.util.Map;

public class KundenVerwaltung extends Benutzer {

    private int kundenID;
    private String adresse;
    private final ShoppingService shoppingService = new ShoppingService(); //final. Wird sich nicht aendern

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
    
    //Methoden delegieren an ShoppingService Klasse weiter
    public void inWarenkorbLegen(Artikel artikel, int stueckzahl) {
        shoppingService.inWarenkorbLegen(artikel, stueckzahl);
    }

    public void stueckzahlAendern(Artikel artikel, int neueStueckzahl) {
        shoppingService.stueckzahlAendern(artikel, neueStueckzahl);
    }

    public void warenkorbLeeren() {
        shoppingService.warenkorbLeeren();
    }

    public void kaufen() {
        shoppingService.kaufen();
    }

    public Map<Artikel, Integer> getWarenkorb() {
        return shoppingService.getWarenkorb();
    }
}
