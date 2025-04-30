package domain.verwaltung;

public class MitarbeiterVerwaltung extends Person implements ArtikelVerwaltung{

    private int mitarbeiterID;

    public MitarbeiterVerwaltung(String name, int id) {
        super(name);
        this.mitarbeiterID = id;
    }

    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    public void artikelAnlegen(String bezeichnung, String kategorie, int preis, int bestand) {
        //Parameter nochmal überprüfen wenn zuhause
        this.bezeichnung = bezeichnung;
    }

    public void artikelBearbeiten(Artikel artikel, String bezeichnung, String kategorie, int preis, int bestand) {
        
    }

    public void neuerMitarbeiter() {

    }

}
