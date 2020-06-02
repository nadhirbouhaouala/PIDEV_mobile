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
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Offre;
import java.util.ArrayList;
import services.ServiceOffre;

/**
 * GUI builder created Form
 *
 * @author Wael Berrachid
 */
public class StatistiqueInterface extends com.codename1.ui.Form {

    private Resources theme;
    private int id_user_actif = 9;
    
    public StatistiqueInterface() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Statistiques");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/MembreGroupes");
        
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        cnt1.setLayout(BoxLayout.y());
        
        ServiceOffre srv = new ServiceOffre();
        ArrayList<Offre> list_o = srv.Afficher();
        
        int offreoui = 0;
        int offrenon = 0;
        int offredoute = 0;
        int nboffres = 0;
        
        tab.addTab("", cnt1);
        
        this.add(tab);
        
        for(Offre o : list_o)
        {
            nboffres++;
            
            if(o.getEtat().equals("Acceptée"))
            {
                offreoui++;
            }
            else if (o.getEtat().equals("Refusée"))
            {
                offrenon++;
            }
            else
            {
                offredoute++;
            }
        }
        
        TextComponent texte = new TextComponent();
        texte.text("On a un total de "+nboffres+" offres sur notre plateforme, réparties comme suit : ");
        texte.multiline(true);
        
        cnt1.add(texte);

        double[] values = new double[]{offreoui, offrenon, offredoute};
        cnt1.add(createPieChartForm(values));
        
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
    
    public StatistiqueInterface(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }
    
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
        renderer.setLabelsColor(ColorUtil.GRAY);
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


//////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


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
