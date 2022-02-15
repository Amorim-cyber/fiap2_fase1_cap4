<h1>FIAP Fase 1 : Capítulo 4 </h1>

<h3>Início</h3>

Para abstrair o conhecimento adquirido no capítulo 4 da Fase 1 desenvolvi o seguinte modelo de negócios:

Temos moradores que ocupam um determinado condomínio. Estes podem está procurando serviços domésticos de diversos tipos (elétrico, hidráulico ou mecânico) e o prestador de serviço detém a capacitação. 

A ideia é criar uma aplicação que facilite o encontro entre moradores e prestadores de serviço. E caso o morador deseje contratar o serviço haverá a criação de um registro entre as partes.

Segue abaixo a representação gráfica do modelo relacional.

<img src="assets/tabelas.PNG">

* <b>tb_condominio:</b> Tabela que vai armazenar dados de condomínio. Contém o nome e endereço do condomínio.
* <b>tb_morador:</b> Tabela que vai armazenar dados de morador. Contém nome e número da sua morada. 
* <b style="color:grey">Relação condominio_morador:</b> Um condomínio pode ser habitado por um ou mais moradores contudo um morador deve pelo menos um condomínio.
* <b>tb_condominio:</b> Um condomínio pode conter n moradores, assim como um morador pode ter posses em n condomínios. Tabela auxiliar `tb_condominio` serve para normalizar essa relação.
* <b>tb_servico:</b> Tabela que vai armazenar dados do serviço. Contém o nome do serviço.
* <b>tb_prestador:</b> Tabela que vai armazenar dados do prestador de serviço. Contém o nome, número de telefone do prestador.
* <b>tb_registro_servico:</b> Tabela que vai armazenar dados do registro de serviço. Contém a data de inicio, a data de fim de serviço e status do registro.
* <b style="color:grey">Relação servico_registro:</b> Um tipo de serviço pode ser registrado, contudo um registro deve conter um tipo de serviço.
* <b style="color:grey">Relação prestador_registro:</b> Um prestador de serviço pode ser registrado, contudo um registro deve conter um prestador de serviço.
* <b style="color:grey">Relação morador_registro:</b> Um morador pode ser registrado, contudo um registro deve conter um morador.

Conforme foi pedido na atividade vamos nos concentrar somente na criação das tabelas entidades utilizando o `Hibernate` 

<h3>Setup</h3>

Primeiramente temos que configurar nosso projeto, segue passo a passo:

<ul>
    <li>Cria o arquivo maven java</li>
    <li>Configura o arquivo pom.xml da seguinte forma:</li>
</ul>

````xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>br.com.encontro</groupId>
  <artifactId>encontro</artifactId>
  <version>0.0.1</version>
  <dependencies>
  
  	<dependency>
  		<groupId>org.hibernate</groupId>
  		<artifactId>hibernate-core</artifactId>
  		<version>RELEASE</version>
  	</dependency>
  	
  	<dependency>
  		<groupId>oracle</groupId>
  		<artifactId>jdbc-driver</artifactId>
  		<version>12</version>
  		<scope>system</scope>
  		<systemPath>C:/Users/lucca/Oracle/ojdbc8.jar</systemPath>
  	</dependency>
  
  </dependencies>
  
  
</project>
````

<ul>
    <li>Converte o arquivo para JPA</li>
    <li>Configura o arquivo persistence.xml inicialmente da seguinte forma:</li>
</ul>

````xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
	<persistence-unit name="encontro" transaction-type="RESOURCE_LOCAL">
	
		<provider>
			org.hibernate.jpa.HibernatePersistenceProvider
		</provider>
		
		<properties>
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.format_sql" value="true" />
			<property name="hibernate.hbm2ddl.auto" value="create" />
			<property name="hibernate.dialect" value="org.hibernate.dialect.Oracle12cDialect" />
			<property name="javax.persistence.jdbc.driver" value="oracle.jdbc.OracleDriver" />
			<property name="javax.persistence.jdbc.url" value="jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL" />
			<property name="javax.persistence.jdbc.user" value="XXXXX" />
			<property name="javax.persistence.jdbc.password" value="XXXXX" />
		</properties>
	
	</persistence-unit>
</persistence>
````

Feita a configuração inicial!

<img src="assets/java1.PNG">

<hr>
<h3>Criação das entidades</h3>

1. <b>Condominio.java</b>

   ````java
   package br.com.encontro.entity;
   
   import javax.persistence.Column;
   import javax.persistence.Entity;
   import javax.persistence.GeneratedValue;
   import javax.persistence.GenerationType;
   import javax.persistence.Id;
   import javax.persistence.SequenceGenerator;
   import javax.persistence.Table;
   
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
   ````

   Após criada a classe incluir a seguinte linha em `persistence.xml`

   ````xml
   <class>br.com.encontro.entity.Condominio</class>
   ````

