/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author Nebil
 */
public class Portfolio {
   
    private String titre;
    private String description ;
    private int id_port;
    private int id_membre;
    private String lien;

    public Portfolio() {
    }

   
   

    public Portfolio(int id_membre,String titre, String description,String lien ) {
        this.titre = titre;
        this.id_membre=id_membre;
        this.description = description;
       // this.id_port = id_port;
        this.lien=lien;
        
    }

    public String getLien() {
        return lien;
    }

    public int getId_membre() {
        return id_membre;
    }
    

   
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getId_port() {
        return id_port;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

   

    public void setId_port(int id_port) {
        this.id_port = id_port;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    @Override
    public String toString() {
        return "Portfolio{" + "titre=" + titre + ", description=" + description + ", id_port=" + id_port + ", id_membre=" + id_membre + ", lien=" + lien + '}';
    }

    public Portfolio(String titre, String description, int id_port, int id_membre, String lien) {
        this.titre = titre;
        this.description = description;
        this.id_port = id_port;
        this.id_membre = id_membre;
        this.lien = lien;
    }
    
    
}
