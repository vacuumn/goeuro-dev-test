package com.goeuro.bus.provider.route.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Routes data.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
public class Routes {
    public final List<Route> routes = new ArrayList<>();
    public final String provider;

    public Routes(String provider) {
        this.provider = provider;
    }

    public void addRoute(Route route) {
         this.routes.add(route);
    }
}
