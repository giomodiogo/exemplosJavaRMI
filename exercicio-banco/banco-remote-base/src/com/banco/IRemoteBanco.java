package com.banco;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteBanco extends Remote {

	public void depositar(BigDecimal valor, Usuario usuario) throws RemoteException;

	public String sacar(BigDecimal valor, Usuario usuario) throws RemoteException;

	public BigDecimal consultar(Usuario usuario) throws RemoteException;

}
