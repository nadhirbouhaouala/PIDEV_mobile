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
public class GroupeMembre {
    
    private int id , id_groupe , id_membre, id_invite;
    private String etat;//invitation /standard // administrateur

    public GroupeMembre() {
    }

    public GroupeMembre(int id, int id_groupe, int id_membre, int invite, String etat) {
        this.id = id;
        this.id_groupe = id_groupe;
        this.id_membre = id_membre;
        this.id_invite = invite;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public int getId_invite() {
        return id_invite;
    }

    public void setId_invite(int id_invite) {
        this.id_invite = id_invite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "GroupeMembre{" + "id=" + id + ", id_groupe=" + id_groupe + ", id_membre=" + id_membre + ", id_invite=" + id_invite + ", etat=" + etat + '}';
    }
    
    
}
