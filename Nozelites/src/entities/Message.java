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
public class Message {
    private int id;
    private String nomD, prenomD;

    
    
    public String getNomD() {
        return nomD;
    }

    public void setNomD(String nomD) {
        this.nomD = nomD;
    }

    public String getPrenomD() {
        return prenomD;
    }

    public void setPrenomD(String prenomD) {
        this.prenomD = prenomD;
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

    public void setIdEmetteur(int idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public void setIdRecepteur(int idRecepteur) {
        this.idRecepteur = idRecepteur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Message() {
        
    }
    
    public Message(int id) {
        this.id = id;
    }

    public Message(int id, String objet, String texte, int idEmetteur, int idRecepteur) {
        this.id = id;
        this.objet = objet;
        this.texte = texte;
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
    }

    
    
    public int getId() {
        return id;
    }
    private String objet;
    private String texte;
    private int idEmetteur,idRecepteur;
    private String date;
    private String nom,prenom;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    

    public String getDate() {
        return date;
    }

    public Message(String objet, String texte, int idEmetteur, int idRecepteur) {
        this.objet = objet;
        this.texte = texte;
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
    }

    public String getObjet() {
        return objet;
    }

    public String getTexte() {
        return texte;
    }

    public int getIdEmetteur() {
        return idEmetteur;
    }

    public int getIdRecepteur() {
        return idRecepteur;
    }
    
}
