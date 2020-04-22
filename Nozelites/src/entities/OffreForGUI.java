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
public class OffreForGUI {

    private int id;
    private String type;
    private String entreprise,domaine,poste;
    private String requis,description;
    private String date;
    private String etat;
    private String nom,prenom;

    public OffreForGUI(int id, String type, String entreprise, String domaine, String poste, String requis, String description, String date, String etat, String nom, String prenom) {
        this.id = id;
        this.type = type;
        this.entreprise = entreprise;
        this.domaine = domaine;
        this.poste = poste;
        this.requis = requis;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
    }

    public OffreForGUI(String type, String entreprise, String date, String etat) {
        this.type = type;
        this.entreprise = entreprise;
        this.date = date;
        this.etat = etat;
    }
    
    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getPoste() {
        return poste;
    }

    public String getRequis() {
        return requis;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setRequis(String requis) {
        this.requis = requis;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
    
}
