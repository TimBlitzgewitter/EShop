package domain.verwaltung;

import domain.bestand.Artikel;

public interface ArtikelVerwaltung {
    Artikel artikelAnlegen(String kategorie_name, int id, String name, int bestand, int preis);
    void artikelBearbeiten(Artikel artikel, String name, int bestand, int preis);
}
