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
public class chasseurTalent extends Utilisateur{
    private String entreprise;
    
    public chasseurTalent(){
        
    }
    
    public chasseurTalent(String nom, String prenom, String mail, String login, String mdp, int age, int tel, int id,String entreprise,String image){
        super(nom,prenom, mail,login,mdp,age,tel,id,image);
        this.entreprise = entreprise ;
    }
    
    public chasseurTalent(String nom, String prenom, String mail, String login, String mdp, int age, int tel,String entreprise,String image){
        super(nom,prenom, mail,login,mdp,age,tel,image);
        this.entreprise = entreprise ;
    }

    @Override
    public int getUsrId() {
        return super.getUsrId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTel() {
        return super.getTel(); //To change body of generated methods, choose Tools | Templates.
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
    public String getImage(){
        return super.getImage();
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

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
}
