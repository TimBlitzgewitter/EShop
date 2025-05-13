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
    private int kundenCounter = 1; // Zähler für KundenIDs
    private int mitarbeiterCounter = 1; // Zähler für MitarbeiterIDs

    public ShopCUI() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.artikelListe = new ArrayList<>();

        // Test-Artikel hinzufügen
        artikelListe.add(artikelService.artikelAnlegen("Kuechengeraete", 1, "Kaffeemaschine", 10, 79.99f));
        artikelListe.add(artikelService.artikelAnlegen("Kuechengeraete", 2, "Toaster", 5, 39.99f));

        // Test-Mitarbeiter hinzufügen (für Demo-Zwecke)
        benutzerService.registriereMitarbeiter("Admin", "admin", "admin123", 1);
    }

    private void gibMenueAus() {
        if (benutzerService.getEingeloggterBenutzer() == null) {
            System.out.print("\n===== Shop Menü =====");
            System.out.print("\n1. Registrieren");
            System.out.print("\n2. Login");
            System.out.print("\n---------------------");
            System.out.print("\n3. Beenden");
        } else if (benutzerService.getEingeloggterBenutzer() instanceof MitarbeiterVerwaltung) {
            System.out.print("\n===== Mitarbeiter Menu =====");
            System.out.print("\na: Artikel anzeigen");
            System.out.print("\nb: Bestand aendern");
            System.out.print("\nm: Mitarbeiter registrieren");
            System.out.print("\no: Ausloggen");
        } else if (benutzerService.getEingeloggterBenutzer() instanceof KundenVerwaltung) {
            System.out.print("\n===== Kunden Menu =====");
            System.out.print("\na: Artikel anzeigen:");
            System.out.print("\nw: Artikel in Warenkorb legen");
            System.out.print("\ns: Stueckzahl im Warenkorb aendern");
            System.out.print("\nl: Warenkorb leeren");
            System.out.print("\nk: Warenkorb kaufen");
            System.out.print("\no: Ausloggen");
        }
        
        System.out.print("\n> ");
        System.out.flush();
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
                this.aendereBestand();
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
                this.registriereMitarbeiter();
                break;
            case "o":
                this.benutzerService.ausloggen();
                System.out.println("Erfolgreich ausgeloggt.");
                break;
            case "3":
                System.out.println("Beende Programm...");
                System.exit(0);
                break;
            case "1":
                this.registrieren();
                break;
            default:
                System.out.println("Ungueltige Eingabe");
        }
    }

    private void zeigeArtikelAn() {
        System.out.println("\nVerfuegbare Artikel:");
        for (Artikel artikel : artikelListe) {
            System.out.println("Nr: " + artikel.getArtikelID() + ", Bezeichnung: " + artikel.getArtikel_name() + 
                             ", Bestand: " + artikel.getBestand() + ", Preis: " + artikel.getPreis() + " €");
        }
    }

    private void aendereBestand() throws IOException {
        try {
            System.out.print("Artikel-ID eingeben > ");
            int artikelId = Integer.parseInt(liesEingabe());

            Artikel artikelToEdit = null;
            for (Artikel artikel : artikelListe) {
                if (artikel.getArtikelID() == artikelId) {
                    artikelToEdit = artikel;
                    break;
                }
            }

            if (artikelToEdit == null) {
                System.out.println("Artikel nicht gefunden!");
                return;
            }

            System.out.print("Neuer Name (aktuell: " + artikelToEdit.getArtikel_name() + ") > ");
            String neuerName = liesEingabe();

            System.out.print("Neuer Bestand (aktuell: " + artikelToEdit.getBestand() + ") > ");
            int neuerBestand = Integer.parseInt(liesEingabe());

            System.out.print("Neuer Preis (aktuell: " + artikelToEdit.getPreis() + ") > ");
            float neuerPreis = Float.parseFloat(liesEingabe());

            artikelService.artikelBearbeiten(artikelToEdit, neuerName, neuerBestand, neuerPreis);
            System.out.println("Artikel erfolgreich bearbeitet!");
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Zahleneingabe!");
        }
    }

    private void registriereMitarbeiter() throws IOException {
        if (!(benutzerService.getEingeloggterBenutzer() instanceof MitarbeiterVerwaltung)) {
            System.out.println("Nur Mitarbeiter dürfen neue Mitarbeiter registrieren!");
            return;
        }

        System.out.print("Name > ");
        String name = liesEingabe();

        System.out.print("Benutzerkennung > ");
        String benutzerkennung = liesEingabe();

        System.out.print("Passwort > ");
        String passwort = liesEingabe();

        boolean erfolg = benutzerService.registriereMitarbeiter(name, benutzerkennung, passwort, mitarbeiterCounter++);
        
        if (erfolg) {
            System.out.println("Mitarbeiter wurde erfolgreich registriert.");
        } else {
            System.out.println("Registrierung fehlgeschlagen. Benutzerkennung bereits vergeben.");
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

        boolean erfolg = benutzerService.registrierenKunde(name, benutzerkennung, passwort, adresse, kundenCounter++);
        
        if (erfolg) {
            System.out.println("Registrierung erfolgreich.");
        } else {
            System.out.println("Registrierung fehlgeschlagen. Benutzerkennung bereits vergeben.");
        }
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