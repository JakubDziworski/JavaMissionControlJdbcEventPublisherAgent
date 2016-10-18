package com.jakubdziworski.jfr;

import com.oracle.jrockit.jfr.DurationEvent;
import com.oracle.jrockit.jfr.EventDefinition;
import com.oracle.jrockit.jfr.EventToken;
import com.oracle.jrockit.jfr.ValueDefinition;


/**
 * Created by Jakub Dziworski on 17.10.16
 */
@EventDefinition(path = "JakubDziworski/JdbcEvent", name = "Jdbc Event", description = "An event triggered by executiing jdbc query or update.", stacktrace = true, thread = true)
public class JdbcEvent extends DurationEvent {
    @ValueDefinition(name = "Message", description = "The logged important stuff.")
    private String query;

    public JdbcEvent(EventToken eventToken) {
        super(eventToken);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}


