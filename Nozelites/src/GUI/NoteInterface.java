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
import entities.Evaluation;

import entities.Membre;
import entities.Reclamation;
import java.util.ArrayList;
import services.ServiceEvaluation;
import services.ServiceMembre;
import services.ServiceReclamation;

/**
 *
 * @author KHAIRI
 */
public class NoteInterface extends com.codename1.ui.Form{
    ComboBox rating = new ComboBox();
     private Resources theme;
    private ArrayList<Membre> list_m = new ServiceMembre().Afficher();
int rate;
int ahawa ;
    public NoteInterface(int id) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Noté le traitement de la Réclamation");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Note ", cnt1);
      
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
       
  com.codename1.ui.ComboBox rating=new com.codename1.ui.ComboBox(); 
        rating.addItem("0" );
        rating.addItem("1" );
        rating.addItem("2" );
        rating.addItem("3" );
        rating.addItem("4" );
        rating.addItem("5" );
   
                        System.out.println("hedheya el rate :"+ rating.getSelectedItem());

        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                ServiceEvaluation sg = new ServiceEvaluation();
                     rate =  rating.getSelectedItem().hashCode();  
                     ahawa = rate - 48 ;
                System.out.println("hedheya el rate " +ahawa  );
                
                sg.ajouter(new Evaluation(id,ahawa));
             
                
                
            
              // new MembreReclamationAfficherInterface().show();
                 new MembreReclamationAfficherInterface().show();
            }
        });
        cnt1.setLayout(BoxLayout.y());
        cnt1.add(rating).add(save);
        
        //inviter membre 
 
        
        //retour btn
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new MembreGroupesInterface().show();
        });
     //   ArrayList<Evaluation> list_e = new ServiceEvaluation().Afficher();
        
        cnt1.setLayout(BoxLayout.y());
     /*   for(Reclamation gmi :  list_e )
                            cnt1.add(addItemGroupe(gmi));*/
                         
        
    }
  /*   public Container addItemGroupe(Reclamation g){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        
        Label lab=new Label(g.getDescription());
        Label lab2=new Label(g.getSelecteur());
        
        ImageViewer imgv=new ImageViewer(theme.getImage("groupes.png"));
        cn2.add(lab).add(lab2);  
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        return cn1;
                
    }*/
    public NoteInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
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

    

