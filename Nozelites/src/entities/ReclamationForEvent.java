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
public class ReclamationForEvent {
    
    
    
     private int id;
    private String nom,prenom,mail,titre,description,etat,selecteur,descriptione,date,lieu;

    public ReclamationForEvent(int id, String nom, String prenom, String mail, String titre, String description,String descriptione, String etat, String selecteur,String date, String lieu) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.selecteur = selecteur;
        this.descriptione = descriptione;
        this.date = date;
        this.lieu = lieu;
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

    public String getTitre() {
        return titre;
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

    public String getDescriptione() {
        return descriptione;
    }

    public String getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    @Override
    public String toString() {
        return "ReclamationForEvent{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", selecteur=" + selecteur + ", descriptione=" + descriptione + ", date=" + date + ", lieu=" + lieu + '}';
    }
    
    
    
    
}
