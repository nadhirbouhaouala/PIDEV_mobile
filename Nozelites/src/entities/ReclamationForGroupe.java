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
public class ReclamationForGroupe {
    
    
    private int id;
    private String nom,prenom,mail,titre,description,etat,selecteur,descriptiong,date;

    public ReclamationForGroupe(int id, String nom, String prenom,String mail, String titre, String description, String descriptiong,String etat, String selecteur,String date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail=mail;
        this.titre = titre;
        this.description = description;
        this.etat=etat;
        this.selecteur = selecteur;
        this.descriptiong = descriptiong;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
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

    public String getEtat() {
        return etat;
    }


    public String getDescription() {
        return description;
    }

    public String getSelecteur() {
        return selecteur;
    }

    public String getDescriptiong() {
        return descriptiong;
    }

    @Override
    public String toString() {
        return "ReclamationForGroupe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", selecteur=" + selecteur + ", descriptiong=" + descriptiong + ", date=" + date + '}';
    }

  
   
    

   
    
    
    
    
}
