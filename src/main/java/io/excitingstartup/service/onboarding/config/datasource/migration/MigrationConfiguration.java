package io.excitingstartup.service.onboarding.config.datasource.migration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Arun Vishnu
 */

@Configuration
@AutoConfigureAfter( {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class} )
public class MigrationConfiguration {

    @Resource(name = "onBoardingDataSource")
    private DataSource onBoardingDataSource;

    @Resource(name = "shardDataSource")
    private DataSource shardDataSource;

    @Bean(name = "flywayOnBoarding")
    public Flyway flywayOne() {
        Flyway flyway = Flyway
                .configure()
                .locations( "classpath:db/migration/onboarding" )
                .dataSource( onBoardingDataSource )
                .outOfOrder(true)
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean( name = "flywayShard" )
    public Flyway flywayTwo() {
        Flyway flyway = Flyway
                .configure()
                .locations( "classpath:db/migration/shard" )
                .dataSource( shardDataSource )
                .outOfOrder(true)
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    @Primary
    public FlywayMigrationInitializer flywayInitializerOne(@Qualifier("flywayOnBoarding") Flyway flywayOne) {
        return new FlywayMigrationInitializer( flywayOne, Flyway::repair );
    }

    @Bean
    public FlywayMigrationInitializer flywayInitializerTwo(@Qualifier("flywayShard") Flyway flywayTwo) {
        return new FlywayMigrationInitializer( flywayTwo, Flyway::repair );
    }



}
