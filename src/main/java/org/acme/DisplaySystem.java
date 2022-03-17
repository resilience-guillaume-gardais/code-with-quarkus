package org.acme;

import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class DisplaySystem {
    private static final Logger LOGGER = Logger.getLogger("System");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
        AtomicInteger len = new AtomicInteger();
        System.getProperties()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getKey().toString().length(), e1.getKey().toString().length()))
                .peek(e -> len.set(Math.max(len.get(), e.getKey().toString().length())))
                .map(e -> String.format("%-" + len.get() + "s: %s", e.getKey(), e.getValue()))
                .sorted()
                .forEach(System.out::println);
    }
}
