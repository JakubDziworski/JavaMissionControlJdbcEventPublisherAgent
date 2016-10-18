package com.jakubdziworski.jfr;

import com.oracle.jrockit.jfr.EventToken;
import com.oracle.jrockit.jfr.Producer;

import static sun.misc.Version.print;

/**
 * Created by Jakub Dziworski on 17.10.16
 */

public class EventProducer {
    private static final EventToken myToken;
    private static final Producer myProducer;

    static {
        myProducer = createProducer();
        myToken = createToken();
        myProducer.register();
    }

    private static EventToken createToken() {
        try {
            return myProducer.addEvent(JdbcEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Producer createProducer() {
        try {
            return new Producer("Demo Producer", "A demo event producer.", "http://www.example.com/demo/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JdbcEvent startJdbcQuery(String query) {
        JdbcEvent jdbcEvent = new JdbcEvent(myToken);
        jdbcEvent.begin();
        jdbcEvent.setQuery(query);
        return jdbcEvent;
    }

    public static void endJdbcEvent(JdbcEvent jdbcEvent) {
        System.out.println("Ending " + jdbcEvent);
        jdbcEvent.end();
        jdbcEvent.commit();
    }


}
