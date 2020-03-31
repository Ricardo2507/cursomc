package com.ricardo.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ricardo.cursomc.security.UserSS;

public class UserService {

	// retorna usuário so SpringSecurity
	public static UserSS authenticated() {
		// retorna o usuário que estiver logado no sistema
		try {

			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}

		catch (Exception e) {
			return null;
		}
	}

}
