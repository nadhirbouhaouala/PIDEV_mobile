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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
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
public class MembreGroupeAjouterInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 9;
    private ArrayList<Membre> list_m = new ServiceMembre().Afficher();

    public MembreGroupeAjouterInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Mes Groupes");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Nouveau groupe", cnt1);
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Invitez des membres", cnt2);
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
        ArrayList<GroupeMembre> list_gm = new ServiceGroupeMembre().Afficher();
        ArrayList<Groupe> list_g = new ServiceGroupe().Afficher();
        
        
        //ajouter un groupe
        TextField titre = new TextField(null, "Titre");//textfield
        TextField description = new TextField(null, "Description");//textfield
        Container etatContainer = new Container(BoxLayout.x());
        OnOffSwitch etat = new OnOffSwitch();//switch
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
                //ajouter groupe
                ServiceGroupe sg = new ServiceGroupe();
                int etatt = 0;
                if(etat.isValue())etatt=1;
                sg.ajouter(new Groupe(0, titre.getText(), description.getText(), etatt));
                //ajouter membre
                ServiceGroupeMembre sgm = new ServiceGroupeMembre();
                sgm.ajouter(new GroupeMembre(1, sg.lastid(), id_user_actif, -1, "administrateur"));
                for(Membre mi : list_m)
                    if(mi.getAge()==1)
                        sgm.ajouter(new GroupeMembre(1, sg.lastid(),mi.getUsrId() , id_user_actif, "invitation"));
                new MembreGroupesInterface().show();
                
            }
        });
        cnt1.setLayout(BoxLayout.y());
        cnt1.add(titre).add(description).add(etatContainer).add(save);
        
        //inviter membre 
        for(Membre mi : list_m)
            if(mi.getUsrId()!=id_user_actif)
                cnt2.add(addItemMembre(mi));
        
        //retour btn
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new MembreGroupesInterface().show();
        });
        
    }
    
    public MembreGroupeAjouterInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
    
    public Container addItemMembre(Membre m){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(m.getNom()+" "+m.getPrenom());
        Label lab2=new Label(""+m.getMdp());
        
        m.setAge(0);
        OnOffSwitch genre = new OnOffSwitch();//switch
        genre.addActionListener((e) -> {
            if(genre.isValue())m.setAge(1);//System.out.println("on");
            else m.setAge(0);//System.out.println("off");
        });
        Container genderContainer = new Container(BoxLayout.x());
        genderContainer.add(new Label("Annuler"));
        genderContainer.add(genre);
        genderContainer.add(new Label("Inviter"));
        
        ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
        cn2.add(lab).add(lab2).add(genderContainer);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        
        
        //cn1.setLeadComponent(genderContainer);
        return cn1;
                
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreGroupeAjouterInterface");
        setName("MembreGroupeAjouterInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
