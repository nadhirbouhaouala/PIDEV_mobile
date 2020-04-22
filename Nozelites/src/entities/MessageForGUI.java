/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Wael Berrachid
 */
public class MessageForGUI {
    private int id;
    private String objet;
    private String texte;
    private String nom,prenom;
    private String date;

    public MessageForGUI(int id, String objet, String texte, String nom, String prenom, String date) {
        this.id = id;
        this.objet = objet;
        this.texte = texte;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getObjet() {
        return objet;
    }

    public String getTexte() {
        return texte;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
