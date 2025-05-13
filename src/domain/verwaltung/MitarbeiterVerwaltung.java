package domain.verwaltung;

import domain.bestand.Artikel;
import domain.bestand.ArtikelService;


public class MitarbeiterVerwaltung extends Benutzer implements ArtikelVerwaltung {

    private int mitarbeiterID;
    private ArtikelService artikelService = new ArtikelService(); //ArtikelService objekt, um dessen Methoden zu verwenden

    public MitarbeiterVerwaltung(String name,String benutzerkennung, String passwort, int id) {
        super(name, benutzerkennung, passwort);
        this.mitarbeiterID = id;
    }

    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    public Artikel artikelAnlegen(String kategorie_name, int id, String name, int bestand, int preis) {
        return artikelService.artikelAnlegen(kategorie_name, id, name, bestand, preis);
    }

    //auf Methode zum artikelBearbeiten zugreifen. Änderung an allen Parametern möglich
    public void artikelBearbeiten(Artikel artikel, String name, int bestand, int preis) {
        artikelService.artikelBearbeiten(artikel, name, bestand, preis);
    }

    public void ereignisseEinsehen() {
        for (Ereignis e : Ereignis.getAlleEreignisse()) {
            System.out.println(e.toString());
        }
    }
}
