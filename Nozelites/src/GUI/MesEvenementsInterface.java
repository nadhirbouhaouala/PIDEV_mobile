/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Evennement;
import java.util.ArrayList;
import services.ServiceEvenement;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.Display;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Image;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;
import utils.Session;


/**
 *
 * @author syrine
 */
public class MesEvenementsInterface extends com.codename1.ui.Form {
     private Resources theme;
    private int id_user_actif = Session.id_Session;
    private String reg,imgPath,fileNameInServer;
     ImageViewer vu1, vu2;
    Button btncapture;
     Image img, img1;
    public MesEvenementsInterface(Evennement evnt) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("Mes Evenements");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Les Evenements", cnt1);
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Mes Evenements", cnt2);
           ArrayList<Evennement> list_e = new ServiceEvenement().Afficher();
         
        Button save = new Button("Enregistrer");
        btncapture = new Button("Select Image");
        fileNameInServer=evnt.getImage();
          ScaleImageLabel Photo = new ScaleImageLabel();
         try {
         
          img1 = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/"+evnt.getImage()).fill(600, 700);
          Photo.setIcon(img1);
            
            
        Picker p = new Picker();
        p.setType(Display.PICKER_TYPE_DATE);

        Picker pt = new Picker();
        pt.setType(Display.PICKER_TYPE_TIME);
        p.setText(evnt.getDate());
        pt.setText(evnt.getHeure());

         TextField nom = new TextField(evnt.getNom(), "Nom");//textfield
         TextField lieu = new TextField(evnt.getLieu().replace('/', '-'), "lieu");
          String s="firas";
         
        // TextField date = new TextField(evnt.getDate().toString(), "date");
         TextField siteweb = new TextField(evnt.getSiteWeb(), "siteweb");
         //TextField heure = new TextField(evnt.getHeure().toString(), "heure");
        
         TextField description = new TextField(evnt.getDesciption(), "Description");//textfield
         TextField nbplace = new TextField(String.valueOf(evnt.getNbPlace()), "nbplace");
         System.out.println(String.valueOf(evnt.getNbParticipant()));
          System.out.println(evnt.getNbPlace());
         TextField image = new TextField(evnt.getImage(), "image");
          TextField path = new TextField("");
        btncapture.addActionListener(new ActionListener() {
              @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
               
                Image im;
               imgPath =Capture.capturePhoto();
                im = Image.createImage(imgPath).scaledSmallerRatio(1000, 500);
               Photo.setIcon(im);
               
               System.out.println(imgPath);
               String link=imgPath.toString();
               int pod =link.indexOf("/", 2);
               String news= link.substring(pod + 2, link.length());
               FileUploader fu = new FileUploader ("http://localhost/PIDEV_Web/Nozelites/web");
               fileNameInServer = fu.upload(news);
               path.setText(fileNameInServer);
               
               
           } catch(IOException ex){
               ex.printStackTrace();
           }catch(Exception ex){
               
           }}
                
            });
         
       
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 ServiceEvenement sg = new ServiceEvenement();                
                    Evennement e=new Evennement(evnt.getIdE(), lieu.getText(), p.getText(), pt.getText(), description.getText(),siteweb.getText(),Integer.parseInt(nbplace.getText()), fileNameInServer,nom.getText());
                    sg.modifier(e);
                new EvenementAfficherInterface().show();
            }
        });
           setLayout(BoxLayout.y());
           addAll(nom,lieu,p,siteweb,pt,description,nbplace,btncapture,Photo,save);
            } catch (IOException ex) {
            ex.printStackTrace();
        }
            this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new EvenementAfficherInterface().show();
        });
        
        
    }
}
