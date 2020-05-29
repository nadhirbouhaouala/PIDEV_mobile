/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
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
public class MembreGroupeInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 9;
    
    public MembreGroupeInterface(Groupe groupe) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle(groupe.getTitre());
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        ArrayList<GroupeMembre> list_gm = new ServiceGroupeMembre().Afficher();
    ArrayList<Groupe> list_g = new ServiceGroupe().Afficher();
    ArrayList<Membre> list_m = new ServiceMembre().Afficher();
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mon groupe", cnt1);
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Membres", cnt2);
        Container cnt3 = ui.createContainer(theme, "GUI 3");//ajouter graphiquement un GUI element
        if(groupe.getAutorisation()==1)tab.addTab("Inviter", cnt3);
        //groupe info
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
        //membres
        //liste invitation
        cnt2.setLayout(BoxLayout.y());
        /*for(GroupeMembre gmi : list_gm)
            if(gmi.getId_groupe()==groupe.getId())
                if(gmi.getEtat().equals("membre") || gmi.getEtat().equals("administrateur"))
                    for(Membre mi : list_m)
                        if(mi.getUsrId()==gmi.getId_membre())
                            cnt2.add(addItemMembre(mi,gmi.getEtat()));*/
        boolean existe = false;
        for(Membre mi : list_m)
        {
            for(GroupeMembre gmi : list_gm)
            {
                existe = false;
                if(gmi.getId_groupe()==groupe.getId() && gmi.getId_membre()==mi.getUsrId())
                {
                    existe=true;
                    if(gmi.getEtat().equals("membre") || gmi.getEtat().equals("administrateur"))
                        cnt2.add(addItemMembre(mi,gmi.getEtat()));
                }
            }
            if(existe==false)cnt3.add(addItemInvite(mi,groupe));
        }
      
             
        this.add(tab);
        if(groupe.getAutorisation()==1)this.add(cn3);
        System.out.println("cccs");
        
        
        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
               new MembreGroupesInterface().show();
            });
    }
    
    public MembreGroupeInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
    
    
    public Container addItemMembre(Membre m,String etat){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(m.getNom()+" "+m.getPrenom());
        Label lab2=new Label(m.getMail());
        Button btn=new Button(etat);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
            }
        });
        
        
        ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
        cn2.add(lab).add(lab2).add(btn);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
    public Container addItemInvite(Membre m,Groupe g){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(m.getNom()+" "+m.getPrenom());
        Label lab2=new Label(m.getMail());
        Button btn=new Button("inviter");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cn1.removeAll();
                new ServiceGroupeMembre().ajouter(new GroupeMembre(1, g.getId(), m.getUsrId(), id_user_actif, "invitation"));
            }
        });
        
        
        ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
        cn2.add(lab).add(lab2).add(btn);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    

////-- DON'T EDIT BELOW THIS LINE!!!


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
