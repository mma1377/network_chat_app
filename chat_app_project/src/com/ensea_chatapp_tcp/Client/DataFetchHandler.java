package com.ensea_chatapp_tcp.Client;

/**
 * This is an Interface in order to Wrap the handler to data fetch.
 * For example, it can be used in order to display the data
 */
@FunctionalInterface
public interface DataFetchHandler {
    void applyFunction(String text);
}
