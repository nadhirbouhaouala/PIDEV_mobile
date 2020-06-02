/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Message;
import services.ServiceMessage;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class MessageModifierInterface extends com.codename1.ui.Form {

    private Resources theme;
    private int id_user_actif = 9;
    
    public MessageModifierInterface(String objet, String texte,int idDest,int id) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("Modifier votre message");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element 
        
        TextField objet1 = new TextField(objet, "Objet");//textfield
        TextComponent texte1 = new TextComponent();//textfield
        texte1.text(texte);
        texte1.multiline(true);
        texte1.rows(10);
        
        
        Button save = new Button("Envoyer");
        
        cnt1.setLayout(BoxLayout.y());

        cnt1.add(objet1).add(texte1).add(save);

        tab.addTab("", cnt1);
        this.add(tab);
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter message
                ServiceMessage srv = new ServiceMessage();
                
                srv.ajouter(new Message(id,objet1.getText(),texte1.getText(),id_user_actif,idDest));
                
                new MessageInboxInterface().show();
            }
            });
        
        this.getToolbar().addCommandToLeftSideMenu("Offres", theme.getImage("rocket.png"), ev->{
               new OffreInterface().show();
            });
        this.getToolbar().addCommandToLeftSideMenu("Messages", theme.getImage("digital-marketing.png"), ev->{
               new MessageInboxInterface().show();
            });
        this.getToolbar().addCommandToLeftSideMenu("Statistiques", theme.getImage("graphic.png"), ev->{
               new StatistiqueInterface().show();
            });
    }
    
    public MessageModifierInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MessageModifierInterface");
        setName("MessageModifierInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
