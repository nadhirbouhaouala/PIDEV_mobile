/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author salon2
 */
public class Commentaire_entities {
   private int id_commentaire;
   private int id_membre;
   private int id_publication;
   private String commentaire;

    public Commentaire_entities(int id_commentaire, int id_membre, int id_publication, String commentaire) {
        this.id_commentaire = id_commentaire;
        this.id_membre = id_membre;
        this.id_publication = id_publication;
        this.commentaire = commentaire;
    }
   
   public Commentaire_entities(){
       
   }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public int getId_membre() {
        return id_membre;
    }

    public int getId_publication() {
        return id_publication;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public void setCommentaire(String Commentaire) {
        this.commentaire = Commentaire;
    }
   
    
}
