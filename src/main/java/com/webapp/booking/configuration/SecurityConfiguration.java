package com.webapp.booking.configuration;

import com.webapp.booking.services.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Setter(onMethod = @__(@Autowired))
    @Autowired
    private UserService userService;
    @Autowired
    @Setter(onMethod = @__(@Autowired))
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // todo: remove request url from antmatchers, add failure handler
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/signUp", "/login", "/**/index.html", "/resources/**").permitAll()
                .and()
                .formLogin()
                .successHandler(customAuthenticationSuccessHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        //System.err.println(bCryptPasswordEncoder.encode("123"));
        return new BCryptPasswordEncoder(12);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
