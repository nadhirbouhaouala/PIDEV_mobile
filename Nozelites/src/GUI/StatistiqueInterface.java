/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class StatistiqueInterface extends com.codename1.ui.Form {

    private Resources theme;
    private int id_user_actif = 9;
    
    public StatistiqueInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Statistiques");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        cnt1.setLayout(BoxLayout.y());
        
        
        
        tab.addTab("", cnt1);
        
        this.add(tab);
        
        this.getToolbar().addCommandToLeftSideMenu("Offres", theme.getImage("rocket.png"), ev->{
               new OffreInterface().show();
            });
        this.getToolbar().addCommandToLeftSideMenu("Messages", theme.getImage("digital-marketing.png"), ev->{
               new MessageInboxInterface().show();
            });
        this.getToolbar().addCommandToLeftSideMenu("Statistiques", theme.getImage("graphic.png"), ev->{
               new StatistiqueInterface().show();
            });
    }
    
    public StatistiqueInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("StatistiqueInterface");
        setName("StatistiqueInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
