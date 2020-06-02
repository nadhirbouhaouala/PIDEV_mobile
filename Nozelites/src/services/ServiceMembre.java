/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;
import utils.DataSource;

/**
 *
 * @author nadhir
 */
public class ServiceMembre {
    
    public ArrayList<Membre> membres;
    
    private ConnectionRequest request;
    private boolean responseResult;
    
    //constructeur 1
    public ServiceMembre() {
         request = DataSource.getInstance().getRequest();
    }
    
    public ArrayList<Membre> parseMembres(String jsonText){
        try {
            membres =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> membresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)membresListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Membre membre = new Membre();
                float id = Float.parseFloat(obj.get("idusr").toString());
                membre.setUsrId((int)id);
                membre.setNom(obj.get("nom").toString());
                membre.setPrenom(obj.get("prenom").toString());
                membre.setTel((int)Float.parseFloat(obj.get("tel").toString()));
                membre.setMail(obj.get("mail").toString());
                membre.setLogin(obj.get("login").toString());
                membre.setMdp(obj.get("mdp").toString());
                membre.setAge((int)Float.parseFloat(obj.get("age").toString()));
                if(obj.get("formation")!=null)membre.setFormation(obj.get("formation").toString());
                if(obj.get("experience")!=null)membre.setExp(obj.get("experience").toString());
                membre.setType((int)Float.parseFloat(obj.get("type").toString()));
                membre.setImage(obj.get("image").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                membres.add(membre);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return membres;
    }
    
    public ArrayList<Membre> Afficher(){
        String url = Statics.BASE_URL+"/user/membres/jsonAll";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                membres = parseMembres(new String(request.getResponseData()));// recuperer les donnees
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return membres;
    }
    
    public boolean ajouter(Membre m) {
        String url = Statics.BASE_URL + "/user/membres/jsonAdd/" + m.getNom() + "/" + m.getPrenom() +"/"+m.getAge()+ "/" + m.getTel()+"/"+ m.getLogin() + "/" + m.getMdp() + "/" + m.getMail()+"/"+m.getImage(); //création de l'URL
        request.setUrl(url);// Insertion de l'URL de notre demande de connexion
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
    
    public Membre AfficherProfil(int id){
        String url = Statics.BASE_URL+"/user/membres/jsonShow/"+id;
        ArrayList<Membre> list = this.Afficher();
        for(Membre m : list){
            if(m.getUsrId() == id){
                return m;
            }
        }
        return null;
    }
    
    public boolean modifier(Membre m)
    {
        
        String url = Statics.BASE_URL + "/user/membres/jsonEdit/"+m.getUsrId()+"/" +m.getNom()+"/" +m.getPrenom() +"/"+m.getAge()+"/"+m.getMail()+"/"+m.getTel();
        request.setUrl(url);// Insertion de l'URL de notre demande de connexion
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; 
                request.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
    
}
