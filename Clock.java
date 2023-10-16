/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class Clock {

    private static final int MPH = 60;
    private static final int HPD = 24;
    private int hour, minute;

    public Clock(int h, int m) {

        if (h < 0 || h > HPD - 1 || m < 0 || m > MPH - 1)
            throw new IllegalArgumentException("the arguments are outside the range");

        hour = h;
        minute = m;
    }

    public Clock(String s) {

        if (!s.matches("[0-2][0-9]:[0-5][0-9]"))
            throw new IllegalArgumentException("The string argument hasn't got the needed format");

        String h = s.substring(0, 2);
        String m = s.substring(3, 5);
        int hH = Integer.parseInt(h);
        int mM = Integer.parseInt(m);

        if (hH > HPD - 1)
            throw new IllegalArgumentException("the format mustn't exceed the range of an hour");

        hour = hH;
        minute = mM;
    }

    public String toString() {

        String h0;
        String m0;

        if (hour < 10) {
            h0 = "0";
        } else {
            h0 = "";
        }

        if (minute < 10) {
            m0 = "0";
        } else {
            m0 = "";
        }

        return h0 + hour + ":" + m0 + minute;
    }

    public boolean isEarlierThan(Clock that) {

        if (hour == that.hour)
            return (minute < that.minute);

        return (hour < that.hour);
    }

    public void tic() {

        if (minute < MPH - 1)
            minute++;
        else if (hour == HPD - 1) {
            hour = 0;
            minute = 0;
        }
        else {
            hour++;
            minute = 0;
        }
    }

    public void toc(int delta) {

        if (delta < 0) throw new IllegalArgumentException("delta must to be no negative");

        int epsilon = MPH * hour + minute;
        int x = epsilon + delta;
        int midnight = MPH * HPD;
        int newMinute;
        int newHour;

        if (x >= midnight) {
            int y = x % midnight;
            newMinute = y % MPH;
            newHour = (y - newMinute) / MPH;
        }
        else {
            newMinute = x % MPH;
            newHour = (x - newMinute) / MPH;
        }

        minute = newMinute;
        hour = newHour;
    }

    public static void main(String[] args) {

        // Clock err = new Clock(23, 90);
        Clock clock1 = new Clock(23, 21);
        Clock clock2 = new Clock("03:45");

        // StdOut.println(err);
        StdOut.println("Clock1 = " + clock1);
        StdOut.println("Clock2 = " + clock2);
        StdOut.println("is Clock1 earlier than Clock2: " + clock1.isEarlierThan(clock2));
        clock1.tic();
        StdOut.println("new value for clock1 when adding a minute: " + clock1);
        clock2.toc(30000);
        StdOut.println("new value for clock2 when adding 30000 minutes: " + clock2);
    }
}
