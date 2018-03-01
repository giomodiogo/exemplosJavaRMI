package com.banco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private BigDecimal saldo = BigDecimal.ZERO;
	private BigDecimal saquesAcumulados = BigDecimal.ZERO;
	private Instant ultimoSaque;

	public Usuario() {

	}

	public Usuario(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	
	public BigDecimal getSaquesAcumulados() {
		return saquesAcumulados;
	}

	public void setSaquesAcumulados(BigDecimal saquesAcumulados) {
		this.saquesAcumulados = saquesAcumulados;
	}

	public Instant getUltimoSaque() {
		return ultimoSaque;
	}

	public void setUltimoSaque(Instant ultimoSaque) {
		this.ultimoSaque = ultimoSaque;
	}

	@Override
	public boolean equals(Object obj) {
		Usuario usuario = (Usuario) obj;
		return usuario.getId() == id;
	}
}
