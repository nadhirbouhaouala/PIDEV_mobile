/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author KHAIRI
 */
public class ReclamationForMembre {
    
   private int id;
    private String nom,prenom,mail,nomc,prenomc,etat,selecteur,description,date;

    public ReclamationForMembre(int id, String nom, String prenom, String mail, String nomc, String prenomc,String description,String etat,String selecteur, String date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.etat = etat;
        this.selecteur = selecteur;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getNomc() {
        return nomc;
    }

    public String getPrenomc() {
        return prenomc;
    }

    public String getEtat() {
        return etat;
    }

    public String getSelecteur() {
        return selecteur;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
    
    
    
}
