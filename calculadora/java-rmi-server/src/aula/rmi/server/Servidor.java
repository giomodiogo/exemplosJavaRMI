package aula.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import aula.rmi.remotebase.IRemoteCalculadora;

/**
 * @author Diogo Giomo
 * 
 *         O RMI utiliza uma porta somente para o RMI Registry, e uma para cada
 *         stub regitrado nele, ou seja, a porta que o stub irá escutar. Se você
 *         criar o stub utilizando o método
 *         UnicastRemoteObject.exportObject(Remote objetoRemoto), este seu
 *         objetoRemoto irá escutar em uma porta anônima (aleatória), sendo
 *         assim vocÊ não irá saber que porta deve liberar no firewall. Agora,
 *         criando o stub utilizando o método
 *         UnicastRemoteObject.exportObject(Remote objetoRemoto,int porta), seu
 *         stub irá escutar na porta especificada, assim basta você liberar esta
 *         porta e a porta do RMI Registry no seu firewall. Vale lembrar que a
 *         porta que você especificar para o stub não pode ser 0 (zero), pois se
 *         for, seu stab irá escutar em uma porta aleatória, ou seja
 *         UnicastRemoteObject.exportObject(Remote objetoRemoto) é igual a
 *         UnicastRemoteObject.exportObject(Remote objetoRemoto, 0).
 * 
 *         run rmiregistry 9876 (porta - opicional).
 * 
 *         rmiregistry deve ser executa junto com a interface remota (compilados
 *         do projeto), no caso do eclipse, executar dentro da pasta /bin, para
 *         os testes.
 */

public class Servidor implements IRemoteCalculadora {
	private int conexoes = 0;

	public static void main(String[] args) {
		try {
			System.out.println("Construindo Servidor...");

			Servidor servidor = new Servidor();

			// Porta do stub, se for 0, utiliza uma porta aleatoria.
			IRemoteCalculadora stub = (IRemoteCalculadora) UnicastRemoteObject.exportObject(servidor, 0);

			System.out.println("Registrando Servidor...");

			// Liga o objeto remoto (stub) no registry
			Registry registry = LocateRegistry.getRegistry(9876);// porta do rmiregistry

			registry.bind("servidor_aula", stub);

			System.out.println("Servidor iniciado!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double calcula(double num1, char operacao, double num2) throws RemoteException {
		conexoes++;
		System.out.println("Requisitando método calcula do Servidor: " + conexoes);

		double resultado = 0.0;
		switch (operacao) {
		case '+':
			resultado = num1 + num2;
			break;
		case '-':
			resultado = num1 - num2;
			break;
		case '*':
			resultado = num1 * num2;
			break;
		case '/':
			resultado = num1 / num2;
			break;

		}
		return resultado;
	}

}
