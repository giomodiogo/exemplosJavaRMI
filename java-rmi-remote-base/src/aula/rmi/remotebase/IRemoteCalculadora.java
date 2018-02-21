package aula.rmi.remotebase;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Diogo Giomo
 * 
 *         Interface remota, comum ao Client e Server.
 *
 */
public interface IRemoteCalculadora extends Remote {

	public double calcula(double num1, char operacao, double num2) throws RemoteException;

}
