package com.banco;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Banco {

	public static void main(String[] args) {
		try {
			System.out.println("Construindo Servidor...");

			BancoImpl banco = new BancoImpl();

			IRemoteBanco stub = (IRemoteBanco) UnicastRemoteObject.exportObject(banco, 0);

			System.out.println("Registrando Servidor...");

			Registry registry = LocateRegistry.getRegistry(9876);// porta do rmiregistry

			registry.bind("banco", stub);

			System.out.println("Servidor iniciado!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
