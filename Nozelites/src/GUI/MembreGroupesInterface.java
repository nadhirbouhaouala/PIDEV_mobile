/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceGroupe;

/**
 * GUI builder created Form
 *
 * @author nadhir
 */
public class MembreGroupesInterface extends com.codename1.ui.Form {

    public MembreGroupesInterface(Form previous) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("List tasks");
        setLayout(BoxLayout.y());
        

        this.add(new SpanLabel(new ServiceGroupe().Afficher().toString()));

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
    public MembreGroupesInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreGroupesInterface");
        setName("MembreGroupesInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
