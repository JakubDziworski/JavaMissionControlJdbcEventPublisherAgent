package com.jakubdziworski.jfr;

import com.oracle.jrockit.jfr.EventToken;
import com.oracle.jrockit.jfr.Producer;
import com.sun.management.HotSpotDiagnosticMXBean;

import java.lang.management.ManagementFactory;

import static sun.misc.Version.print;

/**
 * Created by Jakub Dziworski on 17.10.16
 */

public class EventProducer {
    private static final EventToken myToken;
    private static final Producer myProducer;

    static {
        HotSpotDiagnosticMXBean hsd = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        hsd.setVMOption("UnlockCommercialFeatures", "true");
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
            return new Producer("JDBC Producer", "Jdbc events producer", "http://www.example.com/jdbc");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JdbcEvent startJdbcQuery(String query) {
        JdbcEvent jdbcEvent = new JdbcEvent(myToken);
        jdbcEvent.begin();
        jdbcEvent.setStatement(query);
        return jdbcEvent;
    }

    public static void endJdbcEvent(JdbcEvent jdbcEvent) {
        System.out.println("Ending " + jdbcEvent);
        jdbcEvent.end();
        jdbcEvent.commit();
    }


}
