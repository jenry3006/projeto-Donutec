package com.jenry.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "O sabor de cobertura não pode estar vazio!")
	private String saborCobertura;

	@NotNull(message = "Valor não pode ser null!")
	private BigDecimal valor;

	@NotEmpty(message = "A descrição não pode estar vazia!")
	private String descricao;
	
	private String obs;
	
	
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSaborCobertura() {
		return saborCobertura;
	}
	public void setSaborCobertura(String saborCobertura) {
		this.saborCobertura = saborCobertura;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
