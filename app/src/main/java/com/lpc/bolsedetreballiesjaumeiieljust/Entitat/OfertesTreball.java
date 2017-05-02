package com.lpc.bolsedetreballiesjaumeiieljust.Entitat;

/**
 * Created by lpc on 27/04/17.
 */

public class OfertesTreball {
    private int Codi;

    public int getCodi() {
        return Codi;
    }

    public void setCodi(int codi) {
        Codi = codi;
    }

    private String Nom;
    private String Poblacio;
    private String Email;
    private String Cicle;
    private String DataNotificacio;
    private String Descripcio;

    public OfertesTreball() {
    }

    public OfertesTreball(String nom, String poblacio, String email, String cicle, String descripcio) {
        Nom = nom;
        Poblacio = poblacio;
        Email = email;
        Cicle = cicle;
        Descripcio = descripcio;
    }

    public OfertesTreball(String nom, String poblacio, String email, String cicle, String dataNotificacio, String descripcio) {
        Nom = nom;
        Poblacio = poblacio;
        Email = email;
        Cicle = cicle;
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

    public String getCicle() {
        return Cicle;
    }

    public void setCicle(String cicle) {
        Cicle = cicle;
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
