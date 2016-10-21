# Java Mission Control Jdbc JFR Event Publisher Agent

Quick dictionary if you are not familiar with those terms:

* Java Mission Control - tool for monitoring jvm apps
* JFR (Java Flight Recording) - module of Java Mission Control that allows you to "record" events and browse them afterwards.
* Java Agent - It allows you to start or even connect to running jvm and change bytecode at runtime! This allows you
to use this extension and start publishing events on already running jvm any time.

## Running

**Note: Works only with Oracle JRE!**

First build it.

```mvn clean package```

### Option 1 - Start agent with the jvm
Just add `-javaagent $THIS_REPO_ROOT/agent/target/agent-1.0-SNAPSHOT-jar-with-dependencies.jar` to your  app's jvm arguments
and start it

### Option 2 - Attach to the running jvm
```bash 
mvn clean package
java -classpath agent/target/agent-1.0-SNAPSHOT-jar-with-dependencies.jar:jfr/target/jfr-1.0-SNAPSHOT.jar:/usr/jdk1.8.0_71/lib/tools.jar com.jakubdziworski.agent.RuntimeAttacher <PROCESS_ID> agent/target/agent-1.0-SNAPSHOT-jar-with-dependencies.jar

```

Start new fligh recording in jmc. **Remember to enable custom event in "Start Recording Settings Wizard"!!**

## Demo

[![Demo](https://img.youtube.com/vi/ymaV3iTMNI8/0.jpg)](https://www.youtube.com/watch?v=ymaV3iTMNI8)
