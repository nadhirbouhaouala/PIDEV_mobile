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
public class Diplome {
    
    private int id_diplome;
    private String organisation;
    private String domaine;

    public Diplome(String organisation, String domaine) {
        this.organisation = organisation;
        this.domaine = domaine;
    }
    
    public Diplome(int id_diplome, String organisation, String domaine) {
        this.id_diplome = id_diplome;
        this.organisation = organisation;
        this.domaine = domaine;
    }
    public Diplome() {
    }
    
    public void setId_diplome(int id_diplome) {
        this.id_diplome = id_diplome;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    

    public int getId_diplome() {
        return id_diplome;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getDomaine() {
        return domaine;
    }

    
    
    
    
}
