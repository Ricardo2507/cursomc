package com.ricardo.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	// definiremos nesse vetor quais caminhos estão liberados
	// a princípio tudo que vier após /"h2-console/**" está liberado
	
	
	private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};
	
	private static final String[] PUBLIC_MATCHERS_GET = {"/produtos/**", "/categorias/**"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Corrige o problema ao tentar acessar o banco de dados h2
		// caso estejamos no profile=test, queremos acessar o H2
		
		  if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			  http.headers().frameOptions().disable();
		  
		  }
		 
	
		
		// para ativar o método CorsConfigurationSource
		// desabilitar a proteção de ataques CSRF em sistemas stateless.
		http.cors().and().csrf().disable();

		// Vai permitir qualquer requisição dos caminhos especificados no array de
		// Public_Matchers
		// Os caminhos do Public_Matchers apenas possibilitará realizar o get como
		// requisição
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.anyRequest().authenticated();
		
		//Anotação para informar que o back-end não criará estados
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
							

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;

	}

}
