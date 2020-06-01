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
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Groupe;
import entities.Reclamation;
import java.util.ArrayList;
import services.ServiceReclamation;

/**
 *
 * @author KHAIRI
 */
public class ReclamationChercherInterface extends com.codename1.ui.Form{
   private Resources theme;
    private int id_user_actif = 9;
    Reclamation pu=null;
     public ReclamationChercherInterface(String s) {
    Tabs tab = new Tabs();
           setLayout(BoxLayout.y());
                  UIBuilder ui = new UIBuilder();
             Container cnt2 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
        tab.addTab("Reclamation", cnt2);
        
        add(tab);
        ArrayList<Reclamation> list_e = new ServiceReclamation().Afficher();
               
                  for(Reclamation p: list_e)
                 { 
                     if(p.getSelecteur().equals(s))
                     {
                         pu =p;
                      //   cnt1.setLayout(BoxLayout.y());
                       
                 //     cnt1.add(addItemGroupe(pu));
                         
                     }
                    
                 }
                
                    cnt2.add(addItemGroupe(pu));
                  
         this.getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new MembreReclamationAfficherInterface().show();
        });
        
        
    }
      public Container addItemGroupe(Reclamation g){//pour remplir la liste
         Groupe f =new Groupe();
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
         Container cn3=new Container(BoxLayout.x());
        Label lab=new Label(g.getDescription());
        Label lab2=new Label(g.getDate());
        Label lab3=new Label(g.getSelecteur());
        Label lab4=new Label(f.getTitre());
        Button supprimerBtn = new Button("Supprimer");
          supprimerBtn.setMaterialIcon(FontImage.MATERIAL_DELETE);
            supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Reclamation> list_gm = new ServiceReclamation().Afficher();
                ServiceReclamation sgm = new ServiceReclamation();
             
                  new ServiceReclamation().supprimer(g);
                new MembreReclamationAfficherInterface().show();
            }
            
        });
            
              Button modifierBtn = new Button("modifier");
             modifierBtn.setMaterialIcon(FontImage.MATERIAL_UPDATE);

          modifierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                new ReclamationModifierInterface(g).show();
            }
        });
           Button noteBtn = new Button("Note");
                     noteBtn.setMaterialIcon(FontImage.MATERIAL_RATE_REVIEW);

          if(g.getEtat()==1){
                   
           noteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new NoteInterface(g.getIdRecl()).show();
              
            }
        });
           // cn2.add(lab).add(lab2).add(lab3).add(supprimerBtn).add(modifierBtn).add(noteBtn);
          }
      
     
        
         ImageViewer imgv=new ImageViewer(theme.getImage("groupes.png"));
       cn3.add(supprimerBtn).add(modifierBtn).add(noteBtn);
         cn2.add(lab).add(lab2).add(lab3).add(lab4);
         
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        cn1.add(BorderLayout.SOUTH,cn3);

       // cn1.add(BorderLayout.NORTH,modifierBtn);
        
  
        return cn1;
        
          
                
     }
    
}
