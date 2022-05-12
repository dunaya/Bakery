package com.example.bakery.configuration;

import com.example.bakery.repositories.AllRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    AllRoleRepository userRepository;
    


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/baker_page", "/my_baker").hasRole("BAKER")
                .antMatchers("/client_page", "/my_client").hasRole("CLIENT")
                .antMatchers("/target", "/admin_page").hasRole("ADMIN")
                .antMatchers("/", "/home","/registration", "/login", "/registration/client", "/registration/baker", "/registration/admin").permitAll()
                .antMatchers("/free_client").hasAnyRole("CLIENT", "ADMIN", "BAKER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()).usersByUsernameQuery("select login, password, 'true' from allrole where login=?").authoritiesByUsernameQuery("select login, type from allroles where login=?");

    }
}