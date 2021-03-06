package br.com.encontro.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="tb_registro_servico")
public class Registro {
	
	@Id
	@SequenceGenerator(name="registro",sequenceName="sq_tb_registro",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="registro")
	@Column(name="id_registro")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Estado tipo;

	@CreationTimestamp
	@Column(name="dt_data_cadastro")
	private Calendar dataCadastro;
	
	@UpdateTimestamp
	@Column(name="dt_data_modificacao")
	private Calendar dataModificacao;
	
	public Registro() {
	}

	public Registro(int id, Estado tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	
	
}

enum Estado {
	
	ABERTO, FECHADO

}
