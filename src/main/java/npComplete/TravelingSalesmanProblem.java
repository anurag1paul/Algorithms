package npComplete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anurag Paul
 * Date: 28/2/18
 */
public abstract class TravelingSalesmanProblem {

    private static Logger logger = LoggerFactory.getLogger(TravelingSalesmanProblem.class);

    public static class City {
        double x;
        double y;

        City(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%f, %f)" ,x ,y);
        }
    }

    protected int numCities;
    protected List<City> cities = new ArrayList<>();
    protected double[][] matrix;

    public TravelingSalesmanProblem() {
    }

    public abstract double getTour();

    protected double[][] getDistanceMatrix() {

        double[][] matrix = new double[numCities][numCities];

        for(int i=0; i<numCities; i++) {
            for(int j=i+1; j<numCities; j++) {
                matrix[i][j] = matrix[j][i] = Math.sqrt(getDistance(i, j));
            }
        }

        return matrix;
    }

    protected double getDistance(City origin, City destination) {
        return Math.pow((origin.x - destination.x), 2) + Math.pow((origin.y - destination.y), 2);
    }

    protected double getDistance(int origin, int destination) {
        return getDistance(cities.get(origin), cities.get(destination));
    }
}
