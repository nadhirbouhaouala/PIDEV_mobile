/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Publication_entities;
import java.io.IOException;
import java.util.ArrayList;
import services.ServicePublication;

/**
 *
 * @author salon2
 */
public class PublicationChercherInterface extends com.codename1.ui.Form{
    private Resources theme;
    private int id_user_actif = 9;
    Publication_entities pu = null;

    public PublicationChercherInterface(String s) {
        
         Tabs tab = new Tabs();
           setLayout(BoxLayout.y());
                
             Container cnt2 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
        tab.addTab("Pub", cnt2);
        
        add(tab);
         ArrayList<Publication_entities> listedespub = new ServicePublication().Afficher();
               
                  for(Publication_entities p: listedespub)
                 { 
                     if(p.getTitre().equals(s))
                     {
                         pu =p;
                      //   cnt1.setLayout(BoxLayout.y());
                       
                 //     cnt1.add(addItemGroupe(pu));
                         
                     }
                    
                 }
                  System.out.println("voila le contenu du text barre"+pu);
                 cnt2.add(addItemGroupe(pu));
                 this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new PublicationInterface().show();
        });
    }
     public Container addItemGroupe(Publication_entities g){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3=new Container(BoxLayout.x());
       // Component scrollableCnt = makeScrollable(cn1);
        Container cnt = new Container(BoxLayout.y());

       
             Image img;
                        ImageViewer v ;
                   
        try {
           char c= g.getImage().charAt(0);
           String s=String.valueOf(c);
          if(s.equals("C"))
          {
              img = Image.createImage("file:/" + g.getImage()).fill(555, 500);
          }
          else{
            img = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/" + g.getImage()).fill(1200, 600);
          }
                        v = new ImageViewer(img);
        Label lab=new Label("Titre:       "+ g.getTitre());
        Label lab2=new Label("Description:       " +g.getDescription());
      
        
        System.out.println("hedhouma el nwamr" + g.getId_publicateur());
        Button Like = new Button("J'aime");
        Like.setMaterialIcon(FontImage.MATERIAL_FAVORITE);
        Like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  ArrayList<Publication_entities> list_gm = new ServicePublication().Afficher();
                ServicePublication sgm = new ServicePublication();
                for(Publication_entities gmi : list_gm)
                    if(gmi.getId_groupe()==g.getId())
                        sgm.supprimer(gmi);
                new ServicePublication().jaime(g);
                new PublicationInterface().show();
                System.out.println("j'aime" + g.getNb_jaime());
            }
        });
       
        
        Button supprimerBtn = new Button("supprimer");
        supprimerBtn.setMaterialIcon(FontImage.MATERIAL_DELETE);
        
       
        supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Publication_entities> list_gm = new ServicePublication().Afficher();
                ServicePublication sgm = new ServicePublication();
                for(Publication_entities gmi : list_gm)
                    if(gmi.getId_groupe()==g.getId())
                        sgm.supprimer(gmi);
                new ServicePublication().supprimer(g);
                new PublicationInterface().show();
            }
        });
         
        Button modifierBtn = new Button("modifier");
        modifierBtn.setMaterialIcon(FontImage.MATERIAL_UPDATE);

          modifierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                new PublicationModifierInterface(g).show();
            }
        });
      
        
        
        
//        ImageViewer imgv=new ImageViewer(theme.getImage("groupes.png"));
        cn2.add(lab).add(lab2);;
      if (g.getId_publicateur() == 10)
      {
        cn3.add(supprimerBtn).add(modifierBtn);
      }
        cn3.add(Like);
        
        cn1.add(BorderLayout.CENTER,v );
        cn1.add(BorderLayout.NORTH,cn2);
        cn1.add(BorderLayout.SOUTH,cn3);
        

        
      //  cn1.add(modifierBtn);
        
           } catch (IOException ex) {
            System.out.println("errrrr");
        }
  
  
        return cn1;
                
    }
}
