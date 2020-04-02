package io.excitingstartup.service.onboarding.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Arun Vishnu
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories ( basePackages = "io.excitingstartup.service.onboarding.persistance.shard",
        entityManagerFactoryRef = "shardEntityManagerFactory",
        transactionManagerRef = "shardTransactionManager"
)
public class ShardDataSourceConfiguration {

    @Bean
    @ConfigurationProperties( "io.excitingstartup.onboarding.datasource.sharddb" )
    public DataSourceProperties shardDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties ( "io.excitingstartup.onboarding.datasource.sharddb.configuration" )
    public DataSource shardDataSource(){
        return shardDataSourceProperties()
                .initializeDataSourceBuilder()
                .type( HikariDataSource.class )
                .build();
    }

    @Bean( name = "shardEntityManagerFactory" )
    public LocalContainerEntityManagerFactoryBean shardEntityManagerFactory ( EntityManagerFactoryBuilder builder ){
        return builder
                .dataSource( shardDataSource() )
                .packages( "io.excitingstartup.service.onboarding.persistance.shard" )
                .build();
    }

    @Bean
    public PlatformTransactionManager shardTransactionManager (
            final @Qualifier("shardEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean ){
        return new JpaTransactionManager( localContainerEntityManagerFactoryBean.getObject() );
    }
}
