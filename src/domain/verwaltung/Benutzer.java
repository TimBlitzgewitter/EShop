package domain.verwaltung;

public abstract class Benutzer { //abstrakte Klasse Benutzer als Oberklasse von Kunden und Mitarbeiter

    private String name;
    private String benutzerkennung;
    private String passwort;

    public Benutzer(String name, String benutzerkennung, String passwort) {
        this.name = name;
        this.benutzerkennung = benutzerkennung;
        this.passwort = passwort;
    }

    //Vollständiger Name des Benutzers
    public String getName() {
        return name;
    }

    //Der Login-Name. Kein zusätzlicher Setter, da Benutzerkennung eindeutig sein sollte
    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setName(String name) {
        this.name = name;
    }

    //vergleicht eingegebenes Passwort mit dem gespeicherten Passwort
    public boolean pruefePasswort(String eingabe) {
        return passwort.equals(eingabe);
    }

    public void setPasswort(String neuesPasswort) {
        this.passwort = neuesPasswort;
    }

}
