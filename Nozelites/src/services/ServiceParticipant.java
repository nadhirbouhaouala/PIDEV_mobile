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
import entities.ListeParticipant;
import entities.Evennement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author syrine
 */
public class ServiceParticipant {
    
      public ArrayList<ListeParticipant> listep;
      public ArrayList<ListeParticipant> listepr;
      private ConnectionRequest request;
      private boolean responseResult;
      
      public ServiceParticipant() {
         request = DataSource.getInstance().getRequest();
    }
     public ArrayList<ListeParticipant> parsepart(String jsonText){
        try {
            listep =new ArrayList<>();
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
                ListeParticipant g=new ListeParticipant();
           
                
                float etat= Float.parseFloat(obj.get("etatp").toString());
                //float idc= Float.parseFloat(obj.get("idm").toString());
                g.setIdm((int)Float.parseFloat(obj.get("idm").toString().substring(obj.get("idm").toString().indexOf("=")+1, obj.get("idm").toString().indexOf(",")-2)));
                g.setIde((int)Float.parseFloat(obj.get("ide").toString().substring(obj.get("ide").toString().indexOf("=")+1, obj.get("ide").toString().indexOf(",")-2)));
              
                //g.setIde((int)ide);
               // g.setIdm((int)idc);
                g.setEtatp((int)etat);
                //Ajouter la tâche extraite de la réponse Json à la liste
                listep.add(g);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listep;
    }
     public ArrayList<ListeParticipant> Afficherpart(){
        String url = Statics.BASE_URL+"/eventspart/jsonAll";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listep= parsepart(new String(request.getResponseData()));// recuperer les donnees
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return listep;
    }
      public  ArrayList<ListeParticipant>  chercherp(int id,int idc)
    {
       ArrayList<ListeParticipant> ev  =new ArrayList<>();
        ArrayList<ListeParticipant> evt = this.Afficherpart();
        for(ListeParticipant g: evt)
            if(g.getIde()==id)
                if(g.getIdm()==idc)
                     ev.add(g);
        return ev;
    }
}
