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
import entities.Evennement;
import entities.ListeParticipant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.DataSource;
import utils.Statics;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

/**
 *
 * @author syrine
 */
public class ServiceEvenement {
    
    public ArrayList<Evennement> evennements;
     public ArrayList<ListeParticipant> listep;
      public ArrayList<ListeParticipant> listepr;
    
    private ConnectionRequest request;
    private boolean responseResult;
    private int lastid;
    
    //constructeur 1
    public ServiceEvenement() {
         request = DataSource.getInstance().getRequest();
    }
    

    public boolean ajouter(Evennement g) {
       
        String url = Statics.BASE_URL + "/events/jsonadd/" 
             + g.getIdc()+ "/" + g.getNom()+"/"+g.getLieu()+"/"+g.getDate()+"/"
             +g.getHeure()+"/"+g.getDesciption()+"/"+g.getSiteWeb()+"/"+g.getNbPlace()+"/"+g.getImage(); //création de l'URL                                                       
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
      
      public ArrayList<Evennement> parseEvents(String jsonText){
        try {
            evennements =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Evennement g = new Evennement();
                float idE = Float.parseFloat(obj.get("ide").toString());
             //   float idc = Float.parseFloat(obj.get("idc").toString());
                float etat= Float.parseFloat(obj.get("etat").toString());
                float nbp= Float.parseFloat(obj.get("nbplace").toString());
                float nbpr= Float.parseFloat(obj.get("nbparticipant").toString());
                g.setIdE((int)idE);
                g.setNom(obj.get("nom").toString());
               // g.setIdc((int)idc);
                g.setIdc((int)Float.parseFloat(obj.get("idc").toString().substring(obj.get("idc").toString().indexOf("=")+1, obj.get("idc").toString().indexOf(",")-2)));
                g.setLieu(obj.get("lieu").toString());
                g.setDate(obj.get("date").toString());
                g.setHeure(obj.get("heure").toString());
                g.setSiteWeb(obj.get("siteweb").toString());
                g.setDesciption(obj.get("description").toString());
                g.setEtat((int)etat);
                g.setImage(obj.get("image").toString());
                g.setNbPlace((int)nbp);
                g.setNbParticipant((int)nbpr);
                //Ajouter la tâche extraite de la réponse Json à la liste
                evennements.add(g);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return evennements;
    }
     public ArrayList<Evennement> Afficher(){
        String url = Statics.BASE_URL+"/events/jsonAll";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evennements= parseEvents(new String(request.getResponseData()));// recuperer les donnees
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return evennements;
    }
     
      public boolean supprimer(Evennement gm)
    {
        String url = Statics.BASE_URL + "/events/jsondelete/" + gm.getIdE(); 
        request.setUrl(url);// Insertion de l'URL de notre demande de connexion
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
        public boolean modifier(Evennement g)
    {
        String url = Statics.BASE_URL + "/events/jsonedit/" 
                + g.getIdE()+ "/" + g.getNom()+"/"+g.getLieu()+"/"+g.getDate()+"/"
             +g.getHeure()+"/"+g.getDesciption()+"/"+g.getSiteWeb()+"/"+g.getNbPlace()+"/"+g.getImage();  
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
    public Evennement chercher(int id)
    {
        Evennement gr = null;
        ArrayList<Evennement> evt = this.Afficher();
        for(Evennement g: evt)
            if(g.getIdE()==id)
               gr = g;
        return gr;
    }

  
    
    
    public  boolean Rejoindre(int idc,int ide)
    {
         String url = Statics.BASE_URL + "/eventspart/jsonadd/" 
             + ide+ "/" + idc; //création de l'URL                                                       
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
    public boolean disjoindre(int idc,int ide)
    {
        String url = Statics.BASE_URL + "/eventspart/jsondelete/"  + ide+ "/" + idc; 
        request.setUrl(url);// Insertion de l'URL de notre demande de connexion
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
       
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
    
    
     
}
