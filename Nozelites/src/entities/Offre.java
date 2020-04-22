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
public class Offre {
    private int id;
    private String type;
    private int idEmetteur,idRecepteur;
    private String entreprise,domaine,poste;
    private String requis,description;

    public Offre(String type, int idEmetteur, int idRecepteur, String entreprise, String domaine, String poste, String requis, String description) {
        this.type = type;
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
        this.entreprise = entreprise;
        this.domaine = domaine;
        this.poste = poste;
        this.requis = requis;
        this.description = description;
    }

    public Offre(int id, String type, int idEmetteur, int idRecepteur, String entreprise, String domaine, String poste, String requis, String description) {
        this.id = id;
        this.type = type;
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
        this.entreprise = entreprise;
        this.domaine = domaine;
        this.poste = poste;
        this.requis = requis;
        this.description = description;
    }

    public Offre(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdEmetteur(int idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public void setIdRecepteur(int idRecepteur) {
        this.idRecepteur = idRecepteur;
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

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getIdEmetteur() {
        return idEmetteur;
    }

    public int getIdRecepteur() {
        return idRecepteur;
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
    
}
