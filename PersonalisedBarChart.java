/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * The {@code PersonalisedBarChart} class represents a personalised library for drawing static bar
 * chart.
 * personalised by Carlos Uriel García
 *
 * @author Kevin Wayne
 */

public class PersonalisedBarChart {

    private static final Color[] COLORS = initColors();

    private final String title;               // bar chart title
    private final String xAxisLabel;          // x-axis label
    private final String dataSource;          // data source
    private String caption;                   // caption
    private TreeMap<String, Color> colorOf;   // map category to color
    private ArrayList<String> names;          // list of bar names
    private ArrayList<Integer> values;        // list of bar values
    private ArrayList<Color> colors;          // list of bar colors

    public PersonalisedBarChart(String title, String xAxisLabel, String dataSource) {

        if (title == null) throw new IllegalArgumentException("name is null");
        if (xAxisLabel == null) throw new IllegalArgumentException("x-axis label is null");
        if (dataSource == null) throw new IllegalArgumentException("data source is null");
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
        colorOf = new TreeMap<String, Color>();
        reset();
    }

    private static Color[] initColors() {

        String[] hex20 = {
                "#2a4b59", "#295938", "#592a4b", "#59382a",
                "#475d6f", "#476f59", "#6f475d", "#6f5947",
                "#646e85", "#64857b", "#85646e", "#857b64",
                "#82809c", "#809a9c", "#9c8280", "#9a9c80",
                "#9f91b2", "#91a4b2", "#b29f91", "#a4b291",
                };

        Color[] colors = new Color[hex20.length];

        for (int i = 0; i < hex20.length; i++)
            colors[i] = Color.decode(hex20[i]);
        return colors;
    }

    public void setCaption(String caption) {
        if (caption == null) throw new IllegalArgumentException("caption is null");
        this.caption = caption;
    }

    public void add(Bar bar) {

        if (bar == null) throw new IllegalArgumentException("bar is null");

        String category = bar.getCategory();

        if (!colorOf.containsKey(category)) {
            colorOf.put(category, COLORS[colorOf.size() % COLORS.length]);
        }
        Color color = colorOf.get(category);
        String name = bar.getName();
        int value = bar.getValue();
        names.add(name);
        values.add(value);
        colors.add(color);
    }

    public void reset() {
        names = new ArrayList<String>();
        values = new ArrayList<Integer>();
        colors = new ArrayList<Color>();
        caption = "";
    }

    // compute units (multiple of 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, ...)
    // so that between 4 and 8 axes labels
    private static int getUnits(double xmax) {
        int units = 1;
        while (Math.floor(xmax / units) >= 8) {
            // hack to identify 20, 200, 2000, ...
            if (units % 9 == 2) units = units * 5 / 2;
            else units = units * 2;
        }
        return units;
    }

    public void draw() {
        // nothing to draw
        if (names.isEmpty()) return;

        // leave room for at least 8 bars
        int numberOfBars = Math.max(8, names.size());

        // set the scale of the coordinate axes
        double xmax = Double.NEGATIVE_INFINITY;
        for (int value : values) {
            if (value > xmax) xmax = value;
        }

        StdDraw.setXscale(-0.01 * xmax, 1.2 * xmax);
        StdDraw.setYscale(-0.01 * numberOfBars, numberOfBars * 1.25);

        // draw title
        StdDraw.setPenColor(0, 50, 50);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        StdDraw.text(0.6 * xmax, numberOfBars * 1.2, title);

        // draw x-axis label
        StdDraw.setPenColor(50, 0, 0);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
        StdDraw.textLeft(0, numberOfBars * 1.10, xAxisLabel);

        // draw axes
        int units = getUnits(xmax);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int unit = 0; unit <= xmax; unit += units) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.text(unit, numberOfBars * 1.02, String.format("%,d", unit));
            StdDraw.setPenColor(new Color(230, 230, 230));
            StdDraw.line(unit, 0.1, unit, numberOfBars * 1.0);
        }

        // draw caption
        StdDraw.setPenColor(0, 50, 50);
        if (caption.length() <= 4) StdDraw.setFont(new Font("SansSerif", Font.BOLD, 100));
        else if (caption.length() <= 8) StdDraw.setFont(new Font("SansSerif", Font.BOLD, 60));
        else StdDraw.setFont(new Font("SansSerif", Font.BOLD, 40));
        StdDraw.textRight(1.15 * xmax, 0.2 * numberOfBars, caption);

        // draw data source acknowledgment
        StdDraw.setPenColor(50, 0, 0);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 14));
        StdDraw.textRight(1.14 * xmax, 0.1 * numberOfBars, dataSource);

        // draw bars
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int value = values.get(i);
            Color color = colors.get(i);
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle(0.5 * value, numberOfBars - i - 0.5, 0.5 * value, 0.4);
            StdDraw.setPenColor(250, 250, 250);
            int fontSize = (int) Math.ceil(14 * 10.0 / numberOfBars);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, fontSize));
            StdDraw.textRight(value - 0.01 * xmax, numberOfBars - i - 0.5, name);
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.textLeft(value + 0.01 * xmax, numberOfBars - i - 0.5,
                             String.format("%,d", value));
        }
    }

    // sample client
    public static void main(String[] args) {
        // create the bar chart
        String title = "The 10 most populous cities";
        String xAxis = "Population (thousands)";
        String source = "Source: United Nations";
        PersonalisedBarChart chart = new PersonalisedBarChart(title, xAxis, source);
        chart.setCaption("2018");

        // add the bars to the bar chart
        Bar[] bars = new Bar[10];
        bars[0] = new Bar("Tokyo", 38194, "East Asia");
        bars[1] = new Bar("Delhi", 27890, "South Asia");
        bars[2] = new Bar("Shanghai", 25779, "East Asia");
        bars[3] = new Bar("Beijing", 22674, "East Asia");
        bars[4] = new Bar("Mumbai", 22120, "South Asia");
        bars[5] = new Bar("São Paulo", 21698, "Latin America");
        bars[6] = new Bar("Mexico City", 21520, "Latin America");
        bars[7] = new Bar("Osaka", 20409, "East Asia");
        bars[8] = new Bar("Cairo", 19850, "Middle East");
        bars[9] = new Bar("Dhaka", 19633, "South Asia");

        Arrays.sort(bars);

        for (int i = 0; i < 10; i++)
            chart.add(bars[i]);

        // draw the bar chart
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        chart.draw();
        StdDraw.show();
    }
}
