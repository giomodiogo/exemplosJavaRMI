package aula.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import aula.rmi.remotebase.IRemoteCalculadora;

/**
 * 
 * @author Diogo Giomo
 *
 */
public class Cliente1 {

	public static void main(String[] args) {
		try {
			System.out.println("Registrando-se no servidor...");
			Registry registry = LocateRegistry.getRegistry(9876);// null == localhost

			// Recupera o objeto
			IRemoteCalculadora stub = (IRemoteCalculadora) registry.lookup("servidor_aula");

			// Chama o metodo
			double resultado = stub.calcula(2.0, '+', 3.0);

			System.out.println("Resposta do servidor: " + resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
