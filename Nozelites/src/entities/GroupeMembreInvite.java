/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author nadhir
 */
public class GroupeMembreInvite {
    
    private int id_gmi ;
    private String nom , prenom , titre , description;

    public GroupeMembreInvite(int id_gmi, String nom, String prenom, String titre, String description) {
        this.id_gmi = id_gmi;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.description = description;
    }

    public int getId_gmi() {
        return id_gmi;
    }

    public void setId_gmi(int id_gmi) {
        this.id_gmi = id_gmi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GroupeMembreInvite{" + "id_gmi=" + id_gmi + ", nom=" + nom + ", prenom=" + prenom + ", titre=" + titre + ", description=" + description + '}';
    }
    
    
    
}
