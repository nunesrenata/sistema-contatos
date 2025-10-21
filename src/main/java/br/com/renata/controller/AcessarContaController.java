package br.com.renata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.renata.entities.Usuario;
import br.com.renata.repositories.UsuarioRepository;

@Controller
public class AcessarContaController {

	@RequestMapping(value = "/")
	public ModelAndView acessarConta() {
		ModelAndView modelAndView = new ModelAndView("acessar-conta");
		return modelAndView;
	}

	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("acessar-conta");

		try {

			String email = request.getParameter("email_usuario");
			String senha = request.getParameter("senha_usuario");

			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);

			if (usuario != null) {
				request.getSession().setAttribute("usuario_autenticado", usuario);

				modelAndView.setViewName("redirect:/agenda/home");
			} 

			else {
				modelAndView.addObject("mensagem_erro", "Acesso negado. Usuário inválido.");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha: " + e.getMessage());
		}

		return modelAndView;
	}
}
