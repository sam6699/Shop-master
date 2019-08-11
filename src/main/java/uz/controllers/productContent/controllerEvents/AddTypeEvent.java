package uz.controllers.productContent.controllerEvents;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 17.03.2019.
 */
public class AddTypeEvent extends Event {
    public static final EventType<AddTypeEvent> ANY =
            new EventType<>(Event.ANY,"ADD_PRODUCT_TYPE");
    public AddTypeEvent(@NamedArg("eventType") EventType<? extends Event> eventType){super(eventType);}
}
