package WiederholungBezirke;

import java.util.Comparator;

public class Bezirk implements Comparable<Bezirk> {
    private int kennzeichen;
    private String bezirksname;
    private int einwohnerzahl;


    public Bezirk(int kennzeichen, String bezirksname, int einwohnerzahl) {
        this.kennzeichen = kennzeichen;
        this.bezirksname = bezirksname;
        this.einwohnerzahl = einwohnerzahl;
    }

    public String getBezirksname() {
        return bezirksname;
    }

    public double getEinwohnerzahl() {
        return einwohnerzahl;
    }

    @Override
    public String toString() {
        return "Bezirk{" +
                "kennzeichen=" + kennzeichen +
                ", bezirksname='" + bezirksname + '\'' +
                ", einwohnerzahl=" + einwohnerzahl +
                '}';
    }


    @Override
    public int compareTo(Bezirk o) {
        return bezirksname.compareTo(o.getBezirksname());
    }
}
