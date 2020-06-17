/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Groupe;
import entities.GroupeMembre;
import entities.Membre;
import services.ServiceGroupe;
import services.ServiceGroupeMembre;

/**
 * GUI builder created Form
 *
 * @author nadhir
 */
public class MembreGroupeModifierInterface extends com.codename1.ui.Form {
    
    private Resources theme;

    public MembreGroupeModifierInterface(Groupe groupe) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Modifier");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        
        
        TextField titre = new TextField(groupe.getTitre(), "Titre");//textfield
        TextField description = new TextField(groupe.getDescription(), "Description");//textfield
        Container etatContainer = new Container(BoxLayout.x());
        OnOffSwitch etat = new OnOffSwitch();//switch
        if(groupe.getAutorisation()==1)etat.setValue(true);
        etat.addActionListener((e) -> {
            if(etat.isValue())System.out.println("on");
            else System.out.println("off");
        });
        etatContainer.add(new Label("Fermé"));
        etatContainer.add(etat);
        etatContainer.add(new Label("Ouvert"));
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(etat.isValue())groupe.setAutorisation(1);
                else groupe.setAutorisation(0);
                Groupe newGroupe = new Groupe(groupe.getId(),titre.getText(),description.getText(),groupe.getAutorisation());
                new ServiceGroupe().modifier(newGroupe);
                new MembreGroupeInterface(newGroupe).show();
                
            }
        });
        setLayout(BoxLayout.y());
        add(titre).add(description).add(etatContainer).add(save);
        
        
        
        this.getToolbar().addCommandToRightBar("retour", theme.getImage("back-command.png"), ev->{
               new MembreGroupeInterface(groupe);
            });
        
        getToolbar().addCommandToSideMenu("Mes groupes", null, e->{
            new MembreGroupesInterface().show();
        });
    }
    
    public MembreGroupeModifierInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreGroupeModifierInterface");
        setName("MembreGroupeModifierInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
