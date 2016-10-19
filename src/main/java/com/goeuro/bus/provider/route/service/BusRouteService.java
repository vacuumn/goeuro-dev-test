package com.goeuro.bus.provider.route.service;

import com.goeuro.bus.provider.route.model.Route;
import com.goeuro.bus.provider.route.model.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Service for bus routes operations, like checking connected connections for stations, etc.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
@Component
public class BusRouteService {
    private static Logger log = LoggerFactory.getLogger(BusRouteService.class);

    private Routes routes;

    public BusRouteService(Routes routes) {
        this.routes = routes;
    }

    public boolean directRouteExists(int departureId, int arrivalId) {
        return routes.routes.stream().anyMatch(r -> haveStations(r, departureId, arrivalId));
    }

    private boolean haveStations(Route route, int stationOne, int stationTwo) {
          return route.stations.contains(stationOne) && route.stations.contains(stationTwo);
    }
}
