/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.*;
import java.util.ArrayList;
import services.*;

/**
 * GUI builder created Form
 *
 * @author nadhir
 */
public class MembreGroupesInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 9;

    public MembreGroupesInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("Mes Groupes");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mes groupes", cnt1);
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Mes invitations", cnt2);
        //creer un groupe 
        Button ajouterBtn = new Button("Créer un groupe");
        ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MembreGroupeAjouterInterface().show();
            }
        });
        this.add(tab).add(ajouterBtn);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
        ArrayList<GroupeMembre> list_gm = new ServiceGroupeMembre().Afficher();
        ArrayList<Groupe> list_g = new ServiceGroupe().Afficher();
        ArrayList<Membre> list_m = new ServiceMembre().Afficher();
        
        
        //liste groupes
        cnt1.setLayout(BoxLayout.y());
        for(GroupeMembre gmi : list_gm)
            if(gmi.getId_membre()==id_user_actif)
                if(gmi.getEtat().equals("standard") || gmi.getEtat().equals("administrateur"))
                    for(Groupe gi : list_g)
                        if(gi.getId()==gmi.getId_groupe())
                            cnt1.add(addItemGroupe(gi));
        
        //liste invitation
        cnt2.setLayout(BoxLayout.y());
        for(GroupeMembre gmi : list_gm)
            if(gmi.getId_membre()==id_user_actif)
                if(gmi.getEtat().equals("invitation"))
                    for(Membre mi : list_m)
                        if(mi.getUsrId()==gmi.getId_membre())
                            for(Groupe gi : list_g)
                                if(gi.getId()==gmi.getId_groupe())
                            cnt2.add(addItemMembre(mi,gi,gmi));
        
        
       

        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
               System.out.println("back");
            });
    }

    
    
    public Container addItemGroupe(Groupe g){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(g.getTitre());
        Label lab2=new Label(g.getDescription());
        Button btn=new Button("fermé");
        if(g.getAutorisation()==1)btn.setText("ouvert");
        
        ImageViewer imgv=new ImageViewer(theme.getImage("groupes.png"));
        cn2.add(lab).add(lab2).add(btn);
        
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        btn.addActionListener(e->{
            new MembreGroupeInterface(g).show();
        });
        
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
    public Container addItemMembre(Membre m,Groupe g,GroupeMembre gm){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(m.getNom()+" "+m.getPrenom()+" vous invite");
        Label lab2=new Label("au groupe: "+g.getTitre());
        Button btnAccepter=new Button("accepter");
        btnAccepter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                gm.setEtat("membre");
                new ServiceGroupeMembre().modifier(gm);
                new MembreGroupesInterface().show();
            }
        });
        Button btnRefuser=new Button("refuser");
        btnRefuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                gm.setEtat("bloqué");
                new ServiceGroupeMembre().modifier(gm);
                new MembreGroupesInterface().show();
            }
        });
        Container cn3=new Container(BoxLayout.x());
        cn3.add(btnAccepter).add(btnRefuser);
        
        ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
        cn2.add(lab).add(lab2).add(cn3);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        return cn1;
                
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
