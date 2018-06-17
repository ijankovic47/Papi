package konami.pes.configuration;

import java.util.Properties;

//import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource({ "classpath:connections.properties" })
@EnableTransactionManagement
@ComponentScan("konami.pes")
@EnableWebMvc 
@CrossOrigin
public class RootConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	Environment environment;

	@Bean(name="dataSource")
	public DataSource getDataSource(){
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		String dbUsername=environment.getProperty("database.username");
		String dbPassword=environment.getProperty("database.password");
		String dbHost=environment.getProperty("database.host");
		String dbPort=environment.getProperty("database.port");
		dataSource.setUrl("jdbc:oracle:thin:"+dbUsername+"/"+dbPassword+"@"+dbHost+":"+dbPort+":xe");
		return dataSource;		
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
		sessionFactory.scanPackages("konami.pes");
		sessionFactory.addProperties(getHibernateProperties());
		SessionFactory sf=sessionFactory.buildSessionFactory();
		return sf;
	}
//       @Bean(name="entityManagerFactory")
//	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//	      LocalContainerEntityManagerFactoryBean em 
//	        = new LocalContainerEntityManagerFactoryBean();
//	      em.setDataSource(getDataSource());
//	      em.setPackagesToScan(new String[] { "konami.pes" });
//	 
//	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	      em.setJpaVendorAdapter(vendorAdapter);
//	      em.setJpaProperties(getHibernateProperties());
//	 
//	      return em;
//	   }
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
		return txManager;
	}
//	  @Bean
//	   public PlatformTransactionManager transactionManager(){
//	       JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
//	       transactionManager.setDataSource(getDataSource());
//	       return transactionManager;
//	   }
	
	private Properties getHibernateProperties(){
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		return hibernateProperties;	
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE","PATCH").allowedOrigins("*")
        .allowedHeaders("*");
	}
}
