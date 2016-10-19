package com.goeuro.bus.provider.route;

import com.goeuro.bus.provider.route.exception.RoutesException;
import com.goeuro.bus.provider.route.model.Routes;

/**
 * Bus routes data loader.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
public interface IBusRouteLoader {

    /**
     * Load data about bus routes.
     *
     * @return routes data holder
     * @throws RoutesException if error occurred while loading data
     */
    Routes loadBusRoutes() throws RoutesException;
}
