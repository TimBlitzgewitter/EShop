package domain.verwaltung;

public abstract class Benutzer {

    private String name;
    private String benutzerkennung;
    private String passwort;

    public Benutzer(String name, String benutzerkennung, String passwort) {
        this.name = name;
        this.benutzerkennung = benutzerkennung;
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean pruefePasswort(String eingabe) {
        return passwort.equals(eingabe);
    }

    public void setPasswort(String neuesPasswort) {
        this.passwort = neuesPasswort;
    }

}
