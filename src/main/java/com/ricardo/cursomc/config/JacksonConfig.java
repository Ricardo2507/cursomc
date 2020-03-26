package com.ricardo.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardo.cursomc.domain.PagamentoComBoleto;
import com.ricardo.cursomc.domain.PagamentoComCartao;

// classe de configuração é iniciada no início da execução da aplicação

@Configuration
public class JacksonConfig {
	// essa classe é padrão. o quie muda são as subclasses da ser utilizadas, a exemplo de 
	// PagamentoComCartao.class
	// PagamentoComBoleto.class
	// permite a instaciação de classes a partir de dados Json
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {

				// Subclasses que precisam ser registradas
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}