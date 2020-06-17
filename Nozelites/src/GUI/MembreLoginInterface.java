/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Membre;
import java.io.IOException;
import java.util.ArrayList;

import services.ServiceMembre;
import utils.Session;

/**
 * GUI builder created Form
 *
 * @author Nebil
 */
public class MembreLoginInterface extends com.codename1.ui.Form {
    private ArrayList<Membre> list_m = new ServiceMembre().Afficher();
    
    public MembreLoginInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setLayout(BoxLayout.y());
        setTitle("Authentification");
        Label lblMdp = new Label ("Mot de passe");
        Label lblLogin = new Label("Nom d'utilisateur");
        TextField login = new TextField();
        TextField mdp = new TextField("", "", 20, TextField.PASSWORD);
    
        Button btnCnx = new Button("Se Connecter");
        Button btnSign = new Button ("Creer un compte");
        
       add(lblLogin).add(login).add(lblMdp).add(mdp).add(btnCnx).add(btnSign);
        
        btnCnx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                for(Membre mi : list_m){ 
                    if(login.getText().equals(mi.getLogin()) && mdp.getText().equals(mi.getMdp())){
                        Session s =new Session(mi.getUsrId());
                        new MembreInterface().show();

                    }
                }
            }
        });
        btnSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MembreAjouterInterface().show();
            }
        });
        
        
    }
    
    
    
    
    public MembreLoginInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreLoginInterface");
        setName("MembreLoginInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
