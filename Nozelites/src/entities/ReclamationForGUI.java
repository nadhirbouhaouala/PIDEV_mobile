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
public class ReclamationForGUI {
    
    private String nom, prenom, mail, description,etat,selecteur,date;
    private int id;

    public ReclamationForGUI(int id, String nom, String prenom, String mail, String description,String etat,String selecteur,String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.description = description;
        this.etat = etat;
        this.selecteur = selecteur;
        this.date = date;
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public String getSelecteur() {
        return selecteur;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setSelecteur(String selecteur) {
        this.selecteur = selecteur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReclamationForGUI{" + "nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", description=" + description + ", etat=" + etat + ", selecteur=" + selecteur + ", date=" + date + ", id=" + id + '}';
    }

    
}
