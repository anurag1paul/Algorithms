package npComplete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Anurag Paul
 * Date: 10/3/18
 */
public class NearestNeighbourTsp extends TravelingSalesmanProblem {

    private static Logger logger = LoggerFactory.getLogger(NearestNeighbourTsp.class);

    public NearestNeighbourTsp(String fileName, String sep) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            numCities = Integer.parseInt(line);
            line = reader.readLine();
            while(line != null){
                String[] elements = line.split(sep);
                double x = Double.parseDouble(elements[1]);
                double y = Double.parseDouble(elements[2]);
                cities.add(new City(x, y));
                line = reader.readLine();
            }
        }catch (IOException e) {
            logger.error("File Load Error", e);
            System.exit(1);
        }
    }

    @Override
    public double getTour() {
        double tourLength = 0;
        boolean[] visited = new boolean[numCities];
        int currentCity = 0, nextCity = 0;
        visited[0] = true;
        int numVisited = 1;

        while(numVisited != numCities) {
            nextCity = getNearestNeighbour(currentCity, visited);
            tourLength += Math.sqrt(getDistance(currentCity, nextCity));
            visited[nextCity] = true;
            currentCity = nextCity;
            numVisited++;
        }

        tourLength += Math.sqrt(getDistance(nextCity, 0));

        return tourLength;
    }

    private int getNearestNeighbour(int currentCity, boolean[] visited) {
        int nextCity = -1;
        double minDistance = Double.MAX_VALUE, distance;

        for(int i=0; i<numCities; i++){
            if(!visited[i]) {
                distance = getDistance(currentCity, i);
                if(distance < minDistance) {
                    minDistance = distance;
                    nextCity = i;
                }
            }
        }

        return nextCity;
    }
}
