<h1>FIAP Fase 1 : Capítulo 4 </h1>

<h3>Início</h3>

Para abstrair o conhecimento adquirido no capítulo 4 da Fase 1 desenvolvi o seguinte modelo de negócios:

Temos moradores que ocupam um determinado condomínio. Estes podem está procurando serviços domésticos de diversos tipos (elétrico, hidráulico ou mecânico) e o prestador de serviço detém a capacitação. 

A ideia é criar uma aplicação que facilite o encontro entre moradores e prestadores de serviço.

Segue abaixo a representação gráfica do modelo relacional.

<img src="assets/tabelas.PNG">

<h3>Setup</h3>

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

Feita a configuração inicial

<img src="assets/java1.PNG">

<hr>
<h3>Criação das entidades</h3>

