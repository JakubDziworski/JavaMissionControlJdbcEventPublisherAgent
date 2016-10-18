package com.jakubdziworski.agent;

import com.jakubdziworski.jfr.EventProducer;
import com.jakubdziworski.jfr.JdbcEvent;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
/**
 * Created by Jakub Dziworski on 17.10.16
 */
public class ExecutePreparedStatementImplementation {
    @RuntimeType
    public Object execute(@This Object preparedStatement, @SuperCall Callable<?> superMethod) throws Exception {
        System.out.println("executing");
        JdbcEvent jdbcEvent = EventProducer.startJdbcQuery(preparedStatement.toString());
        Object originalCall = superMethod.call();
        EventProducer.endJdbcEvent(jdbcEvent);
        System.out.println("after execution");
        return originalCall;
    }
}
