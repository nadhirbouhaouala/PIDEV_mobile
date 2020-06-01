/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Evennement;
import entities.ListeParticipant;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceEvenement;
import services.ServiceParticipant;

/**
 *
 * @author syrine
 */
public class EvenementChercherInterface extends com.codename1.ui.Form{
     private Resources theme;
    private int id_user_actif = 9;
    Evennement pu=null;
    public EvenementChercherInterface(String s) {
       // Container cnt2 = new Container(BoxLayout.y());
           Tabs tab = new Tabs();
           setLayout(BoxLayout.y());
                  UIBuilder ui = new UIBuilder();
             Container cnt2 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
        tab.addTab("Evenement", cnt2);
        
        add(tab);
        ArrayList<Evennement> list_e = new ServiceEvenement().Afficher();
               
                  for(Evennement p: list_e)
                 { 
                     if(p.getNom().equals(s))
                     {
                         pu =p;
                      //   cnt1.setLayout(BoxLayout.y());
                       
                 //     cnt1.add(addItemGroupe(pu));
                         
                     }
                    
                 }
                  System.out.println("voila le contenu du text barre"+pu);
                 cnt2.add(addItemGroupe(pu));
                  
         this.getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new EvenementAfficherInterface().show();
        });
        
        
    }
    
      public Container addItemGroupe(Evennement g){//pour remplir la liste
         
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
         
                        Image img;
                        ImageViewer v ;
                   
        try {
           char c= g.getImage().charAt(0);
           String s=String.valueOf(c);
          if(s.equals("C"))
          {
              img = Image.createImage("file:/" + g.getImage()).fill(550, 450);
          }
          else{
            img = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/" + g.getImage()).fill(550, 450);
          }
                        v = new ImageViewer(img);
                          
        Label lab=new Label(g.getNom());
        Label lab2=new Label(g.getDesciption());
          cn2.add(lab).add(lab2);
          if(g.getIdc()!=id_user_actif){
        ServiceParticipant sv=new ServiceParticipant();
        Button btnacc=new Button("rejoindre");
        Button btnreff=new Button("disjoindre");
        Button att=new Button("envoyee");
            btnacc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new ServiceEvenement().Rejoindre(id_user_actif, g.getIdE());
                new EvenementAfficherInterface().show();
            }
        });
             btnreff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new ServiceEvenement().disjoindre(id_user_actif, g.getIdE());
                new EvenementAfficherInterface().show();
            }
        });

         if(sv.chercherp(g.getIdE(), id_user_actif).isEmpty())
        {
            cn2.add(btnacc);
        }
        else
         { 
             ArrayList<ListeParticipant> evt = sv.chercherp(g.getIdE(), id_user_actif);
              for(ListeParticipant pr: evt)
              {    if(pr.getEtatp()==0)
                        cn2.add(att);
              
                   if(pr.getEtatp()==1)
                        cn2.add(btnreff);
              }
         }
          }
         
        cn1.add(BorderLayout.WEST,v);
        cn1.add(BorderLayout.CENTER,cn2);
        } catch (IOException ex) {
            System.out.println("errrrr");
        }
  
        return cn1;
                
    }
}
