package com.goeuro.bus.provider.route.exception;

/**
 * Exception for any kind of routes related errors.
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
public class RoutesException extends Exception {
    public RoutesException(String message, Throwable cause) {
        super(message, cause);
    }
}
