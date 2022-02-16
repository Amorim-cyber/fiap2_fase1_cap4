package br.com.encontro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_morada")
public class Morada {
	
	@Id
	@SequenceGenerator(name="morada",sequenceName="sq_tb_morada",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="morada")
	@Column(name="id_morada")
	private int id;
	
	@Column(name="nr_morada",nullable=false)
	private int numero;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_morada")
	private Estrutura estrutura;
	
	public Morada() {
	}

	public Morada(int id, int numero, Estrutura estrutura) {
		this.id = id;
		this.numero = numero;
		this.estrutura = estrutura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Estrutura getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

}
