/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.vistaadmin;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Paola
 */
public class GraficoDePie {
    
    private final JPanel contentPane;
    private final moduloadmin outer;

    public GraficoDePie(String titulo, final moduloadmin outer) {
        this.outer = outer;
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, titulo);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(201, 195));
        // Agregar el gráfico de pie al jPanel6
        contentPane = chartPanel; // Asigna el panel al atributo contentPane
    }

    public JPanel getContentPane() {
        return contentPane; // Devuelve el panel como contenedor
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // Calcular la cantidad de géneros "F" y "M"
        int cantidadGeneroF = 0;
        int cantidadGeneroM = 0;
        for (Profesor p : proyecto.shared.DataStorage.ListaProfe) {
            if ("F".equalsIgnoreCase(p.getGenero())) {
                cantidadGeneroF++;
            } else if ("M".equalsIgnoreCase(p.getGenero())) {
                cantidadGeneroM++;
            }
        }
        dataset.setValue("Femenino", cantidadGeneroF);
        dataset.setValue("Masculino", cantidadGeneroM);
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset, String titulo) {
        JFreeChart chart = ChartFactory.createPieChart(titulo, // Título del gráfico
        dataset, // Dataset
        true, // Mostrar leyenda
        true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Femenino", java.awt.Color.PINK); // Color para la sección "Femenino"
        plot.setSectionPaint("Masculino", java.awt.Color.BLUE); // Color para la sección "Masculino"
        return chart;
    }
    
}
