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
import entities.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author Wael Berrachid
 */
public class ServiceMessage {
    
    public ArrayList<Message> messages;
    
    private ConnectionRequest request;
    private boolean responseResult;
    private int lastid;
    
    //constructeur 1
    public ServiceMessage() {
         request = DataSource.getInstance().getRequest();
    }
    
    public int lastid()
    {
        String url = Statics.BASE_URL+"/offre/message/jsonlastid";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lastid = ((int)Float.parseFloat(new String(request.getResponseData())));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return lastid ;                 
    }
    
    public int getIdByEmail(String email)
    {
        String url = Statics.BASE_URL+"/offre/message/jsonidbyemail/"+email;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lastid = ((int)Float.parseFloat(new String(request.getResponseData())));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return lastid ;                 
    }
    
    public ArrayList<Message> parseMessages(String jsonText){
        try {
            messages = new ArrayList<>();
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
            Map<String,Object> offresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)offresListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Message m = new Message();
                float id = Float.parseFloat(obj.get("idmessage").toString());
                m.setId((int)id);
                m.setObjet(obj.get("objet").toString());
                m.setTexte(obj.get("texte").toString());
                m.setDate(obj.get("date").toString());
                //System.out.println(obj.get("idEmeteur").toString().substring(obj.get("idEmeteur").toString().indexOf("nom")+4, obj.get("idEmeteur").toString().indexOf("prenom")-2));
                m.setNom(obj.get("idEmeteur").toString().substring(obj.get("idEmeteur").toString().indexOf("nom")+4, obj.get("idEmeteur").toString().indexOf("prenom")-2));
                m.setPrenom(obj.get("idEmeteur").toString().substring(obj.get("idEmeteur").toString().indexOf("prenom")+7, obj.get("idEmeteur").toString().indexOf("tel")-2));
                m.setNomD(obj.get("idDestinataire").toString().substring(obj.get("idDestinataire").toString().indexOf("nom")+4, obj.get("idDestinataire").toString().indexOf("prenom")-2));
                m.setPrenomD(obj.get("idDestinataire").toString().substring(obj.get("idDestinataire").toString().indexOf("prenom")+7, obj.get("idDestinataire").toString().indexOf("tel")-2));
                m.setIdEmetteur(((int)Float.parseFloat(obj.get("idEmeteur").toString().substring(obj.get("idEmeteur").toString().indexOf("=")+1, obj.get("idEmeteur").toString().indexOf(",")-2))));
                m.setIdRecepteur(((int)Float.parseFloat(obj.get("idDestinataire").toString().substring(obj.get("idDestinataire").toString().indexOf("=")+1, obj.get("idDestinataire").toString().indexOf(",")-2))));
                //Ajouter la tâche extraite de la réponse Json à la liste
                messages.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return messages;
    }
    
    public ArrayList<Message> Afficher(){
        String url = Statics.BASE_URL+"/offre/message/jsonAll";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                messages = parseMessages(new String(request.getResponseData()));// recuperer les donnees
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return messages;
    }
    
    public boolean ajouter(Message m) {
        String url = Statics.BASE_URL + "/offre/message/jsonadd/" + m.getId() + "/" + m.getIdEmetteur() +"/"+m.getIdRecepteur() +"/"+m.getObjet() +"/"+m.getTexte(); //création de l'URL
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
    
    public boolean supprimer(Message m) {
        String url = Statics.BASE_URL + "/offre/message/jsondelete" + m.getId();
        request.setUrl(url);// Insertion de l'URL de notre demande de connexion
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
    
}
