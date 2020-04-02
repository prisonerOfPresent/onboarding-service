package io.excitingstartup.service.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Arun Vishnu
 */

@Service
public class RegisterService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    RegisterService( final JdbcTemplate jdbcTemplate ){
        this.jdbcTemplate = jdbcTemplate;
    }

    //This is just a test method to see whether Spring jdbc template can create databases through the execute method.
    public Boolean createDB(){
        String sql = "CREATE DATABASE test1";
        try {
            jdbcTemplate.execute(sql);
            return Boolean.TRUE;
        }catch (Exception ex){
            return Boolean.FALSE;
        }
    }
}
