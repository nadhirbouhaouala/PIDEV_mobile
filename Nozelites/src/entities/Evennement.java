/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author syrine
 */
public class Evennement {
    private int idc;
    private int idE;
    private String nom;
    private String lieu;
    private String date;
    private String heure;
    private String desciption;
    private String siteWeb;
    private int NbParticipant;
    private int NbPlace;
    private String image;
    private int etat;

    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    

    public Evennement(int idE, String nom, String lieu, String date, String heure, String desciption, String siteWeb, int NbParticipant, int NbPlace) {
        this.idE = idE;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.desciption = desciption;
        this.siteWeb = siteWeb;
        this.NbParticipant = NbParticipant;
        this.NbPlace = NbPlace;
        this.etat=0;
    }
    
    public Evennement(String nom, String lieu) {
        this.nom = nom;
        this.lieu = lieu;
    }
    

    public Evennement(String nom, String lieu, String date, String heure, String desciption, String siteWeb, int NbParticipant, int NbPlace) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.desciption = desciption;
        this.siteWeb = siteWeb;
        this.NbParticipant = NbParticipant;
        this.NbPlace = NbPlace;
         
    }

    public Evennement(int idc, int idE, String nom, String lieu, String date, String heure, String desciption, String siteWeb, int NbParticipant, int NbPlace, String image) {
        this.idc = idc;
        this.idE = idE;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.desciption = desciption;
        this.siteWeb = siteWeb;
        this.NbParticipant = NbParticipant;
        this.NbPlace = NbPlace;
        this.image = image;
           
    }

    public Evennement(int idc, int idE, String nom, String lieu, String date, String heure, String desciption, String siteWeb, int NbParticipant, int NbPlace) {
        this.idc = idc;
        this.idE = idE;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.desciption = desciption;
        this.siteWeb = siteWeb;
        this.NbParticipant = NbParticipant;
        this.NbPlace = NbPlace;
    }

    public Evennement(int idc, String nom, String lieu, String date, String heure, String desciption, String siteWeb, int NbParticipant, int NbPlace, String image) {
        this.idc = idc;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.desciption = desciption;
        this.siteWeb = siteWeb;
        this.NbParticipant = NbParticipant;
        this.NbPlace = NbPlace;
        this.image = image;
        
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

   
    public int getNbParticipant() {
        return NbParticipant;
    }

    public void setNbParticipant(int NbParticipant) {
        this.NbParticipant = NbParticipant;
    }

    public int getNbPlace() {
        return NbPlace;
    }

    public void setNbPlace(int NbPlace) {
        this.NbPlace = NbPlace;
    }


   

    public Evennement() {
      
    }

    @Override
    public String toString() {
        return "Evennement{" + "idc=" + idc + ", idE=" + idE + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", heure=" + heure + ", desciption=" + desciption + ", siteWeb=" + siteWeb + ", NbParticipant=" + NbParticipant + ", NbPlace=" + NbPlace + ", image=" + image + '}';
    }

   

    
   

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }
    
    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

   

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
    
    
    
}
