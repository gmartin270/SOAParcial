package org.guille.parcialsoa.util;

import java.util.Properties;

import org.guille.parcialsoa.model.ObjetoGenerico;
import org.guille.parcialsoa.model.Telefono;
import org.guille.parcialsoa.model.Transaccion;
import org.guille.parcialsoa.model.Usuario;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class SqlSchemaGenerator {

	public static void main(String[] args) {
		Configuration config = new Configuration();
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/OAUTH"); 
		properties.put("hibernate.connection.username", "root");
		properties.put("hibernate.connection.password", "patoxkgm");
		properties.put("hibernate.connection.driver_class", "org.gjt.mm.mysql.Driver");
		properties.put("hibernate.show_sql", "true");
		config.setProperties(properties);
		
		config.addAnnotatedClass(ObjetoGenerico.class);
		config.addAnnotatedClass(Telefono.class);
		config.addAnnotatedClass(Transaccion.class);
		config.addAnnotatedClass(Usuario.class);
		
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.setDelimiter(";");

		schemaExport.setOutputFile("src/main/resources/Schema.sql");
		schemaExport.setFormat(true);
		schemaExport.execute(true, false, false, false);
	}
}
