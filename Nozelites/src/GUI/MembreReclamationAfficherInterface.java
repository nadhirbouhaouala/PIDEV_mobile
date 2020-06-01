/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
import entities.Groupe;
import entities.GroupeMembre;
import entities.Membre;
import entities.Reclamation;
import java.util.ArrayList;
import static java.util.Collections.list;
import services.ServiceGroupe;
import services.ServiceGroupeMembre;
import services.ServiceMembre;
import services.ServiceReclamation;
import utils.Session;

/**
 *
 * @author KHAIRI
 */
public class MembreReclamationAfficherInterface extends com.codename1.ui.Form{
    ComboBox selecteur = new ComboBox();
     private Resources theme;
    private int id_user_actif = Session.getId_Session();
    private ArrayList<Membre> list_m = new ServiceMembre().Afficher();

    public MembreReclamationAfficherInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Mes Reclamation");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        final DefaultListModel<String> list = new DefaultListModel<>();
        //Tabs : toolbar
         AutoCompleteTextField Textbarre = new AutoCompleteTextField(list);
         Textbarre.setMinimumElementsShownInPopup(5);
          Button chercher = new Button("chercher");
         Tabs tab = new Tabs();
         UIBuilder ui = new UIBuilder();
         Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
         tab.addTab("Réclamer", cnt1);
             
           // Container cn3=new Container(BoxLayout.x());
       
       // Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
       // tab.addTab("Invitez des membres", cnt2);
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
       
        
        
     
        
       
        
     
        Image icon = theme.getImage("logoo.jpg");
         Container topBar = BorderLayout.center(new Label(icon));
        this.getToolbar().addMaterialCommandToSideMenu("Mon Profil", FontImage.MATERIAL_HOME, e->{
            new MembreAfficherInterface().show();
            
       });

        this.getToolbar().addMaterialCommandToSideMenu("Mes Réclamations", FontImage.MATERIAL_19MP, e->{
            new MembreReclamationAfficherInterface().show();  });

         this.getToolbar().addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_EVENT, e->{
            new EvenementAfficherInterface().show();     
       });
         
          chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ReclamationChercherInterface(Textbarre.getText()).show();
            }
        });
        this.getToolbar().addComponentToSideMenu(topBar);
        ArrayList<Reclamation> list_e = new ServiceReclamation().Afficher();
        cnt1.add(Textbarre).add(chercher);
    
        cnt1.setLayout(BoxLayout.y());
        for(Reclamation gmi :  list_e )
            if(gmi.getId_emeteur()==id_user_actif){
        cnt1.add(addItemGroupe(gmi));
            }
                         
        
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
    public MembreReclamationAfficherInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
    
    public Container addItemMembre(Membre m){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(m.getNom()+" "+m.getPrenom());
        Label lab2=new Label(""+m.getMdp());
        
        m.setAge(0);
        OnOffSwitch genre = new OnOffSwitch();//switch
        genre.addActionListener((e) -> {
            if(genre.isValue())m.setAge(1);//System.out.println("on");
            else m.setAge(0);//System.out.println("off");
        });
        Container genderContainer = new Container(BoxLayout.x());
        genderContainer.add(new Label("Annuler"));
        genderContainer.add(genre);
        genderContainer.add(new Label("Inviter"));
        
        ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
        cn2.add(lab).add(lab2).add(genderContainer);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        
        
        //cn1.setLeadComponent(genderContainer);
        return cn1;
                
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreReclamationAfficherInterface");
        setName("MembreReclamationAfficherInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
}
