/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Membre;
import services.ServiceMembre;

/**
 * GUI builder created Form
 *
 * @author Nebil
 */
public class MembreAjouterInterface extends com.codename1.ui.Form {

    
    public MembreAjouterInterface() {
       // this(com.codename1.ui.util.Resources.getGlobalResources());
        Membre m = new Membre();
        setLayout(BoxLayout.y());
        setTitle("Creation de compte");
        TextField TNom = new TextField("","Nom ");
        TextField TPrenom = new TextField("","Prenom");
        TextField TAge = new TextField("","Age");
        TextField TTel = new TextField("","Telephone");
        TextField TEmail = new TextField("","email");       
        TextField TLogin = new TextField("","Nom d'utilisateur");
        TextField TMdp = new TextField("","Mot de passe", 20, TextField.PASSWORD);
        
        Button btnAjouter = new Button("S'inscrire");
        
        add(TNom).add(TPrenom).add(TAge).add(TTel).add(TEmail).add(TLogin).add(TMdp).add(btnAjouter);
        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                m.setNom(TNom.getText());
                m.setPrenom(TPrenom.getText());
                m.setAge(Integer.parseInt(TAge.getText()));
                m.setTel(Integer.parseInt(TTel.getText()));
                m.setMail(TEmail.getText());
                m.setLogin(TLogin.getText());
                m.setMdp(TMdp.getText());
                
                new ServiceMembre().ajouter(m);
                new MembreLoginInterface().show();
            }
        });
    }
    
    public MembreAjouterInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreAjouterInterface");
        setName("MembreAjouterInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
