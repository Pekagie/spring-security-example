package pl.gajda.springsecurityexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


@Bean
    public UserDetailsService userDetailsService(){
    UserDetails moderator = User.withDefaultPasswordEncoder()
            .username("moderator")
            .password("mdoerator1")
            .roles("MODERATOR")
            .build();


    UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin1")
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(moderator, admin);
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.POST, "/api").hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/api").hasRole("ADMIN")
                .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();

        // wylaczona ochrona przed atakiem csrf zeby mozna bylo stestowac na PostManie + dodane httpBasic() zeby mozna pylo w postmanie wrzucic sobie login i haslo
    }
}
