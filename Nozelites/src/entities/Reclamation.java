/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author KHAIRI
 */
public class Reclamation {

    private String description, selecteur;
    private int idRecl, id_emeteur, id_cible;
    //private boolean etat;
    private int etat;

    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sdf.format(dt);

    public Reclamation() {
    }

    public Reclamation( int id_emeteur, int id_cible, String description,  String selecteur) {
        this.description = description;
        
       // this.idRecl = idRecl;
        this.id_emeteur = id_emeteur;
        this.id_cible = id_cible;
        this.selecteur = selecteur;
        
       
    }

    public String getDescription() {
        return description;
    }

    public int getEtat() {
        return etat;
    }

    public int getIdRecl() {
        return idRecl;
    }

    public int getId_emeteur() {
        return id_emeteur;
    }

    public int getId_cible() {
        return id_cible;
    }

    public String getSelecteur() {
        return selecteur;
    }

    public Date getDt() {
        return dt;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public String getDate() {
        return date;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setIdRecl(int idRecl) {
        this.idRecl = idRecl;
    }

    public void setId_emeteur(int id_emeteur) {
        this.id_emeteur = id_emeteur;
    }

    public void setId_cible(int id_cible) {
        this.id_cible = id_cible;
    }

    public void setSelecteur(String selecteur) {
        this.selecteur = selecteur;
    }

    

    @Override
    public String toString() {
        return "Reclamation{" + "description=" + description + ", selecteur=" + selecteur + ", idRecl=" + idRecl + ", id_emeteur=" + id_emeteur + ", id_cible=" + id_cible + ", etat=" + etat + ", dt=" + dt + ", sdf=" + sdf + ", date=" + date + '}';
    }
   

}
