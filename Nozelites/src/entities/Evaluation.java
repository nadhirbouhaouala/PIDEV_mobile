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
public class Evaluation {
    
    private int id,id_r,rating;

    public Evaluation() {
    }

    public Evaluation(int id_r,int rating) {
        this.id = id;
        this.id_r = id_r;
       
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

  

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    
    
    
    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", id_r=" + id_r +  '}';
    }
    
    
    
}
