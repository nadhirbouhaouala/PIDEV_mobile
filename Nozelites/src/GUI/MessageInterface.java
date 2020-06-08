/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Message;
import java.util.Date;
import services.ServiceMessage;
import utils.Session;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class MessageInterface extends com.codename1.ui.Form {

    Session s = new Session();
    private Resources theme;
    private int id_user_actif = s.getId_Session();
    
    public MessageInterface(String objet, String texte,int idDest){
        
        setTitle("Envoyer un message");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element 
        
        TextField dest = new TextField("", "Destinataire");//textfield
        TextField objet1 = new TextField(objet, "Objet");//textfield
        TextComponent texte1 = new TextComponent();
        texte1.text(texte);
        texte1.multiline(true);
        texte1.rows(10);
            
        Button save = new Button("Envoyer");
        
        cnt1.setLayout(BoxLayout.y());
        if(idDest == -1)
        {
            cnt1.add(dest).add(objet1).add(texte1).add(save);
        }
        else
        {
            cnt1.add(objet1).add(texte1).add(save);
        }  
        tab.addTab("", cnt1);
        this.add(tab);
        
        if(idDest == -1) //Transfert
        {
            save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter message
                ServiceMessage srv = new ServiceMessage();

                int id = -1;
                int idD = srv.getIdByEmail(dest.getText());
                
                srv.ajouter(new Message(id,objet1.getText(),texte1.getText(),id_user_actif,idD));
                
                new MessageInboxInterface().show();
            }
        });
         
        }
        else             //Réponse
        {
            save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter message
                ServiceMessage srv = new ServiceMessage();

                int id = -1;
                
                srv.ajouter(new Message(id,objet1.getText(),texte1.getText(),id_user_actif,idDest));
                
                new MessageInboxInterface().show();
            }
            });
        }
        
        this.getToolbar().addCommandToOverflowMenu("Déconnexion", theme.getImage("back-command.png"), ev->{
               new MembreLoginInterface().show();
            });
        
        // Sidebar
        Image icon = theme.getImage("logoo.jpg");
        Container topBar = BorderLayout.center(new Label(icon));
        this.getToolbar().addMaterialCommandToSideMenu("Mon Profil", FontImage.MATERIAL_HOME, e->{
            new MembreAfficherInterface().show();
            
       });


        this.getToolbar().addMaterialCommandToSideMenu("Mes Réclamations", FontImage.MATERIAL_19MP, e->{
            new MembreReclamationAfficherInterface().show();  });


           this.getToolbar().addMaterialCommandToSideMenu("Mon Forum", FontImage.MATERIAL_HOME, e->{
            new PublicationInterface().show();
            
       });

         this.getToolbar().addMaterialCommandToSideMenu("Mes Evènements", FontImage.MATERIAL_EVENT, e->{
            new EvenementAfficherInterface().show();

            
       });
         
         this.getToolbar().addMaterialCommandToSideMenu("Inbox", FontImage.MATERIAL_MESSAGE, e->{
            new MessageInboxInterface().show();

            
       });
         
         this.getToolbar().addMaterialCommandToSideMenu("Mes Offres", FontImage.MATERIAL_PERSON, e->{
            new OffreInterface().show();

            
       });
         
         this.getToolbar().addMaterialCommandToSideMenu("Statistiques", FontImage.MATERIAL_GRAPHIC_EQ, e->{
            new StatistiqueInterface().show();

            
       });
         
        this.getToolbar().addComponentToSideMenu(topBar);
        //fin sidebar
        
    }
    
    public MessageInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MessageInterface");
        setName("MessageInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
