package io.excitingstartup.service.onboarding.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Arun Vishnu
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories ( basePackages = "io.excitingstartup.service.onboarding.persistence.onboarding",
        entityManagerFactoryRef = "onBoardingEntityManagerFactory",
        transactionManagerRef = "onBoardingTransactionManager"
)
public class OnBoardingDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties( "io.excitingstartup.onboarding.datasource.onboarding" )
    public DataSourceProperties onBoardingDataSourceProperties(){
        return new DataSourceProperties();
    }

    @FlywayDataSource
    @Bean("onBoardingDataSource")
    @Primary
    @ConfigurationProperties( "io.excitingstartup.onboarding.datasource.onboarding.configuration" )
    public DataSource onBoardingDataSource(){

        return onBoardingDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean( name = "onBoardingEntityManagerFactory" )
    public LocalContainerEntityManagerFactoryBean onBoardingEntityManagerFactory(EntityManagerFactoryBuilder builder){
            return builder
                    .dataSource( onBoardingDataSource() )
                    .packages( "io.excitingstartup.service.onboarding.persistence.onboarding" )
                    .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager onBoardingTransactionManager
            ( final @Qualifier("onBoardingEntityManagerFactory")
                     LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean ) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }


}
