package com.jakubdziworski.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Transformer;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.sql.PreparedStatement;

import static net.bytebuddy.matcher.ElementMatchers.named;
/**
 * Created by Jakub Dziworski on 17.10.16
 */
public class Agent {

    public static void agentmain(String arguments, Instrumentation instrumentation) {
        System.out.println("agent sucesfully attached at runtime!");
        instrument(arguments,instrumentation);
    }

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("agent sucesfully attached at startup!");
        instrument(arguments,instrumentation);
    }

    private static void instrument(String arguments, Instrumentation instrumentation) {
        System.out.println("started instrumenting");
        ExecutePreparedStatementImplementation preparedStmtImplementation = new ExecutePreparedStatementImplementation();
        Transformer transformer = (builder, typeDescription, classLoader) -> {
            return builder.method(named("executeQuery").or(named("executeUpdate")))
                    .intercept(MethodDelegation.to(preparedStmtImplementation));
        };
        new AgentBuilder.Default()
                .type(ElementMatchers.isSubTypeOf(PreparedStatement.class))
                .transform(transformer)
                .installOn(instrumentation);
    }
}
