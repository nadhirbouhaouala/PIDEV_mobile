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
import entities.Evaluation;
import entities.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author KHAIRI
 */
public class ServiceEvaluation {
    
     public ArrayList<Evaluation> groupes;
    
    private ConnectionRequest request;
    private boolean responseResult;
    private int lastid;
    
    //constructeur 1
    public ServiceEvaluation() {
         request = DataSource.getInstance().getRequest();
    }
    

    public boolean ajouter(Evaluation g) {
        String url = Statics.BASE_URL + "/reclamation/reclamation/jsonadd/" + g.getId_r()+ "/" + g.getRating(); //création de l'URL
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

    public ArrayList<Evaluation> parseGroupes(String jsonText){
        try {
            groupes =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> groupesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)groupesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               Evaluation g = new Evaluation();
                float id = Float.parseFloat(obj.get("id").toString());
                g.setId((int)id);
                 g.setId_r((int)Float.parseFloat(obj.get("idR").toString().substring(obj.get("idR").toString().indexOf("=")+1, obj.get("idR").toString().indexOf(",")-2)));
               // g.setId_cible((int)Float.parseFloat(obj.get("id_cible").toString().substring(obj.get("id_cible").toString().indexOf("=")+1, obj.get("id_cible").toString().indexOf(",")-2)));
               float not = Float.parseFloat(obj.get("note").toString());
                g.setNote((int)not);
           
               
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                groupes.add(g);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return groupes;
    }
    
    
    
    
}
