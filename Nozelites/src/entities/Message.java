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

    public Message(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    private String objet;
    private String texte;
    private int idEmetteur,idRecepteur;

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
