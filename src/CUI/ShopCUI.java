package CUI;

import domain.BenutzerService;
import domain.bestand.Artikel;
import domain.bestand.ArtikelService;
import domain.bestand.ShoppingService;
import domain.verwaltung.KundenVerwaltung;
import domain.verwaltung.MitarbeiterVerwaltung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class ShopCUI {

    private BufferedReader in;
    private List<Artikel> artikelListe;
    private ArtikelService artikelService = new ArtikelService();
    private BenutzerService benutzerService = new BenutzerService();
    private ShoppingService shoppingService = new ShoppingService();

    public ShopCUI() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.artikelListe = new ArrayList<>();

        //Hinzufügen von Test-Artikeln
        artikelListe.add(artikelService.artikelAnlegen("Kuechengeraete",1 , "Kaffeemaschine", 10, 79.99f));
        artikelListe.add(artikelService.artikelAnlegen("Kuechengeraete",2 , "Toaster", 5, 39.99f));
    }

    private void gibMenueAus() {
        if (benutzerService.getEingeloggterBenutzer() == null) {
        
            System.out.print("\n===== Shop Menü =====");
            System.out.print("         \n 1. Registrieren");
            System.out.print("         \n 2. Login");
            System.out.print("         \n  ---------------------");
            System.out.println("         \n 3. Beenden");

        } else if (benutzerService.getEingeloggterBenutzer() instanceof MitarbeiterVerwaltung) {
            System.out.print("\n===== Mitarbeiter Menu =====");
            System.out.print("         \n  Artikel anzeigen: 'a'");
            System.out.print("\nBestand aendern: 'b'");
            System.out.print("\n Mitarbeiter registrieren: 'm'");
            System.out.print("\nAusloggen: 'o'");
        } else if (benutzerService.getEingeloggterBenutzer() instanceof KundenVerwaltung) {
            System.out.print("\n===== Kunden Menu =====");
            System.out.print("         \n  Artikel anzeigen: 'a'");
            System.out.print("\nArtikel in Warenkorb legen: 'w'");
            System.out.print("\nStueckzahl im Warenkorb aendern: 's'");
            System.out.print("\nWarenkorb leeren: 'l'");
            System.out.print("\nWarenkorb kaufen: 'k'");
            System.out.print("\nAusloggen: 'o'");

        }
        
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
            case "b":
             //   this.artikelService.artikelBearbeiten(); //Parameter hier in der CUI mit einer Methode aufsammeln und dann Methode aufrufen
                break;
            
            case "2":
                this.login();
                break;
            case "k":
                this.shoppingService.kaufen();
                break;
            case "l":
                this.shoppingService.warenkorbLeeren();
                break;
            case "m":
              //  this.benutzerService.registriereMitarbeiter(); //Parameter hier in der CUI sammeln und dann registriereMitarbeiter() damit aufrufen
                break;
            case "o":
                this.benutzerService.ausloggen();
                break;
            case "3":
                System.out.println("Beende Programm...");
                break;
            case "1":
                this.registrieren();
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

        var benutzer = benutzerService.einloggen(benutzerkennung, passwort);

        if (benutzer != null) {
            System.out.println("Login erfolgreich. Willkommen, " + benutzer.getName() + "!");
        } else {
            System.out.println("Loginversuch fehlgeschlagen.");
        }
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


        int kundenID = kundenID + 1; //wie mache ich einen vernuenftigen Counter für die ID's?
        KundenVerwaltung neuerKunde = new KundenVerwaltung(name, benutzerkennung, passwort, adresse, kundenID);

        System.out.println("Registrierung erfolgreich. Ihre Kundennummer ist: " + kundenID);
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
