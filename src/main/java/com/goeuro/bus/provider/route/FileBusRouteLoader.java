package com.goeuro.bus.provider.route;

import com.goeuro.bus.provider.route.exception.RoutesException;
import com.goeuro.bus.provider.route.model.Route;
import com.goeuro.bus.provider.route.model.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Bus routes data loader that read data from file in next format:
 *
 * The first line of the data gives you the number N of bus routes, followed by N bus routes.
 * For each bus route there will be one line containing a space separated list of integers.
 * This list contains at least three integers. The first integer represents the bus route id.
 * The bus route id is unique among all other bus route ids in the input.
 * The remaining integers in the list represent a list of station ids.
 * A station id may occur in multiple bus routes, but can never occur twice within the same bus route.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
public class FileBusRouteLoader implements IBusRouteLoader {

    private static Logger log = LoggerFactory.getLogger(FileBusRouteLoader.class);

    private final String filePath;

    public FileBusRouteLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Routes loadBusRoutes() throws RoutesException {
        log.info("Started bus routes loading from data file...");
        Routes routes = new Routes(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                String routeLine = br.readLine();
                String[] numbers = routeLine.split("\\s+");
                int routeId = Integer.parseInt(numbers[0]);
                Set<Integer> stations = new HashSet<>();
                for (int j = 1; j < numbers.length; j++) {
                    stations.add(Integer.parseInt(numbers[j]));
                }
                routes.addRoute(new Route(routeId, stations));
            }
        } catch (Exception e) {
            log.error("Failed to load bus routes from data file: {}", filePath);
            throw new RoutesException("Failed to load bus routes from data file", e);
        }
        return routes;
    }
}
