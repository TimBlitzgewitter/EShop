package domain.bestand;

public class Artikel extends Kategorie{

    private int artikelID;
    private String artikel_name;

    public Artikel(String kategorie_name, int id, String artikel_name) {
        super(kategorie_name);
    }

    public int getArtikelID() {
        return artikelID;
    }

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public String getArtikel_name() {
        return artikel_name;
    }

    public void setArtikel_name(String artikel_name) {
        this.artikel_name = artikel_name;
    }
}
