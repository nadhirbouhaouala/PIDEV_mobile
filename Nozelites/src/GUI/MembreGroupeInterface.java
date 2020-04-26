/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Groupe;
import entities.GroupeMembre;
import entities.Membre;
import java.util.ArrayList;
import services.ServiceGroupe;
import services.ServiceGroupeMembre;
import services.ServiceMembre;

/**
 * GUI builder created Form
 *
 * @author nadhir
 */
public class MembreGroupeInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 9;

    public MembreGroupeInterface(Groupe groupe) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle(groupe.getTitre());
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mon groupe", cnt1);
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Mes invitations", cnt2);
        //afficher un groupe 
        Label titre = new Label("Titre : "+groupe.getTitre());
        Label description = new Label("Description : "+groupe.getDescription());
        Label etat = new Label("Etat : fermé");
        if(groupe.getAutorisation()==1)etat.setText("Etat : Ouvert");
        Button modifierBtn = new Button("Modifier");
        modifierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MembreGroupeModifierInterface(groupe).show();
            }
        });
        Button supprimerBtn = new Button("Supprimer");
        supprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<GroupeMembre> list_gm = new ServiceGroupeMembre().Afficher();
                ServiceGroupeMembre sgm = new ServiceGroupeMembre();
                for(GroupeMembre gmi : list_gm)
                    if(gmi.getId_groupe()==groupe.getId())
                        sgm.supprimer(gmi);
                new ServiceGroupe().supprimer(groupe);
                new MembreGroupesInterface().show();
            }
        });
        Container cn3=new Container(BoxLayout.x());
        cn3.add(supprimerBtn).add(modifierBtn);
        
        Container cn4=new Container(BoxLayout.y());
        
        cn4.add(titre).add(description).add(etat);
        cnt1.add(cn4);
                
        this.add(tab).add(cn3);
        
        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
               new MembreGroupesInterface().show();
            });
    }
    
    public MembreGroupeInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreGroupeInterface");
        setName("MembreGroupeInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}