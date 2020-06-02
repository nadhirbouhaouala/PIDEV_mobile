/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
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
import java.util.ArrayList;
import services.ServiceMessage;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class MessageInboxInterface extends com.codename1.ui.Form {

    private Resources theme;
    private int id_user_actif = 9;
    
    public MessageInboxInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Inbox");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        
        ServiceMessage srvm = new ServiceMessage();
        ArrayList<Message> list_m = srvm.Afficher();
        Container cnt1 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        
        for(Message m : list_m)
        {
            if(m.getIdEmetteur()== id_user_actif)
                {
                    Label objet = new Label("Objet : ");
                    objet.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    objet.getAllStyles().setFgColor(0xc30f10);
                    Label objet1 = new Label(m.getObjet());
                    Container objetf = new Container();
                    objetf.add(objet).add(objet1);
                    
                    Label texte = new Label("Message : ");
                    texte.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    texte.getAllStyles().setFgColor(0xc30f10);
                    SpanLabel texte1 = new SpanLabel(m.getTexte());
                    Container textef = new Container();
                    textef.add(texte).add(texte1);
                    
                    Label emetteur = new Label("À : ");
                    emetteur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    emetteur.getAllStyles().setFgColor(0xc30f10);
                    Label emetteur1 = new Label(m.getNomD()+" "+m.getPrenomD());
                    Container emetteurf = new Container();
                    emetteurf.add(emetteur).add(emetteur1);
                    
                    Label date = new Label("Date : ");
                    date.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    date.getAllStyles().setFgColor(0xc30f10);
                    Label date1 = new Label(m.getDate());
                    Container datef = new Container();
                    datef.add(date).add(date1);
                    
                    Button modifierBtn = new Button("Modifier");
                    modifierBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                            new MessageModifierInterface(m.getObjet(),m.getTexte(),m.getIdRecepteur(),m.getId()).show();
                        }
                    });
                    
                    Button transfererBtn = new Button("Transférer");
                    transfererBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                            new MessageInterface(m.getObjet(),m.getTexte(),-1).show();
                        }
                    });
                    
                    Button supprimerBtn = new Button("Supprimer");
                    supprimerBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            srvm.supprimer(m);
                            new MessageInboxInterface().show();
                        }
                    });
                    
                    Container cn4=new Container(BoxLayout.x());
                    cn4.add(modifierBtn).add(transfererBtn).add(supprimerBtn);

                    Container cn5=new Container(BoxLayout.y());

                    cn5.add(emetteurf).add(objetf).add(textef).add(datef).add(cn4);
                    cnt1.add(cn5);
                    
                }
        }
        
        for(Message m : list_m)
        {
            if(m.getIdRecepteur() == id_user_actif)
                {
                    Label objet = new Label("Objet : ");
                    objet.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    objet.getAllStyles().setFgColor(0xc30f10);
                    Label objet1 = new Label(m.getObjet());
                    Container objetf = new Container();
                    objetf.add(objet).add(objet1);
                    
                    Label texte = new Label("Message : ");
                    texte.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    texte.getAllStyles().setFgColor(0xc30f10);
                    SpanLabel texte1 = new SpanLabel(m.getTexte());
                    Container textef = new Container();
                    textef.add(texte).add(texte1);
                    
                    Label emetteur = new Label("De : ");
                    emetteur.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    emetteur.getAllStyles().setFgColor(0xc30f10);
                    Label emetteur1 = new Label(m.getNom()+" "+m.getPrenom());
                    Container emetteurf = new Container();
                    emetteurf.add(emetteur).add(emetteur1);
                    
                    Label date = new Label("Date : ");
                    date.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    date.getAllStyles().setFgColor(0xc30f10);
                    Label date1 = new Label(m.getDate());
                    Container datef = new Container();
                    datef.add(date).add(date1);
                    
                    Button repondreBtn = new Button("Répondre");
                    repondreBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                         
                            new MessageInterface("Re: "+m.getObjet(),"",m.getIdEmetteur()).show();
                        }
                    });
                    
                    Button transfererBtn = new Button("Transférer");
                    transfererBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                            new MessageInterface(m.getObjet(),m.getTexte(),-1).show();
                        }
                    });
                    
                    Container cn3=new Container(BoxLayout.x());
                    cn3.add(repondreBtn).add(transfererBtn);

                    Container cn4=new Container(BoxLayout.y());

                    cn4.add(emetteurf).add(objetf).add(textef).add(datef).add(cn3);
                    cnt2.add(cn4);
                    
                }
        }
        
        tab.addTab("Envoyés", cnt1);
        tab.addTab("Reçus",cnt2);
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
        
        this.getToolbar().addCommandToRightBar("Envoyer", theme.getImage("cal_right_arrow.png"), ev->{
               new MessageInterface("","",-1).show();
            });
    }
    
    public MessageInboxInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MessageInboxInterface");
        setName("MessageInboxInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
