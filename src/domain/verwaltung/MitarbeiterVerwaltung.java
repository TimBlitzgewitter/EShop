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

    public void artikelAnlegen() {

    }

    public void artikelBearbeiten() {
        
    }
}
