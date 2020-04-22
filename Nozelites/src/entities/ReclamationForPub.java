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
public class ReclamationForPub {
    
     private int id;
    private String nom,prenom,mail,titre,description,etat,selecteur,descriptionp,date;

    public ReclamationForPub(int id, String nom, String prenom, String mail, String titre, String description, String descriptionp, String etat, String selecteur, String date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.selecteur = selecteur;
        this.descriptionp = descriptionp;
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

    public String getDescriptionp() {
        return descriptionp;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ReclamationForPub{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", selecteur=" + selecteur + ", descriptionp=" + descriptionp + ", date=" + date + '}';
    }

    
    
    
    
    
}
