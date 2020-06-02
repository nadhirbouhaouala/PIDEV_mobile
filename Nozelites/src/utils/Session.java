/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Nebil
 */
public class Session {
    public static int id_Session;

    public static int getId_Session() {
        return id_Session;
    }

    public static void setId_Session(int id_Session) {
        Session.id_Session = id_Session;
    }

    public Session() {
    }
    
    public Session(int id){
        this.id_Session = id;
    }
    
}
