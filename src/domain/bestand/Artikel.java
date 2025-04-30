package domain.bestand;

public class Artikel extends Kategorie{

    private int artikelID;
    private String artikel_name;
    private int bestand;

    public Artikel(String kategorie_name, int id, String artikel_name) {
        super(kategorie_name);
    }

    public int getArtikelID() {
        return artikelID;
    }

    public int getBestand() {
        return bestand;
    }

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    public String getArtikel_name() {
        return artikel_name;
    }

    public void setArtikel_name(String artikel_name) {
        this.artikel_name = artikel_name;
    }
}
