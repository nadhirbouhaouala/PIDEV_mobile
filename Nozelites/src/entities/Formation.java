/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nebil
 */
public class Formation {
    private String titre;
    private int id_formation;
    public Formation() {
        
    }
    public Formation(String titre, int id_formation) {
        this.titre = titre;
        this.id_formation = id_formation;
    }

    public Formation(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }
    
    
}
