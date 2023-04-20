package pt.ipb.dsys.class_exercise.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CpuProvider extends Remote {
    <T> T executeTask(Task<T> t) throws RemoteException;
}
