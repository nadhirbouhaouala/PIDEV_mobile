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
public class Utilisateur {
    protected String nom, prenom, mail, login, mdp ;
    
    protected int age, tel,usrId ;
    protected String image ;
    
    public Utilisateur() {
    }
public Utilisateur(String nom, String prenom, String mail, int usrId) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.usrId = usrId;
    }

    public Utilisateur(String nom, String prenom,String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail=mail;
    }
     public Utilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
 
    }
    public Utilisateur(String nom, String prenom, String mail, String login, String mdp, int age, int tel, int id,String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        this.image = image;
        
        this.age = age;
        this.tel = tel;
        this.usrId = id;
    }
    
    public Utilisateur(String nom, String prenom, String mail, String login, String mdp, int age, int tel,String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        this.image = image;
        
        this.age = age;
        this.tel = tel;
        
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

   

    

    public void setAge(int age) {
        this.age = age;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUsrId() {
        return usrId;
    }

   
    //---Getters ---
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    
    public String getImage(){
        return image;
    }

    public int getAge() {
        return age;
    }

    public int getTel() {
        return tel;
    }

    
    
    
}
