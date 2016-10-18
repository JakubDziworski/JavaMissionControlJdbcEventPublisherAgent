package com.jakubdziworski.agent;

import com.jakubdziworski.jfr.EventProducer;
import com.jakubdziworski.jfr.JdbcEvent;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.Callable;
/**
 * Created by Jakub Dziworski on 17.10.16
 */
public class ExecutePreparedStatementImplementation {

    private Random random = new Random();
    @RuntimeType
    public Object execute(@This Object preparedStatement, @SuperCall Callable<?> superMethod) throws Exception {
        System.out.println("before jdbc statement execution");
        JdbcEvent jdbcEvent = EventProducer.startJdbcQuery(preparedStatement.toString());
        Thread.sleep(random.nextInt(3000));
        Object originalCall = superMethod.call();
        EventProducer.endJdbcEvent(jdbcEvent);
        System.out.println("after jdbc statement execution");
        return originalCall;
    }
}
