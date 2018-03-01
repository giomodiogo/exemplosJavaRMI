package com.banco;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
	public static void main(String[] args) {
		try {
			System.out.println("Registrando-se no servidor...");
			Registry registry = LocateRegistry.getRegistry(9876);

			IRemoteBanco stub = (IRemoteBanco) registry.lookup("banco");

			Usuario u1 = new Usuario(1, "Usuario 1");
			// stub.depositar(new BigDecimal(1000), u1);
			System.out.println(stub.sacar(new BigDecimal(100), u1));
			System.out.println(u1.getNome() + ": " + stub.consultar(u1));

			System.out.println("\n********\n");

			Usuario u2 = new Usuario(2, "Usuario 2");
			// stub.depositar(new BigDecimal(1000), u2);
			System.out.println(stub.sacar(new BigDecimal(300), u2));
			System.out.println(u2.getNome() + ": " + stub.consultar(u2));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
