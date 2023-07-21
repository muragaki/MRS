package mrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import mrs.domain.service.user.ReservationUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	ReservationUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/js/**", "/css/**").permitAll().antMatchers("/**").authenticated().and()
//				.formLogin().loginPage("/loginForm").loginProcessingUrl("/login").usernameParameter("username")
//				.passwordParameter("password").defaultSuccessUrl("/rooms", true).failureUrl("/loginForm?error=true")
//				.permitAll();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
						.requestMatchers("/js/**", "/css/**").permitAll()
						.requestMatchers("/signupForm").permitAll()
						.requestMatchers("/signup").permitAll()
						.requestMatchers("/**").authenticated())
				.formLogin(login -> login
						.loginPage("/loginForm")
						.loginProcessingUrl("/login")
						.usernameParameter("username")
						.passwordParameter("password")
						.defaultSuccessUrl("/rooms", true)
						.failureUrl("/loginForm?error=true")
						.permitAll());
		return http.build();
		}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}