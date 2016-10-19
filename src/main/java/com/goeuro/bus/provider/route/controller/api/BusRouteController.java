package com.goeuro.bus.provider.route.controller.api;

import com.goeuro.bus.provider.route.model.api.DirectRouteResponse;
import com.goeuro.bus.provider.route.service.BusRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for bus routes operations
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
@Controller
@RequestMapping("/api")
public class BusRouteController {
    private static Logger log = LoggerFactory.getLogger(BusRouteController.class);

    @Autowired
    private BusRouteService busRouteService;

    @RequestMapping(value = "direct", method = RequestMethod.GET)
    public
    @ResponseBody
    DirectRouteResponse checkDirectConnection(@RequestParam("dep_sid") int departureId,
                                              @RequestParam("arr_sid") int arrivalId) {
        log.info("Received connected connection request for departureId: {}, arrivalId: {}", departureId, arrivalId);

        boolean connectionExists = busRouteService.directRouteExists(departureId, arrivalId);

        log.info("Direct connection departureId: {}, arrivalId: {} exists: {}", departureId, arrivalId, connectionExists);
        return new DirectRouteResponse(departureId, arrivalId, connectionExists);
    }
}
