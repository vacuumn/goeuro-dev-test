package com.goeuro.bus.provider.route.model;

import java.util.Set;

/**
 * Single route data.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
public class Route {
    public final int id;
    public final Set<Integer> stations;

    public Route(int id, Set<Integer> stations) {
        this.id = id;
        this.stations = stations;
    }
}