2. <b>Morador.java</b>

   ````java
   package br.com.encontro.entity;
   
   import javax.persistence.Column;
   import javax.persistence.Entity;
   import javax.persistence.GeneratedValue;
   import javax.persistence.GenerationType;
   import javax.persistence.Id;
   import javax.persistence.SequenceGenerator;
   import javax.persistence.Table;
   
   @Entity
   @Table(name="tb_morador")
   public class Morador {
   
   	@Id
   	@SequenceGenerator(name="morador",sequenceName="sq_tb_morador",allocationSize=1)
   	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="morador")
   	@Column(name="id_morador")
   	private int id;
   	
   	@Column(name="nm_morador",nullable=false,length=100)
   	private String nome;
   	
   	@Column(name="nr_morador",nullable=false)
   	private Long telefone;
   	
   	public Morador() {
   	}
   
   	public Morador(String nome, Long telefone) {
   		this.nome = nome;
   		this.telefone = telefone;
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
   
   	public Long getTelefone() {
   		return telefone;
   	}
   
   	public void setTelefone(Long telefone) {
   		this.telefone = telefone;
   	}
   	
   }
   
   ````

   Após criada a classe incluir a seguinte linha em `persistence.xml`

   ````xml
   <class>br.com.encontro.entity.Morador</class>
   ````

3. <b>Prestador.java</b>

   ````java
   package br.com.encontro.entity;
   
   import javax.persistence.Column;
   import javax.persistence.Entity;
   import javax.persistence.GeneratedValue;
   import javax.persistence.GenerationType;
   import javax.persistence.Id;
   import javax.persistence.SequenceGenerator;
   import javax.persistence.Table;
   
   @Entity
   @Table(name="tb_prestador")
   public class Prestador {
   	
   	@Id
   	@SequenceGenerator(name="prestador",sequenceName="sq_tb_prestador",allocationSize=1)
   	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prestador")
   	@Column(name="id_prestador")
   	private int id;
   	
   	@Column(name="nm_prestador",nullable=false,length=100)
   	private String nome;
   	
   	@Column(name="nr_morador",nullable=false)
   	private Long telefone;
   	
   	public Prestador() {
   	}
   
   	public Prestador(String nome, Long telefone) {
   		this.nome = nome;
   		this.telefone = telefone;
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
   
   	public Long getTelefone() {
   		return telefone;
   	}
   
   	public void setTelefone(Long telefone) {
   		this.telefone = telefone;
   	}
   	
   	
   	
   
   }
   
   ````

   Após criada a classe incluir a seguinte linha em `persistence.xml`

   ````xml
   <class>br.com.encontro.entity.Prestador</class>
   ````

4. <b>Servico.java</b>

   Antes de criar a classe, precisamos criar o enum `Ocupacao.java`

   ````java
   package br.com.encontro.entity;
   
   public enum Ocupacao {
   
   	PINTOR, ELETRICISTA, PEDREIRO, ENCANADOR
   	
   }
   
   ````

    Após criado o enum, iniciamos a criação da classe

   ````java
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
   @Table(name="tb_servico")
   public class Servico {
   
   	@Id
   	@SequenceGenerator(name="servico",sequenceName="sq_tb_servico",allocationSize=1)
   	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="servico")
   	@Column(name="id_servico")
   	private int id;
   	
   	@Enumerated(EnumType.STRING)
   	@Column(name="nm_servico")
   	private Ocupacao nome;
   	
   	public Servico() {
   	}
   
   	public Servico(Ocupacao nome) {
   		this.nome = nome;
   	}
   
   	public int getId() {
   		return id;
   	}
   
   	public void setId(int id) {
   		this.id = id;
   	}
   
   	public Ocupacao getNome() {
   		return nome;
   	}
   
   	public void setNome(Ocupacao nome) {
   		this.nome = nome;
   	}
   	
   	
   	
   }
   
   ````

   Após criada a classe incluir a seguinte linha em `persistence.xml`

   ````xml
   <class>br.com.encontro.entity.Servico</class>
   ````

    

5. <b>Registro.java</b>

   Antes de criar a classe, precisamos criar o enum `Estado.java`
   
   ````java
   package br.com.encontro.entity;
   
   public enum Estado {
   	
   	ABERTO, FECHADO
   
   }
   
   ````
   
   Após criado o enum, iniciamos a criação da classe
   
   ````java
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
   
   ````
   
   Após criada a classe incluir a seguinte linha em `persistence.xml`
   
   ````xml
   <class>br.com.encontro.entity.Registro</class>
   ````

Pronto! Todas as entidades foram criadas com sucesso!

 <img src="assets/java2.PNG">

<hr>

<h3>Realizando testes</h3>

Agora vamos testar se o programa está criando nossas tabelas de forma correta. Contudo antes de tudo devemos criar a classe `Main.java` que vai apresentar o método main().

````java
package br.com.encontro.main;

import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("smartcities").createEntityManager();

	}

}

````

Vamos aos testes!

<img src="assets/Testes.GIF">

Como pode ser visto acima as tabelas foram criadas com sucesso! Os erros mostrados foram de comandos `drops de tables, sequences e constraints `  que não encontraram os componentes (e com razão visto que as estruturas estão sendo criadas pela primeira vez).

Conferindo no banco de dados Oracle, podemos notar a presença das novas tabelas.

<img src="assets/Testes2.GIF">



<a href="https://github.com/Amorim-cyber/fiap2_fase1_cap4">CLIQUE AQUI PARA VISUALIZAR O PROJETO NO GITHUB</a>

<B>FORTE ABRAÇO!</B>