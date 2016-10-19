package com.goeuro.bus.provider.route.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API model for stations connection endpoint.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
public class DirectRouteResponse {

    @JsonProperty("dep_sid")
    public final int departureId;
    @JsonProperty("arr_sid")
    public final int arrivalId;
    @JsonProperty("direct_bus_route")
    public final boolean connected;

    public DirectRouteResponse(int departureId, int arrivalId, boolean connected) {
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.connected = connected;
    }
}
