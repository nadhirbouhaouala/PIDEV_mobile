/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Evennement;
import entities.ListeParticipant;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceEvenement;
import services.ServiceParticipant;
import utils.ScrollBar;
import utils.Session;



/**
 *
 * @author syrine
 */
public class EvenementAfficherInterface extends com.codename1.ui.Form{
    private Resources theme;
    private int id_user_actif = Session.id_Session;
    
    public EvenementAfficherInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        System.out.println(Session.id_Session);
        setTitle("Mes Evenements");
      Button b=new Button();
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
         final DefaultListModel<String> list = new DefaultListModel<>();
          ArrayList<Evennement> liste = new ServiceEvenement().Afficher();
        for(Evennement g: liste)
                 { 
                    
                    list.addItem(g.getNom());
                              


                 }
        
         AutoCompleteTextField Textbarre = new AutoCompleteTextField(list);
         Textbarre.setMinimumElementsShownInPopup(5);
        
          Button chercher = new Button("chercher");
        //Tabs : toolbar
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 =new Container(BoxLayout.y());//ajouter graphiquement un GUI element
      
        Container cnt2 =new Container(BoxLayout.y());//ajouter graphiquement un GUI element
         Component scrollableCnt = makeScrollable(cnt1);
         Component scrollableCnt2 = makeScrollable(cnt2);
         
        tab.addTab("Les Evenements", scrollableCnt);
        tab.addTab("Mes Evenements", scrollableCnt2);
       /* hi.getToolbar().addCommandToSideMenu("page 1", null, e->{
      page1=new Form("page 1");
        page1.getToolbar().addCommandToLeftBar("back",theme.getImage("back-command.png"),ev->{hi.show();});
      
      page1.show();});*/
       this.getToolbar().addMaterialCommandToSideMenu("Mon Profil", FontImage.MATERIAL_HOME, e->{
            new MembreAfficherInterface().show();
            
       });
         this.getToolbar().addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_EVENT, e->{
            new EvenementAfficherInterface().show();
            
       });
          
        //creer un groupe 
        Button ajouterBtn = new Button("Créer un evenement");
        
        chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new EvenementChercherInterface(Textbarre.getText()).show();
            }
        });
        
        
        ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new EvenementAjouterInterface().show();
            }
        });
        this.add(ajouterBtn).add(tab);
       
        ArrayList<Evennement> list_e = new ServiceEvenement().Afficher();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
         cnt1.add(Textbarre).add(chercher);
        cnt1.setLayout(BoxLayout.y());
        for(Evennement gmi :  list_e )
           if(gmi.getEtat()==1 )
                         {
                            cnt1.add(addItemGroupe(gmi));
                         }
         cnt2.setLayout(BoxLayout.y());
        for(Evennement gmi :  list_e )
            if(gmi.getEtat()==1 )
                if(gmi.getIdc()==id_user_actif)
                            cnt2.add(addItemEvent(gmi));
       
       
        
       
        
    }
     public Container addItemGroupe(Evennement g){//pour remplir la liste
         
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
       // Container cn3=new Container(BoxLayout.x());
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
        btnacc.setMaterialIcon(FontImage.MATERIAL_ADD_CIRCLE);
        Button btnreff=new Button("disjoindre");
        btnreff.setMaterialIcon(FontImage.MATERIAL_REMOVE_CIRCLE);
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
          
          
          
             Button reclamer=new Button("réclamer");
             reclamer.setMaterialIcon(FontImage.MATERIAL_BLOCK);
             reclamer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                }
        });
             
             
         cn2.add(reclamer);
         
         
        cn1.add(BorderLayout.WEST,v);
        cn1.add(BorderLayout.CENTER,cn2);
        //cn1.add(BorderLayout.SOUTH,cn3);
        } catch (IOException ex) {
            System.out.println("errrrr");
        }
  
        return cn1;
                
    }
      public Container addItemEvent(Evennement gm){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
             Image img;
                        ImageViewer v ;
                   System.out.println(gm.getImage().charAt(0));     
        try {
          char c= gm.getImage().charAt(0);
           String s=String.valueOf(c);
            if(s.equals("C"))
          {
              img = Image.createImage("file:/" + gm.getImage()).fill(550, 450);
          }
          else{
            img = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/" + gm.getImage()).fill(550, 450);
          }
                        v = new ImageViewer(img);
        Label lab=new Label(gm.getNom());
        Label lab2=new Label(gm.getDesciption());
        
        Button btnSupp=new Button();
        btnSupp.setMaterialIcon(FontImage.MATERIAL_DELETE);
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ServiceEvenement().supprimer(gm);
                new EvenementAfficherInterface().show();
            }
        });
        Button btnmodifier=new Button();
        btnmodifier.setMaterialIcon(FontImage.MATERIAL_EDIT);
       btnmodifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                new MesEvenementsInterface(gm).show();
            }
        });
        Container cn3=new Container(BoxLayout.x());
        cn3.add(btnSupp).add(btnmodifier);
        
//        ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
        cn2.add(lab).add(lab2).add(cn3);
        cn1.add(BorderLayout.WEST,v );
        cn1.add(BorderLayout.CENTER,cn2);
          } catch (IOException ex) {
            System.out.println("errrrr");
        }
        return cn1;
                
    }
  public static Component makeScrollable(final Component scrollable) {
        if(!Display.getInstance().isDesktop()) {
            return scrollable;
        }
        if (!(scrollable instanceof Container)) {
            return scrollable;
        }
        
        ScrollBar scroll = new ScrollBar((Container)scrollable, ScrollBar.Y_AXIS);
        Container sc = BorderLayout.center(scrollable).
                add(BorderLayout.EAST, scroll);
        $(sc).selectAllStyles().setBgColor(0xffffff).setBgTransparency(255);
        return sc;
    }
    
}
