package pt.ipb.dsys.class_exercise.classes;

import java.rmi.registry.Registry;

import pt.ipb.dsys.class_exercise.interfaces.CpuProvider;

import java.rmi.registry.LocateRegistry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(CpuEngine.REGISTRY_PORT);
            CpuProvider provider = (CpuProvider) registry.lookup(CpuEngine.STUB_NAME);
            WordSort task1 = new WordSort("The quick brown fox jumps over the lazy dog");
            WordShuffler task2 = new WordShuffler("The quick brown fox jumps over the lazy dog");
            String processResult1 = provider.executeTask(task1);
            System.out.println(processResult1);
            String processResult2 = provider.executeTask(task2);
            System.out.println(processResult2);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
