/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import services.ServiceGroupe;
import services.ServiceGroupeMembre;

/**
 * GUI builder created Form
 *
 * @author nadhir
 */
public class MembreGroupesInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 8;

    public MembreGroupesInterface(Form previous) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("List tasks");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mes groupes", cnt1);
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Mes invitations", cnt2);
        add(tab);
        cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));

        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
    
    public MembreGroupesInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////-- DON'T EDIT BELOW THIS LINE!!!


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
