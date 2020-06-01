/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Message;
import entities.Offre;
import java.util.ArrayList;
import services.ServiceMessage;
import services.ServiceOffre;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class OffreInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 9;
    
    public OffreInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Mes Offres");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        ServiceOffre srv = new ServiceOffre();
        ArrayList<Offre> list_o = srv.Afficher();
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        
        cnt1.setLayout(BoxLayout.y());
        
        for(Offre o : list_o)
        {
            if(o.getIdRecepteur() == id_user_actif)
                {
                    Label emetteur = new Label("De : ");
                    emetteur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    emetteur.getAllStyles().setFgColor(0xc30f10);
                    Label emetteur1 = new Label(o.getNom()+" "+o.getPrenom());
                    Container emetteurf = new Container();
                    emetteurf.add(emetteur).add(emetteur1);
                    
                    Label etat = new Label("Etat : ");
                    etat.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    etat.getAllStyles().setFgColor(0xc30f10);
                    Label etat1 = new Label(o.getEtat());
                    Container etatf = new Container();
                    etatf.add(etat).add(etat1);
                    
                    Label type = new Label("Type : ");
                    type.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    type.getAllStyles().setFgColor(0xc30f10);
                    Label type1 = new Label(o.getType());
                    Container typef = new Container();
                    typef.add(type).add(type1);
                    
                    Label entreprise = new Label("Entreprise : ");
                    entreprise.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    entreprise.getAllStyles().setFgColor(0xc30f10);
                    Label entreprise1 = new Label(o.getEntreprise());
                    Container entreprisef = new Container();
                    entreprisef.add(entreprise).add(entreprise1);
                    
                    Label domaine = new Label("Domaine : ");
                    domaine.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    domaine.getAllStyles().setFgColor(0xc30f10);
                    Label domaine1 = new Label(o.getDomaine());
                    Container domainef = new Container();
                    domainef.add(domaine).add(domaine1);
                    
                    Label poste = new Label("Poste : ");
                    poste.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    poste.getAllStyles().setFgColor(0xc30f10);
                    Label poste1 = new Label(o.getPoste());
                    Container postef = new Container();
                    postef.add(poste).add(poste1);
                    
                    Label requis = new Label("Requis : ");
                    requis.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    requis.getAllStyles().setFgColor(0xc30f10);
                    Label requis1 = new Label(o.getRequis());
                    Container requisf = new Container();
                    requisf.add(requis).add(requis1);
                    
                    Label description = new Label("Description : ");
                    description.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    description.getAllStyles().setFgColor(0xc30f10);
                    Label description1 = new Label(o.getDescription());
                    Container descriptionf = new Container();
                    descriptionf.add(description).add(description1);
                    
                    Label date = new Label("Date : ");
                    date.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    date.getAllStyles().setFgColor(0xc30f10);
                    Label date1 = new Label(o.getDate());
                    Container datef = new Container();
                    datef.add(date).add(date1);
                    
                    Button accepterBtn = new Button("Accepter");
                    accepterBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            srv.accepter(o);
                            new OffreInterface().show();
                        }
                    });
                    
                    Button refuserBtn = new Button("Refuser");
                    refuserBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            srv.refuser(o);
                            new OffreInterface().show();
                        }
                    });
                    
                    Container cn2=new Container(BoxLayout.x());
                    cn2.add(accepterBtn).add(refuserBtn);

                    Container cn3=new Container(BoxLayout.y());

                    cn3.add(emetteurf).add(etatf).add(typef).add(entreprisef).add(domainef).add(postef).add(requisf).add(descriptionf).add(datef).add(cn2);
                    cnt1.add(cn3);
                    
                }
        }
        
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
    
    public OffreInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("OffreInterface");
        setName("OffreInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
