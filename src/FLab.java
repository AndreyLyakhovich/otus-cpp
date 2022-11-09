import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.style.lines.SeriesLines;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

class FLab {

    private static double function(int n) {
        return ((2 + Math.sin((Math.PI*n)/2))*((n+2)/(n+1)));
    }
    
   /* private static double functionConvSubs(int n) {
        return ((n+2)/(n+1));
    }*/

    private static void addPoints(XYChart chart, String seriesName, int from, int to) { // Рисуем последовательность X_n
        List<Double> x = new ArrayList<>(to - from);
        List<Double> y = new ArrayList<>(to - from);
        for(int n = from; n < to; n++) {
            x.add((double) n);
            y.add(function(n));    
        }    

        XYSeries series = chart.addSeries(seriesName, x, y);
        series.setLineStyle(SeriesLines.NONE);
        series.setMarker(SeriesMarkers.CIRCLE);
    }

    private static void addPointsForConvSubs(XYChart chart, String seriesName, int from, int to) { // Рисуем сходящуюся подпоследовательность
        List<Double> x = new ArrayList<>(to - from);
        List<Double> y = new ArrayList<>(to - from);
        for(int n = from; n < to; n++) {
            x.add((double) n);
            y.add(function(n));    
        }    

        XYSeries series = chart.addSeries(seriesName, x, y);
        series.setLineStyle(SeriesLines.DASH_DOT);
        series.setMarker(SeriesMarkers.CIRCLE);             
    }                                      
     
    private static void addHorizontalLine(XYChart chart, String name, double xFrom, double xTo, double yHeight) {
        List<Double> x = List.of(xFrom, xTo);
        List<Double> y = List.of(yHeight, yHeight);
        
        XYSeries series = chart.addSeries(name, x, y);
        series.setLineStyle(SeriesLines.SOLID);
        series.setMarker(SeriesMarkers.NONE);    
    }

    
    public static void main(String[] args) {
        XYChart chart = new XYChart (1000, 400);
        addPoints(chart,"x_n", 1, 101);
        addPointsForConvSubs(chart, "x1_n", 35, 38);
        addPointsForConvSubs(chart, "x2_n", 45, 48);
        addHorizontalLine(chart, "inf_and_downLim", 1, 101, 1);
        addHorizontalLine(chart, "sup_and_topLim", 1, 101, 3);

        new SwingWrapper<>(chart).displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, "./lim", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            System.out.println("Could not save chart: " + e.getMessage());
        }
        //addHorizontalLine(chart, "inf", 1, 101, )
    }
}