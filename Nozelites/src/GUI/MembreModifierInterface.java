/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.TextField;
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
public class MembreModifierInterface extends com.codename1.ui.Form {
    
private Resources theme;
private Membre list_m;


    public MembreModifierInterface() {
        
        Membre m = new Membre();
        
        setLayout(BoxLayout.y());
        list_m= new ServiceMembre().AfficherProfil(Session.id_Session);
       // this(com.codename1.ui.util.Resources.getGlobalResources());
       theme = UIManager.initFirstTheme("/MembreGroupes");
        TextField nom = new TextField( list_m.getNom());
        TextField prenom = new TextField(list_m.getPrenom());
        TextField age = new TextField(Integer.toString(list_m.getAge()));
        TextField mail = new TextField (list_m.getMail());
        TextField Tel = new TextField( Integer.toString(list_m.getTel()));
        Button  modif = new Button("Accepter");
        
        add(nom).add(prenom).add(mail).add(age).add(Tel).add(modif);
        
        
        
        modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.out.println(nom.getText());
                m.setNom(nom.getText());
                m.setPrenom(prenom.getText());
                m.setAge(Integer.parseInt(age.getText()));
                m.setTel(Integer.parseInt(Tel.getText()));
                m.setMail(mail.getText());
                m.setUsrId(Session.id_Session);
                
                new ServiceMembre().modifier(m);
                new MembreAfficherInterface().show();
                
                
            }
        });
    }
    
    public MembreModifierInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


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
