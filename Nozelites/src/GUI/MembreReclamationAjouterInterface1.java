/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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

import entities.Membre;
import entities.Reclamation;
import java.util.ArrayList;
import services.ServiceMembre;
import services.ServiceReclamation;
import utils.Session;

/**
 *
 * @author KHAIRI
 */
public class MembreReclamationAjouterInterface1 extends com.codename1.ui.Form{
    ComboBox selecteur = new ComboBox();
     private Resources theme;
    private int id_user_actif = Session.getId_Session();;
    private ArrayList<Membre> list_m = new ServiceMembre().Afficher();

    public MembreReclamationAjouterInterface1(int id) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Mes Reclamations");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Signaler un Evenement ", cnt1);
      
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
       
        
        
        //ajouter une reclamation
         com.codename1.ui.ComboBox selecteur=new com.codename1.ui.ComboBox(); 
       // selecteur.addItem("groupe"); 
        selecteur.addItem("evenement"); 
        //selecteur.addItem("Al Hiwar Ettounsi"); 
        //hi.add(cb1);
        
        
        TextField description = new TextField(null, "Description");//textfield
       
       
        
    
        
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                ServiceReclamation sg = new ServiceReclamation();
          
               // if(etat.isValue())etatt=1;
                sg.ajouter(new Reclamation(id_user_actif, id, description.getText(),selecteur.getSelectedItem().toString()));
                //ajouter membre
             
            
              // new MembreReclamationAfficherInterface().show();
                 new MembreReclamationAfficherInterface().show();
            }
        });
        cnt1.setLayout(BoxLayout.y());
        cnt1.add(selecteur).add(description).add(save);
        
        //inviter membre 
 
        
        //retour btn
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new MembreGroupesInterface().show();
        });
        ArrayList<Reclamation> list_e = new ServiceReclamation().Afficher();
        
        cnt1.setLayout(BoxLayout.y());
     /*   for(Reclamation gmi :  list_e )
                            cnt1.add(addItemGroupe(gmi));*/
                         
        
    }

    public MembreReclamationAjouterInterface1(com.codename1.ui.util.Resources resourceObjectInstance) {
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
        setTitle("MembreReclamationAjouterInterface");
        setName("MembreReclamationAjouterInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

    

