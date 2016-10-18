package com.jakubdziworski.agent;

import com.sun.tools.attach.VirtualMachine;

/**
 * Created by 'Jakub Dziworski' on 18.10.16
 */
public class RuntimeAttacher {
    public static void main(String[] args) throws Exception {
        String javaProcessId = args[0];
        String agentJarLocation = args[1];
        VirtualMachine vm = VirtualMachine.attach(javaProcessId);
        vm.loadAgent(agentJarLocation,"");
        System.out.println("Attached to remote jvm!");
        vm.detach();
        System.out.println("Done. Detaching");
    }
}
