package com.goeuro.bus.provider.route.service;

import com.goeuro.bus.provider.route.model.Route;
import com.goeuro.bus.provider.route.model.Routes;
import com.google.common.collect.Sets;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
@RunWith(JUnitParamsRunner.class)
public class BusRouteServiceTest {

    BusRouteService busRouteService;

    @Before
    public void setUp() throws Exception {
        Routes testRoutes = new Routes("BusRouteServiceTest");
        testRoutes.addRoute(new Route(1, Sets.newHashSet(1,2,3,4,5,6)));
        testRoutes.addRoute(new Route(2, Sets.newHashSet(5, 142, 106, 11)));
        testRoutes.addRoute(new Route(10, Sets.newHashSet(153, 121, 114, 150, 5)));
        busRouteService = new BusRouteService(testRoutes);
    }

    @Test
    @Parameters({"1,2, true",
                 "1, 1, true",
                 "106, 142, true",
                 "11, 1, false",
                 "5, 153, true",
                 "5, 22, false"})
    public void testDirectRouteExists(int departureId, int arrivalId, boolean expectedResult) throws Exception {
        boolean result = busRouteService.directRouteExists(departureId, arrivalId);
        assertEquals(expectedResult, result);
    }

}