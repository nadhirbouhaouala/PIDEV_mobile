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
public class PortfolioForGUI {
    
     private String titre;
    private String description ;
    private int id;
    private String lien;

    public PortfolioForGUI(int id,String titre,String description, String lien) {
        this.titre = titre;
        this.description = description;
        this.id = id;
        this.lien = lien;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getLien() {
        return lien;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
    
    
    
    
}
