package npComplete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Anurag Paul
 * Date: 28/2/18
 */
public abstract class TravelingSalesmanProblem {

    private static Logger logger = LoggerFactory.getLogger(TravelingSalesmanProblem.class);

    private static class City {
        double x;
        double y;

        City(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private int numCities;
    private List<City> cities = new ArrayList<>();
    private double[][] matrix;

    public TravelingSalesmanProblem(String fileName, String sep) {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            numCities = Integer.parseInt(line);
            line = reader.readLine();
            while(line != null){
                String[] elements = line.split(sep);
                double x = Double.parseDouble(elements[0]);
                double y = Double.parseDouble(elements[0]);
                cities.add(new City(x, y));
                line = reader.readLine();
            }
        }catch (IOException e) {
            logger.error("File Load Error", e);
            System.exit(1);
        }

        matrix = getDistanceMatrix();
    }

    public abstract double getTour();

    private double[][] getDistanceMatrix() {
        double[][] matrix = new double[numCities][numCities];
        for(int i=0; i<numCities; i++) {
            for(int j=i+1; j<numCities; j++) {
                matrix[i][j] = matrix[j][i] = getDistance(cities.get(i), cities.get(j));
            }
        }
        return matrix;
    }

    private double getDistance(City origin, City destination) {
        return Math.sqrt(Math.pow((origin.x - destination.x), 2) + Math.pow((origin.y - destination.y), 2));
    }
}
