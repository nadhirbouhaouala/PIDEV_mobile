/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Membre;
import java.util.ArrayList;
import services.ServiceMembre;

/**
 * GUI builder created Form
 *
 * @author Nebil
 */
public class MembreProfilinterface extends com.codename1.ui.Form {
    private Membre list_m;
    private Resources theme;
    
    public MembreProfilinterface(int id) {
        
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setLayout(BoxLayout.y());
        list_m= new ServiceMembre().AfficherProfil(id);
        
        theme = UIManager.initFirstTheme("/MembreGroupes");
        Label Lnom = new Label("Nom : "+ list_m.getNom());
        Label Lprenom = new Label("Prenom : "+list_m.getPrenom());
        Label Lage = new Label("Age : "+list_m.getAge());
        Label Lmail = new Label ("Email : "+list_m.getMail());
        Label Lexperience = new Label("Experience : " + list_m.getExp());
        
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("users.png"), false);
        
        String str = list_m.getImage();
        str = str.substring(str.indexOf("P"), str.length());
        
        System.out.println(str);
        
        Image img = URLImage.createToStorage(enc, "http://localhost/"+str, "http://localhost/"+str);
        ImageViewer imgv1 = new ImageViewer(img);
        add(imgv1).add(Lnom).add(Lprenom).add(Lmail).add(Lage);
        
        
        
        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
               new MembreInterface().show();
            });
        
        
        
        
        
        
        
    }
    
    public MembreProfilinterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreProfilinterface");
        setName("MembreProfilinterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
