/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Publication_entities;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;
import services.ServicePublication;

/**
 *
 * @author salon2
 */
public class PublicationModifierInterface extends com.codename1.ui.Form {
    private Resources theme;
    private int id_user_actif = 9;
     private String reg,imgPath,fileNameInServer;
     ImageViewer vu1, vu2;
    Button btncapture;
     Image img, img1;
    public PublicationModifierInterface(Publication_entities groupe)  {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Modifier");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        //TextField titre = new TextField(null, "Titre");//textfield
        //TextField description = new TextField(null, "Description");//textfield
      
         Button savee = new Button("Enregistrer");
        btncapture = new Button("Select Image");
        fileNameInServer=groupe.getImage();
          ScaleImageLabel Photo = new ScaleImageLabel();
         try {
          if(groupe.getImage().equals("C"))
          {
              img1 = Image.createImage("file:/" + groupe.getImage()).fill(555, 500);
          }
          else{
              img1 = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/" + groupe.getImage()).fill(300, 300);
          }
                        //v = new ImageViewer(img);
          //img1 = Image.createImage("file:/C:/wamp64/www/PIDEV_Web/Nozelites/web/front/images/"+groupe.getImage()).fill(600, 700);
          Photo.setIcon(img1);
          
        TextField titre = new TextField(groupe.getTitre(), "Titre");//textfield
        TextField description = new TextField(groupe.getDescription(), "Description");//textfield
         TextField path = new TextField("");
        btncapture.addActionListener(new ActionListener() {
              @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
               
                Image im;
               imgPath =Capture.capturePhoto();
                im = Image.createImage(imgPath);
                        System.out.println("lien" + im);
               Photo.setIcon(im);
               
               System.out.println(imgPath);
               String link=imgPath.toString();
               int pod =link.indexOf("/", 2);
               String news= link.substring(pod + 2, link.length());
               FileUploader fu = new FileUploader ("http://localhost/PIDEV_Web/Nozelites/web");
               fileNameInServer = fu.upload(news);
               path.setText(fileNameInServer);
                        System.out.println("hedha el lien modifié"+fileNameInServer);
               
           } catch(IOException ex){
               ex.printStackTrace();
           }catch(Exception ex){
               
           }}
                
            });
        
        
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Publication_entities newGroupe = new Publication_entities(titre.getText(),description.getText(),groupe.getId(),fileNameInServer,1,10,0);
                new ServicePublication().modifier(newGroupe);
                new PublicationInterface().show();
                
            }
        });
        
      setLayout(BoxLayout.y());
        addAll(titre,description,btncapture,Photo,save);
      
         } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        this.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), ev->{
               new PublicationInterface();
            });
    }
    
    public PublicationModifierInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("MembreGroupeModifierInterface");
        setName("MembreGroupeModifierInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
}
