package WiederholungPoints;

import javax.swing.text.html.Option;
import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        rightDataInNewFile("src/main/resources/WiederholungPoints/punkte.dat");
        System.out.println("------------------------------------------------\n");


        Optional<Map<Integer, List<Point>>> optional=   allPoints("src/main/resources/WiederholungPoints/newPunkte.dat");
        Map<Integer, List<Point>> points=optional.orElseThrow();
        points.forEach((integer, point) -> {
            System.out.println("Quadrant: "+integer);
            point.forEach(point1 -> System.out.println( point1));
        });
    }


    static public int getRightQuadrant(double a, double b) {
        if (a > 0 && b > 0) {
            return 1;
        } else if (a > 0 && b < 0) {
            return 4;
        } else if (a < 0 && b > 0) {
            return 2;
        } else {
            return 3;
        }
    }

    static public void rightDataInNewFile(String filename) {
        try (DataInputStream in = new DataInputStream(
                new FileInputStream(filename));
             DataOutputStream out = new DataOutputStream(
                     new FileOutputStream("src/main/resources/WiederholungPoints/newPunkte.dat"));
        ) {


            while (in.available() > 0) {
                int quadrant = in.readInt();
                double x = in.readDouble();
                double y = in.readDouble();

                System.out.println("X:" + x + " Y:" + y + " Q:" + quadrant);
                quadrant = getRightQuadrant(x, y);

                out.writeInt(quadrant);
                out.writeDouble(x);
                out.writeDouble(y);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Optional<Map<Integer, List<Point>>>allPoints(String file) {
        try (DataInputStream in = new DataInputStream(
                new FileInputStream(file))) {
            Map<Integer, List<Point>> mapPoints = new TreeMap<>();

            while (in.available() > 0) {
                int quadrant = in.readInt();
                double x = in.readDouble();
                double y = in.readDouble();

                if (!mapPoints.containsKey(quadrant)) {
                    mapPoints.put(quadrant, new ArrayList<>());
                }
                mapPoints.get(quadrant).add(new Point(quadrant, x, y));
            }
            return Optional.of(mapPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
