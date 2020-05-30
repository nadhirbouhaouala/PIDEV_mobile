/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author syrine
 */
public class ListeParticipant {
     private int idp;
     private int ide;
     private int idm;
     private int etatp;

    public ListeParticipant(int idp, int ide, int idm, int etatp) {
        this.idp = idp;
        this.ide = ide;
        this.idm = idm;
        this.etatp = etatp;
    }

    public ListeParticipant(int ide, int idm, int etatp) {
        this.ide = ide;
        this.idm = idm;
        this.etatp = etatp;
    }

    public ListeParticipant() {
    }
    

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public int getEtatp() {
        return etatp;
    }

    public void setEtatp(int etatp) {
        this.etatp = etatp;
    }

    @Override
    public String toString() {
        return "ListeParticipant{" + "idp=" + idp + ", ide=" + ide + ", idm=" + idm + ", etatp=" + etatp + '}';
    }
     
}
