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
public class Groupe {
    
    private int id;
    private String titre;
    private String Description;
    private int autorisation;

    public Groupe() {
    }

    public Groupe(int id, String titre, String Description, int autorisation) {
        this.id = id;
        this.titre = titre;
        this.Description = Description;
        this.autorisation = autorisation;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return Description;
    }

    public int getAutorisation() {
        return autorisation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setAutorisation(int autorisation) {
        this.autorisation = autorisation;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", titre=" + titre + ", Description=" + Description + ", autorisation=" + autorisation + '}';
    }
    
}
