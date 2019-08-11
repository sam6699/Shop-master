package uz.controllers.productContent.controllerEvents;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 17.02.2019.
 */
public class AddProductEvent extends Event {
    public static final EventType<AddProductEvent> ANY =
            new EventType<>(Event.ANY, "Add_PRODUCT_EVENT");
    public AddProductEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }
}
