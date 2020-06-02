/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Membre;
import java.util.ArrayList;
import services.ServiceMembre;

/**
 * GUI builder created Form
 *
 * @author Nebil
 */
public class MembreInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    
    private ArrayList<Membre> list_m = new ServiceMembre().Afficher();
    private Form f1;
    private Form page1;
    
    
    public MembreInterface() {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setScrollableY(true);
        UIBuilder ui = new UIBuilder();
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        
        for(Membre mi : list_m){     
                add(addItemMembre(mi));
        } 
        
        this.getToolbar().addCommandToOverflowMenu("deconnexion", theme.getImage("back-command.png"), ev->{
               new MembreLoginInterface().show();
            });
        
        // Sidebar
        Image icon = theme.getImage("logoo.jpg");
        Container topBar = BorderLayout.center(new Label(icon));
        this.getToolbar().addComponentToSideMenu(topBar);
        this.getToolbar().addMaterialCommandToSideMenu("Mon Profil", FontImage.MATERIAL_HOME, e->{
            new MembreAfficherInterface().show();
            
       });
        
        //fin sidebar
        
        //this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    
    
    public Container addItemMembre(Membre m){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        
        Label lab=new Label(m.getNom()+" "+m.getPrenom());
        Label lab2=new Label(""+m.getMail());
        
        
        
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("users.png"), false);
        String str = m.getImage();
        str = str.substring(str.indexOf("P"), str.length());   
        System.out.println(str);      
        Image img = URLImage.createToStorage(enc, "http://localhost/"+str, "http://localhost/"+str);
        
        ImageViewer imgv = new ImageViewer(img);
        
        cn2.add(lab).add(lab2);
        
        
        Button btn = new Button ("Profil");
        cn2.add(btn);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        //cn1.setLeadComponent(genderContainer);
        cn1.setLeadComponent(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                 new MembreProfilinterface(m.getUsrId()).show();  
                 
            }
        });
       
        
        return cn1;
                
    }
    
    
    
    
    
    
    public MembreInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    
    
    
//////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreInterface");
        setName("MembreInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
