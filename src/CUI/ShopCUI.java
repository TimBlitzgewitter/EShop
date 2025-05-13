package CUI;

import domain.bestand.Artikel;
import domain.bestand.ArtikelService;
import domain.bestand.ShoppingService;
import domain.verwaltung.KundenVerwaltung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class ShopCUI {

    private BufferedReader in;
    private List<Artikel> artikelListe;
    private List<KundenVerwaltung> kundenListe;
    private KundenVerwaltung eingeloggterKunde;
    private ArtikelService artikelService = new ArtikelService();

    public ShopCUI() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.artikelListe = new ArrayList<>();
        this.kundenListe = new ArrayList<>();
        this.eingeloggterKunde = null;

        //Hinzufügen von Test-Artikeln
        artikelListe.add(artikelService.artikelAnlegen("Kuechengeraete",1 , "Kaffeemaschine", 10, 79.99f));
        artikelListe.add(artikelService.artikelAnlegen("Kuechengeraete",2 , "Toaster", 5, 39.99f));
    }

    private void gibMenueAus() {
        System.out.print("\n===== Shop Menü =====");
        System.out.print("         \n  Artikel anzeigen: 'a'");
        System.out.print("         \n  Login: 'l'");
        System.out.print("         \n  Registrieren:  'r'");
        System.out.print("         \n  ---------------------");
        System.out.println("         \n  Beenden:        'q'");
        System.out.print("> ");
        System.out.flush(); //was ist flush? Stand in seinem Code Beispiel von der Bibliothek
    }

    private String liesEingabe() throws IOException {
        return this.in.readLine();
    }

    private void verarbeiteEingabe(String eingabe) throws IOException {
        switch (eingabe) {
            case "a":
                this.zeigeArtikelAn();
                break;
            case "l":
                this.login();
                break;
            case "r":
                this.registrieren();
                break;
            case "q":
                System.out.println("Beende Programm...");
                break;
            default:
                System.out.println("ungueltige Eingabe");
        }

    }

    private void zeigeArtikelAn() {
        System.out.println("\nVerfuegbare Artikel:");
        for (Artikel artikel : artikelListe) {
            System.out.println("Nr: " + artikel.getArtikelID() + ", Bezeichnung: " + artikel.getArtikel_name() + ", Bestand: " + artikel.getBestand() + ", Preis: " + artikel.getPreis() + " €");
        }
    }

    private void login() throws IOException {
        System.out.print("Benutzerkennung > ");
        String benutzerkennung = liesEingabe();

        System.out.print("Passwort > ");
        String passwort = liesEingabe();

        for (KundenVerwaltung kunde : kundenListe) {
            if (kunde.getBenutzerkennung().equals(benutzerkennung) && kunde.pruefePasswort(passwort)) {
                eingeloggterKunde = kunde;
                System.out.println("Login erfolgreich. Willkommen, " + kunde.getName() + "!");
                return;
            } 
        }
        System.out.println("Loginversuch fehlgeschlagen.");
    }

    private void registrieren() throws IOException {
        System.out.print("Name > ");
        String name = liesEingabe();

        System.out.print("Benutzerkennung > ");
        String benutzerkennung = liesEingabe();

        System.out.print("Passwort > ");
        String passwort = liesEingabe();

        System.out.print("Adresse > ");
        String adresse = liesEingabe();


        int id = kundenListe.size() + 1;
        KundenVerwaltung neuerKunde = new KundenVerwaltung(name, benutzerkennung, passwort, adresse, id);
        kundenListe.add(neuerKunde);

        System.out.println("Registrierung erfolgreich. Ihre Kundennummer ist: " + id);
    }

    public void run() {
        String input = "";

        do {
            this.gibMenueAus();

            try {
                input = this.liesEingabe();
                this.verarbeiteEingabe(input);
            } catch (IOException e) {
                System.out.println("Fehler bei der Eingabe.");
            }
        } while(!input.equals("q"));

    }

    public static void main(String[] args) {
            ShopCUI cui = new ShopCUI();
            cui.run();
    }
}
