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
public class Membre extends Utilisateur{
    
    private String exp;
    private String formation ;
    int type;
    public Membre(String exp, String formation, String nom, String prenom, String mail, int usrId) {
        super(nom, prenom, mail, usrId);
        this.exp = exp;
        this.formation = formation;
    }

   

    public Membre(String nom, String prenom,String mail) {
        super(nom, prenom,mail);
    }
     public Membre(String nom, String prenom) {
        super(nom, prenom);
    }

    
    public Membre(){
        
    }
    public Membre(String nom, String prenom, String mail, String login, String mdp, String Exp, String Formation, int age, int tel,int type,String image) {
         
         
         super(nom,prenom, mail,login,mdp,age,tel,image);

        this.exp = Exp;
        this.formation = Formation;
        this.type = type;
         
     }
    public Membre(String nom, String prenom, String mail, String login, String mdp, String Exp, String Formation, int age, int tel, int id,int type,String image) {
         
         
         super(nom,prenom, mail,login,mdp,age,tel,id,image);

        this.exp = Exp;
        this.formation = Formation;
        this.type = type;
         
     }
    
    
    public int getType(){
        return type;
    }
    public String getExp() {
        return exp;
    }

    public String getFormation() {
        return formation;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    
   

    @Override
    public int getTel() {
        return super.getTel(); //To change body of generated methods, choose Tools | Templates.
    }
    @Override public String getImage(){
        return super.getImage();
    }
    @Override
    public int getAge() {
        return super.getAge(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMdp() {
        return super.getMdp(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLogin() {
        return super.getLogin(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMail() {
        return super.getMail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrenom() {
        return super.getPrenom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom() {
        return super.getNom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTel(int tel) {
        super.setTel(tel); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAge(int age) {
        super.setAge(age); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMdp(String mdp) {
        super.setMdp(mdp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMail(String mail) {
        super.setMail(mail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom); //To change body of generated methods, choose Tools | Templates.
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }
    
   
     
     
}
