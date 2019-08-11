package uz.controllers.companyContent.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 24.03.2019.
 */
public class AddCompanyEvent extends Event {
    public static final EventType<AddCompanyEvent> ANY =
            new EventType<>(Event.ANY, "Add_Company_EVENT");
    public AddCompanyEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }
}
