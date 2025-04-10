package es.urjc.code.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests(requests -> requests.requestMatchers("/").permitAll());
        http.authorizeRequests(requests -> requests.requestMatchers("/login").permitAll());
        http.authorizeRequests(requests -> requests.requestMatchers("/loginerror").permitAll());
        http.authorizeRequests(requests -> requests.requestMatchers("/logout").permitAll());

        // Private pages (all other pages)
        http.authorizeRequests(requests -> requests.requestMatchers("/home").hasAnyRole("USER"));
        http.authorizeRequests(requests -> requests.requestMatchers("/admin").hasAnyRole("ADMIN"));

        // Login form
        http.formLogin(login -> login.loginPage("/login"));
        http.formLogin(login -> login.usernameParameter("username"));
        http.formLogin(login -> login.passwordParameter("password"));
        http.formLogin(login -> login.defaultSuccessUrl("/home"));
        http.formLogin(login -> login.failureUrl("/loginerror"));

        // Logout
        http.logout(logout -> logout.logoutUrl("/logout"));
        http.logout(logout -> logout.logoutSuccessUrl("/"));
    }

    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }
}
