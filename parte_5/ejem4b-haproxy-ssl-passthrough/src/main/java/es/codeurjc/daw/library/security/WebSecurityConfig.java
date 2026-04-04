package es.codeurjc.daw.library.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		
		// Public pages
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/loginerror").permitAll()
            .antMatchers("/logout").permitAll()
            // Private pages
            .antMatchers("/newbook").hasAnyRole("USER")
            .antMatchers("/editbook/*").hasAnyRole("USER")
            .antMatchers("/removebook/*").hasAnyRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            // Login form
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/loginerror")
                .and()
            // Logout
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }
}