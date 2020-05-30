/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
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
import entities.Publication_entities;
import java.io.IOException;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;
import services.ServicePublication;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;


/**
 *
 * @author salon2
 */
public class PublicationAjouterInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    private int id_user_actif = 9;
    private ArrayList<Publication_entities> list_m = new ServicePublication().Afficher();
    private FileUploader file;
    String fileNameInServer;
    public String imgPath;
    private Image img;
    public PublicationAjouterInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Ajouter les publications ");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Nouvelle publication", cnt1);
        
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
        ArrayList<Publication_entities> list_gm = new ServicePublication().Afficher();
   
        
        
        //ajouter un groupe
        TextField titre = new TextField(null, "Titre");//textfield
        TextField description = new TextField(null, "Description");//textfield
    
   
         Validator val = new Validator();
            val.addConstraint(titre, new LengthConstraint(1));
            val.addConstraint(description, new LengthConstraint(1));
       Button upload = new Button("upload");
       Button picture = new Button("parcourir");
        ImageViewer iv = new ImageViewer();
       picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
       picture.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    imgPath = Capture.capturePhoto();
                    System.out.println("ken haka" + imgPath);
                    String link =imgPath.toString();
                    int pod = link.indexOf("/", 2);
                    String news = link.substring(pod + 2, link.length());
                    System.out.println("ye5i wala haka" + news);
                    img = Image.createImage(imgPath);
                    
                    iv.setImage(img);
                   
                    FileUploader fu = new FileUploader("http://localhost/PIDEV_Web/Nozelites/web");
                    
                    
                    fileNameInServer = fu.upload(news);
                    
                  
                    
                    
                } catch (IOException ex){
                    ex.printStackTrace();
                } catch (Exception ex) {
                    
                }
            }
        });

       
       
       
       
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                ServicePublication sg = new ServicePublication();
                
                if ( val.isValid()==false ) {
                    
                 
                    
                     Dialog.show("Erreur", val.getErrorMessage(titre) + " and "+ val.getErrorMessage(description)  , "OK", "Cancel");
                    
                } else {
                sg.ajouter(new Publication_entities(titre.getText(), description.getText(),0,fileNameInServer,1,10,0));
                showToast("Votre Publication est ajoutée avec succées ");
                InfiniteProgress bar = new InfiniteProgress();
                    Showbar(bar);
                
                }
               
                
                new PublicationInterface().show();
                
            }
        });
        
        cnt1.setLayout(BoxLayout.y());
         
  
      
        cnt1.add(titre).add(description).add(picture).add(save).add(iv);
        
        
        
      
        
        //retour btn
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new MembreGroupesInterface().show();
        });
        
    }
    
    public PublicationAjouterInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
    
    public Container addItemMembre(Publication_entities m){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(m.getTitre()+" "+m.getDescription());
        
        
        
  
        Container genderContainer = new Container(BoxLayout.x());
        genderContainer.add(new Label("Annuler"));
        genderContainer.add(new Label("Inviter"));
        
       ImageViewer imgv=new ImageViewer(theme.getImage("users.png"));
       
    
        cn2.add(lab).add(genderContainer);
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
        
        
        
        //cn1.setLeadComponent(genderContainer);
        return cn1;
                
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
      private void Showbar (InfiniteProgress b){
          b.showInfiniteBlocking();
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
