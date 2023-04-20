package pt.ipb.dsys.class_exercise.classes;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import pt.ipb.dsys.class_exercise.interfaces.CpuProvider;
import pt.ipb.dsys.class_exercise.interfaces.Task;

public class CpuEngine implements CpuProvider {
    protected static int REGISTRY_PORT = 1099;
    protected static String STUB_NAME = "CpuProvider";

    public static void main(String[] args) {
        try {
            CpuProvider engine = new CpuEngine();
            CpuProvider stub = (CpuProvider) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
            registry.bind(STUB_NAME, stub);
            System.out.println("Stub \'" + STUB_NAME + "\' registered");
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public <T> T executeTask(Task<T> t) {
        return t.process();
    }
}
