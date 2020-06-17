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
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.MyApplication;
import entities.*;
import java.util.ArrayList;
import services.*;
import utils.Session;

/**
 * GUI builder created Form
 *
 * @author nadhir
 */
public class MembreGroupesInterface extends com.codename1.ui.Form {
    
    private Resources theme;
    int groupes_ferme = 0 , groupes_ouvert = 0;
        

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
        tab.addTab("Statistiques", cnt2);
        Container cnt3 = ui.createContainer(theme, "GUI 3");//ajouter graphiquement un GUI element
        tab.addTab("Mes invitations", cnt3);
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
        ArrayList<Groupe> groupes = new ArrayList<>();
        Container cnt1_grs = ui.createContainer(theme, "GUI 1");
        cnt1.setLayout(BoxLayout.y());
        TextField recherche = new TextField(null, "Chercher ici...");
        recherche.addDataChangeListener((i1, i2) -> {
            System.out.println(recherche.getText());
            cnt1_grs.removeAll();
            for(Groupe gi:groupes)
                if(gi.getTitre().toUpperCase().contains(recherche.getText().toUpperCase())||
                    gi.getDescription().toUpperCase().contains(recherche.getText().toUpperCase())||
                    recherche.getText().equals(""))
                        cnt1_grs.add(addItemGroupe(gi));
            this.repaint();
            this.refreshTheme();
            
        });
        cnt1.add(recherche);
        for(GroupeMembre gmi : list_gm)
            if(gmi.getId_membre()==Session.id_Session)
                if(gmi.getEtat().equals("membre") || gmi.getEtat().equals("administrateur"))
                    for(Groupe gi : list_g)
                        if(gi.getId()==gmi.getId_groupe())
                        {
                            if(gi.getAutorisation()==0)groupes_ferme++;
                            else groupes_ouvert++;
                            cnt1_grs.add(addItemGroupe(gi));
                            groupes.add(gi);
                        }
        cnt1.add(cnt1_grs);
        
        //liste invitation
        cnt3.setLayout(BoxLayout.y());
        for(GroupeMembre gmi : list_gm)
            if(gmi.getId_membre()==Session.id_Session)
                if(gmi.getEtat().equals("invitation"))
                    for(Membre mi : list_m)
                        if(mi.getUsrId()==gmi.getId_invite())
                            for(Groupe gi : list_g)
                                if(gi.getId()==gmi.getId_groupe())
                            cnt3.add(addItemMembre(mi,gi,gmi));
        
        
        //pie chart
      
        double[] values = new double[]{groupes_ouvert, groupes_ferme};
        cnt2.add(createPieChartForm(values));
       

        /*this.getToolbar().addCommandToRightBar("retour", theme.getImage("back-command.png"), ev->{
               System.out.println("back");
            });*/
        String txt = "" ;
        for(Membre mi : list_m)
            if(Session.id_Session==mi.getUsrId())
                txt = mi.getNom()+" "+mi.getPrenom();
        //notification
        showToast("Bienvenue aux groupes "+txt);
        getToolbar().addCommandToSideMenu(" ",null,null);
        getToolbar().addCommandToSideMenu("bienvenue "+txt,null,null);
        this.getToolbar().addMaterialCommandToSideMenu("Mes Réclamations", FontImage.MATERIAL_19MP, e->{
            new MembreReclamationAfficherInterface().show();  });


           this.getToolbar().addMaterialCommandToSideMenu("Mon Forum", FontImage.MATERIAL_HOME, e->{
            new PublicationInterface().show();
            
       });
           
           this.getToolbar().addMaterialCommandToSideMenu("Mes Groupes", FontImage.MATERIAL_EVENT, e->{
            new MembreGroupesInterface().show();
       });

         this.getToolbar().addMaterialCommandToSideMenu("Mes Evènements", FontImage.MATERIAL_EVENT, e->{
            new EvenementAfficherInterface().show();

            
       });
         
         this.getToolbar().addMaterialCommandToSideMenu("Inbox", FontImage.MATERIAL_MESSAGE, e->{
            new MessageInboxInterface().show();

            
       });
         
         this.getToolbar().addMaterialCommandToSideMenu("Mes Offres", FontImage.MATERIAL_PERSON, e->{
            new OffreInterface().show();

            
       });
         
         this.getToolbar().addMaterialCommandToSideMenu("Statistiques", FontImage.MATERIAL_GRAPHIC_EQ, e->{
            new StatistiqueInterface().show();

            
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
    
//stat--------------------------------------------------------------------------------------------------------
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(50);
    renderer.setMargins(new int[]{60, 90, 45, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    
    series.add("Ouvert",values[0]);
    series.add("Fermé",values[1]);

    return series;
}

public ChartComponent createPieChartForm(double[] values) {
    
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.rgb(254, 187, 57)/*BLUE*/, ColorUtil.rgb(57, 215, 254)/*GREEN*/, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    /*renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);*/
    renderer.setLabelsColor(ColorUtil.BLACK);
    renderer.setChartTitleTextSize(100);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    /*r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);*/
    r.setHighlighted(true);
    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Groupes oUvert(bleu),fermé(vert)", values), renderer);
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);
    
    return c;

}

//notification 
private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ADD_ALARM, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.showDelayed(LEFT);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }

//////-- DON'T EDIT BELOW THIS LINE!!!


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
