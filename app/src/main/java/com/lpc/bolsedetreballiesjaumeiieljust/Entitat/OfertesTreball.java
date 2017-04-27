package com.lpc.bolsedetreballiesjaumeiieljust.Entitat;

/**
 * Created by lpc on 27/04/17.
 */

public class OfertesTreball {

    private String Nom;
    private String Poblacio;
    private String Email;
    private String Requerirements;
    private String DataNotificacio;
    private String Descripcio;

    public OfertesTreball() {
    }

    public OfertesTreball(String nom, String poblacio, String email, String requerirements, String dataNotificacio, String descripcio) {
        Nom = nom;
        Poblacio = poblacio;
        Email = email;
        Requerirements = requerirements;
        DataNotificacio = dataNotificacio;
        Descripcio = descripcio;
    }


    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPoblacio() {
        return Poblacio;
    }

    public void setPoblacio(String poblacio) {
        Poblacio = poblacio;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRequerirements() {
        return Requerirements;
    }

    public void setRequerirements(String requerirements) {
        Requerirements = requerirements;
    }

    public String getDataNotificacio() {
        return DataNotificacio;
    }

    public void setDataNotificacio(String dataNotificacio) {
        DataNotificacio = dataNotificacio;
    }

    public String getDescripcio() {
        return Descripcio;
    }

    public void setDescripcio(String descripcio) {
        Descripcio = descripcio;
    }
}
