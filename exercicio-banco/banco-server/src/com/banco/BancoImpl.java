package com.banco;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class BancoImpl implements IRemoteBanco {

	private final static BigDecimal VALOR_MAX_SAQUE = new BigDecimal(500);
	List<Usuario> usuarios = new LinkedList<Usuario>();

	@Override
	public void depositar(BigDecimal valor, Usuario usuario) throws RemoteException {
		usuario = isExiste(usuario);
		usuario.setSaldo(usuario.getSaldo().add(valor));
	}

	@Override
	public String sacar(BigDecimal valor, Usuario usuario) throws RemoteException {
		usuario = isExiste(usuario);
		// return saqueSemControleTempo(valor, usuario);
		return saqueComControleTempo(valor, usuario);
	}

	/*
	 * Controle de saques.
	 * 
	 * Permitir sacar R$ 500 a cada 2 minutos.
	 */
	private String saqueComControleTempo(BigDecimal valor, Usuario usuario) {
		if (usuario.getUltimoSaque() != null) {
			if (usuario.getSaquesAcumulados().add(valor).compareTo(VALOR_MAX_SAQUE) > 0) {
				long minutos = Duration.between(usuario.getUltimoSaque(), Instant.now()).toMinutes();
				if (minutos > 2) {
					usuario.setSaquesAcumulados(BigDecimal.ZERO);
					return validaValor(valor, usuario);
				} else {
					return "Nos ultimos 2 minutos, seus saques sao maiores que R$ " + VALOR_MAX_SAQUE;
				}
			} else {
				// return saqueNormal(valor, usuario);
				return validaValor(valor, usuario);
			}
		} else {
			return validaValor(valor, usuario);
		}
	}

	private String saqueNormal(BigDecimal valor, Usuario usuario) {
		usuario.setSaldo(usuario.getSaldo().subtract(valor));
		usuario.setUltimoSaque(Instant.now());
		usuario.setSaquesAcumulados(usuario.getSaquesAcumulados().add(valor));
		return "Sucesso!";
	}

	private String saqueSemControleTempo(BigDecimal valor, Usuario usuario) {
		usuario.setSaldo(usuario.getSaldo().subtract(valor));
		return "Sucesso!";
	}

	private String validaValor(BigDecimal valor, Usuario usuario) {
		if (valor.compareTo(VALOR_MAX_SAQUE) <= 0) {
			if (usuario.getSaldo().compareTo(valor) < 0) {
				return "O valor do saque é maior que o saldo disp.";
			}
			return saqueNormal(valor, usuario);
		} else {
			return "O valor maximo permitido : R$ " + VALOR_MAX_SAQUE;
		}
	}

	@Override
	public BigDecimal consultar(Usuario usuario) throws RemoteException {
		usuario = isExiste(usuario);
		return usuario.getSaldo();
	}

	public Usuario isExiste(Usuario u) {
		synchronized (this) {
			if (usuarios.contains(u)) {
				return usuarios.get(usuarios.indexOf(u));
			} else {
				usuarios.add(u);
				return u;
			}
		}
	}
}
