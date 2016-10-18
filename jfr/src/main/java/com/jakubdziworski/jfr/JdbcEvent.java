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
    @ValueDefinition(name = "Statement", description = "The logged important stuff.")
    private String statement;

    public JdbcEvent(EventToken eventToken) {
        super(eventToken);
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}


