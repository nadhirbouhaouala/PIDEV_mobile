/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author salon2
 */
public class Favoris_entities {
    private int id_fav,id_pub,id_membre;
    public Favoris_entities(){
        
    }

    public Favoris_entities(int id_fav, int id_pub, int id_membre) {
        this.id_fav = id_fav;
        this.id_pub = id_pub;
        this.id_membre = id_membre;
    }

    public int getId_fav() {
        return id_fav;
    }

    public int getId_pub() {
        return id_pub;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_fav(int id_fav) {
        this.id_fav = id_fav;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }
    
}
