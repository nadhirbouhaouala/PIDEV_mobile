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
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Offre;
import java.util.ArrayList;
import services.ServiceOffre;
import utils.Session;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class StatistiqueInterface extends com.codename1.ui.Form {

    Session s = new Session();
    private Resources theme;
    private int id_user_actif = s.getId_Session();
    
    public StatistiqueInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Statistiques");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        cnt1.setLayout(BoxLayout.y());
        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        cnt2.setLayout(BoxLayout.y());
        
        ServiceOffre srv = new ServiceOffre();
        ArrayList<Offre> list_o = srv.Afficher();
        
        int offreoui = 0;
        int offrenon = 0;
        int offredoute = 0;
        int nboffres = 0;
        
        int offreouim = 0;
        int offrenonm = 0;
        int offredoutem = 0;
        int nboffresm = 0;
        
        tab.addTab("Global", cnt1);
        tab.addTab("Partiel", cnt2);
        
        this.add(tab);
        
        for(Offre o : list_o)
        {
            nboffres++;
            
            if(o.getIdRecepteur() == id_user_actif)
            {
                nboffresm++;
            }
            
            if(o.getEtat().equals("Acceptée"))
            {
                offreoui++;
                if(o.getIdRecepteur() == id_user_actif)
                {
                    offreouim++;
                }
            }
            else if (o.getEtat().equals("Refusée"))
            {
                offrenon++;
                if(o.getIdRecepteur() == id_user_actif)
                {
                    offrenonm++;
                }
            }
            else
            {
                offredoute++;
                if(o.getIdRecepteur() == id_user_actif)
                {
                    offredoutem++;
                }
            }
        }
        
        TextComponent texte = new TextComponent();
        texte.text("On a un total de "+nboffres+" offres sur notre plateforme, réparties comme suit : ");
        texte.multiline(true);
        
        cnt1.add(texte);

        double[] values = new double[]{offreoui, offrenon, offredoute};
        cnt1.add(createPieChartForm(values));
        
        TextComponent textem = new TextComponent();
        textem.text("Vous avez reçu "+nboffresm+" offres sur notre plateforme, réparties comme suit : ");
        textem.multiline(true);
        
        cnt2.add(textem);
        
        double[] valuesm = new double[]{offreouim, offrenonm, offredoutem};
        cnt2.add(createPieChartForm(valuesm));
        
        this.getToolbar().addCommandToOverflowMenu("Déconnexion", theme.getImage("back-command.png"), ev->{
               new MembreLoginInterface().show();
            });
        
        // Sidebar
        Image icon = theme.getImage("logoo.jpg");
        Container topBar = BorderLayout.center(new Label(icon));
        this.getToolbar().addMaterialCommandToSideMenu("Mon Profil", FontImage.MATERIAL_HOME, e->{
            new MembreAfficherInterface().show();
            
       });


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
         
        this.getToolbar().addComponentToSideMenu(topBar);
        //fin sidebar
    }
    
    public StatistiqueInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(0);
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

        series.add("Acceptée",values[0]);
        series.add("Refusée",values[1]);
        series.add("En attente",values[2]);

        return series;
    }

    public ChartComponent createPieChartForm(double[] values) {

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        /*renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);*/
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setChartTitleTextSize(100);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Offres Stats", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        return c;

    }


////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("StatistiqueInterface");
        setName("StatistiqueInterface");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
