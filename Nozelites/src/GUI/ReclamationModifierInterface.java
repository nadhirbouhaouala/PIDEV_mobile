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
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Reclamation;
import services.ServiceReclamation;

/**
 *
 * @author KHAIRI
 */
public class ReclamationModifierInterface extends com.codename1.ui.Form {
    
      private Resources theme;
    private int id_user_actif = 9;

    public ReclamationModifierInterface(Reclamation groupe)  {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Modifier");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        //TextField titre = new TextField(null, "Titre");//textfield
        //TextField description = new TextField(null, "Description");//textfield
      
        
        
  
        TextField description = new TextField(groupe.getDescription(), "Description");//textfield
        
        
        
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Reclamation newGroupe = new Reclamation(groupe.getIdRecl(),9,groupe.getId_cible(),description.getText(),groupe.getEtat(),groupe.getSelecteur(),groupe.getDate());
                
                new ServiceReclamation().modifier(newGroupe);
                new MembreReclamationAfficherInterface().show();
                
            }
        });
        
      setLayout(BoxLayout.y());
        add(description).add(save);
      
        
        
        
          this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new MembreReclamationAfficherInterface().show();
        });
    }
    
    public ReclamationModifierInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
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
