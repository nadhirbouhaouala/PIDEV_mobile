/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Evennement;
import entities.Groupe;
import entities.GroupeMembre;
import entities.Membre;
import java.util.ArrayList;
import services.ServiceEvenement;
import com.codename1.ui.Display;
import rest.file.uploader.tn.FileUploader;
import com.codename1.ui.Image;
import com.codename1.ui.FontImage;
import java.io.IOException;
import com.codename1.util.StringUtil;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

/**
 *
 * @author syrine
 */
public class EvenementAjouterInterface extends com.codename1.ui.Form{
     private Resources theme;
    private int id_user_actif = 9;
     private FileUploader file;
    String fileNameInServer;
    public String imgPath;
    private Image img;
  
     public EvenementAjouterInterface() {
    //this(com.codename1.ui.util.Resources.getGlobalResources());
         setTitle("Mes Evenements");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Nouveau Evenement", cnt1);
        
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
      
        ArrayList<Evennement> list_e = new ServiceEvenement().Afficher();
         Picker p=new Picker();
         Picker pt=new Picker();
         Validator val = new Validator();
         p.setType(Display.PICKER_TYPE_DATE);
         p.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
         pt.setType(Display.PICKER_TYPE_TIME);
        // pt.setFormatter(new SimpleDateFormat("HH:mm"));
         
         TextField nom = new TextField(null, "Nom");//textfield
         TextField lieu = new TextField(null, "lieu");
         
         //TextField date = new TextField(null, "date");
         TextField siteweb = new TextField(null, "siteweb");
         //TextField heure = new TextField(null, "heure");
         TextField description = new TextField(null, "Description");//textfield
         TextField nbplace = new TextField(null, "nbplace");
         TextField image = new TextField(null, "image");
          val.addConstraint(nom, new LengthConstraint(1));
           val.addConstraint(lieu, new LengthConstraint(1));
              val.addConstraint(description, new LengthConstraint(1));
              val.addConstraint(nbplace, new NumericConstraint(true, 0, 100, "nombre de place superieur a 0"));
          Button save = new Button("Enregistrer");
           TextField path = new TextField("");
             ScaleImageLabel Photo = new ScaleImageLabel();
          Button picture = new Button("parcourir");
        ImageViewer iv = new ImageViewer(theme.getImage("placeholder2 .png"));
        picture.addActionListener(new ActionListener() {
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
            
            
        
      
         
         
           System.out.println(pt.getText());
          save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                ServiceEvenement sg = new ServiceEvenement();  
     
                System.out.println(p.getText());
                 if ( val.isValid()==false ) {
                    
                     Dialog.show("Erreur", val.getErrorMessage(nbplace) + " and "+ val.getErrorMessage(nom)  + "  and "+ val.getErrorMessage(description) + " and "+ val.getErrorMessage(lieu)    , "OK", "Cancel");
                    
                } else {
                sg.ajouter(new Evennement(id_user_actif,nom.getText(), lieu.getText(),p.getText(),pt.getText()+":00", description.getText(),siteweb.getText(),Integer.parseInt(nbplace.getText()), fileNameInServer));
                              showToast("Votre Publication est ajoutée avec succées ");

                System.out.println(pt.getText());
                //ajouter membre
               
               
                new EvenementAfficherInterface().show();
                 }
                
            }
        });
        
        
        cnt1.setLayout(BoxLayout.y());
        cnt1.addAll(nom,lieu,p,siteweb,pt,description,nbplace,picture,Photo,save);
        
        
        
          this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new EvenementAfficherInterface().show();
        });
     }
     private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ADD_ALARM, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.showDelayed(LEFT);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
}
