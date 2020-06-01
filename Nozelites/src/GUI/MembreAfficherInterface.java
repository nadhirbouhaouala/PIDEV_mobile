/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Membre;
import services.ServiceMembre;
import utils.Session;

/**
 * GUI builder created Form
 *
 * @author Nebil
 */
public class MembreAfficherInterface extends com.codename1.ui.Form {
private Resources theme;
 private Membre list_m;
 
    public MembreAfficherInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        this.setTitle("Profil");
        setLayout(BoxLayout.y());
        list_m= new ServiceMembre().AfficherProfil(Session.id_Session);
        
        
        theme = UIManager.initFirstTheme("/MembreGroupes");
        Label Lnom = new Label("Nom : "+ list_m.getNom());
        Label Lprenom = new Label("Prenom : "+list_m.getPrenom());
        Label Lage = new Label("Age : "+list_m.getAge());
        Label Lmail = new Label ("Email : "+list_m.getMail());
        Label Lexperience = new Label("Experience : " + list_m.getExp());
        Button modif = new Button("Modifer");
        
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("users.png"), false);
        
        String str = list_m.getImage();
        str = str.substring(str.indexOf("P"), str.length());
        
        System.out.println(str);
        
        Image img = URLImage.createToStorage(enc, "http://localhost/"+str, "http://localhost/"+str);
        ImageViewer imgv1 = new ImageViewer(img);
        add(imgv1).add(Lnom).add(Lprenom).add(Lmail).add(Lage).add(modif);
        
        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
               new MembreInterface().show();
            });
        
        modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                 new MembreModifierInterface().show();
                 
            }
        });
        
    }
    
    public MembreAfficherInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreModifierInterface");
        setName("MembreModifierInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
