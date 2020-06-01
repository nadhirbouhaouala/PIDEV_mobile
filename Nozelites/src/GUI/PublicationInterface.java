/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
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
import entities.Publication_entities;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import java.util.ArrayList;
import com.codename1.ui.RadioButton;
import services.ServicePublication;
import entities.Favoris_entities;
import utils.ScrollBar;
import static com.codename1.ui.ComponentSelector.$;
import services.ServiceFavoris;
import utils.Session;

/**
 *
 * @author salon2
 */
public class PublicationInterface extends com.codename1.ui.Form  {
    
    private Resources theme;
    private int id_user_actif = 9;
    Publication_entities pu = null;
    String searchString ; 
    ImageViewer iv = new ImageViewer();
    private Image img;
    String  param ; 
    public PublicationInterface()  {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
       setTitle("Mes Publications");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
         
        Container cnt2 = new Container(BoxLayout.y());//ajouter graphiquement un GUI element
        Component scrollableCnt = makeScrollable(cnt1);
         Component scrollableCnt2 = makeScrollable(cnt2);
      
        tab.addTab("Mon Forum", scrollableCnt);
        //tab.addTab("Mes Publications", scrollableCnt2);
        
      
        
       // hi.add(scrollableCnt);
        
        
     
        
        //creer un groupe 
    final DefaultListModel<String> list = new DefaultListModel<>();
    ArrayList<Publication_entities> liste = new ServicePublication().Afficher();
        for(Publication_entities g: liste)
                 { 
                    
                    list.addItem(g.getTitre());
                              


                 }
        
  
        AutoCompleteTextField Textbarre = new AutoCompleteTextField(list);
        Textbarre.setMinimumElementsShownInPopup(5);
         
  

        
        Button ajouterBtn = new Button("Créer une Publication");
         Button chercher = new Button("chercher");
        
         chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    // Media m =(Media) MediaManager.getAudioBuffer("a.mp3");
                    Media m =  MediaManager.createMedia("sound-effects-button.mp3", focusScrolling);
                    m.play();
                } catch (IOException ex) {
                }
               
                  new PublicationChercherInterface(Textbarre.getText()).show();
               
                  
                    

                
      
                
                
             
            }
        });
        
           ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 
                new PublicationAjouterInterface().show();
            }
        });
       
        this.add(tab).add(ajouterBtn);
        
     ArrayList<Publication_entities> list_e = new ServicePublication().Afficher();
                         

        cnt1.setLayout(BoxLayout.y());

        cnt1.add(Textbarre).add(chercher);
        for(Publication_entities gmi : list_e )
        
                      //cnt1.add(addItemGroupe(gmi));
        cnt1.add(addItemGroupe(gmi));
             
                            
                                           
       
       
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
              img = Image.createImage("file:/" + g.getImage()).fill(1200, 1000);
          }
          else{
            img = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/" + g.getImage()).fill(1200, 1000);
          }
                        v = new ImageViewer(img);
        Label lab=new Label("Titre:       "+ g.getTitre());
        Label lab2=new Label("Description:       " +g.getDescription());
      
        
       // System.out.println("hedhouma el nwamr" + g.getId_publicateur());
          Button Reclamer = new Button();
        Reclamer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
       
                
            }
        });
         Button Like = new Button();
           Like.setMaterialIcon(FontImage.MATERIAL_FAVORITE);
        Button Dislike = new Button();
        Dislike.setMaterialIcon(FontImage.MATERIAL_DANGEROUS);
           // cacher(Dislike);
        Dislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //  cacher(Dislike);
               // apparaitre(Like);
                  ArrayList<Publication_entities> list_gm = new ServicePublication().Afficher();
                ServicePublication sgm = new ServicePublication();
                for(Publication_entities gmi : list_gm)
                    if(gmi.getId_groupe()==g.getId())
                        sgm.supprimer(gmi);
                new ServicePublication().dislike(g);
                new PublicationInterface().show();
                System.out.println("Dislike" + g.getNb_jaime());
            }
        });
       
      
        Like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cacher(Like);
               // apparaitre(Dislike);
                ArrayList<Publication_entities> list_gm = new ServicePublication().Afficher();
                ServicePublication sgm = new ServicePublication();
                for(Publication_entities gmi : list_gm)
                    if(gmi.getId_groupe()==g.getId())
                        sgm.supprimer(gmi);
                new ServicePublication().jaime(g);
                new PublicationInterface().show();
             //   System.out.println("j'aime" + g.getNb_jaime());
               // cn3.add(Dislike);
                
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
      if (g.getId_publicateur() == Session.id_Session)
      {
        cn3.add(supprimerBtn).add(modifierBtn);
      }
      
     // System.out.println("like"+Like.isSelected());
          //  System.out.println("dislike "+Dislike.getState());

        cn3.add(Like).add(Dislike);

        
        cn1.add(BorderLayout.CENTER,v );
        cn1.add(BorderLayout.NORTH,cn2);
        cn1.add(BorderLayout.SOUTH,cn3);
        

        
      //  cn1.add(modifierBtn);
        
           } catch (IOException ex) {
            System.out.println("errrrr");
        }
  
  
        return cn1;
                
    }
       private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
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
          private void cacher(Button B) {
              B.setHidden(true);
          }
           private void apparaitre(Button B) {
              B.setHidden(false);
          } 
   
 

 
}
