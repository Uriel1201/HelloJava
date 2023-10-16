import java.util.Arrays;

public class BarChartRacer {

    public static void main(String[] args) {

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1000, 700);

        In input0 = new In(args[0]);
        int maxBars = Integer.parseInt(args[1]);

        String title = input0.readLine();
        String xAxis = input0.readLine();
        String source = input0.readLine();

        BarChart chart = new BarChart(title, xAxis, source);

        input0.readLine();

        while (input0.hasNextLine()) {

            String caption = "";
            int numberOfBars = Integer.parseInt(input0.readLine());
            Bar[] bars = new Bar[numberOfBars];

            for (int i = 0; i < numberOfBars; i++) {
                String line = input0.readLine();
                String[] param = line.split(",");
                caption = param[0];
                String name = param[1];
                int value = Integer.parseInt(param[3]);
                String category = param[4];
                bars[i] = new Bar(name, value, category);
            }

            Arrays.sort(bars);

            chart.setCaption(caption);

            if (maxBars > numberOfBars) {
                for (int i = numberOfBars - 1; i > -1; i--)
                    chart.add(bars[i].getName(), bars[i].getValue(), bars[i].getCategory());
            } else {
                for (int i = numberOfBars - 1; i > numberOfBars - 1 - maxBars; i--)
                   chart.add(bars[i].getName(), bars[i].getValue(), bars[i].getCategory()); 
            }

            StdDraw.clear();
            chart.draw();
            StdDraw.show();
            StdDraw.pause(300);
            chart.reset();
            input0.readLine();
        }
    }
}
