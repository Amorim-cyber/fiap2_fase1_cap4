package br.com.encontro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_condominio")
public class Condominio {

	@Id
	@SequenceGenerator(name="condominio",sequenceName="sq_tb_condominio",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="condominio")
	@Column(name="id_condominio")
	private int id;
	
	
	@Column(name="nm_condominio",nullable=false,length=100)
	private String nome;
	
	
	@Column(name="endereco",nullable=false,length=200)
	private String endereco;

	public Condominio() {
	}

	public Condominio(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
