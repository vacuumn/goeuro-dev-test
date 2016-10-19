package com.goeuro;

import com.goeuro.bus.provider.route.FileBusRouteLoader;
import com.goeuro.bus.provider.route.IBusRouteLoader;
import com.goeuro.bus.provider.route.exception.RoutesException;
import com.goeuro.bus.provider.route.model.Routes;
import com.goeuro.bus.provider.route.service.BusRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
* Entry point for bus route service app.
*
* Should be executed with one argument which indicates routes data file path.
*
* It will tries to read and parse data from file, is succeeded - will start REST service, if no - fail with error.
*/
@SpringBootApplication
public class BusRouteServiceRunner {

    private static Logger log = LoggerFactory.getLogger(BusRouteServiceRunner.class);
    public static Routes routes;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar bus-route.jar FILE, where FILE - path to a bus routes data file");
            System.exit(1);
        }

        //Try to parse file with routes, if error occurred - no reason to start context
        routes = parseRoutes(args[0]);

		SpringApplication.run(BusRouteServiceRunner.class, args);
	}

	@Bean
    public BusRouteService busRouteService() {
        return new BusRouteService(routes);
    }

    public static Routes parseRoutes(String filePath) {
        log.info("Loading routes from file: {}", filePath);
        IBusRouteLoader busLoader = new FileBusRouteLoader(filePath);
        try {
            return busLoader.loadBusRoutes();
        } catch (RoutesException e) {
            log.error("Error while parsing bus routes data file", e);
            System.exit(1);
        }
        return null;
    }
}
