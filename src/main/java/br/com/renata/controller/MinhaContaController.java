package br.com.renata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.renata.entities.Usuario;
import br.com.renata.repositories.UsuarioRepository;

@Controller
public class MinhaContaController {

	@RequestMapping(value = "/agenda/minhaconta")
	public ModelAndView minhaConta() {
		ModelAndView modelAndView = new ModelAndView("agenda/minhaconta");
		return modelAndView;
	}

	@RequestMapping(value = "/agenda/alterar-senha", method = RequestMethod.POST)
	public ModelAndView alterSenha(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("agenda/minhaconta");

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			String senhaAtual = request.getParameter("senhaAtual");
			String novaSenha = request.getParameter("novaSenha");

			UsuarioRepository usuarioRepository = new UsuarioRepository();

			if (usuarioRepository.findByEmailAndSenha(usuario.getEmail(), senhaAtual) != null) {
				usuarioRepository.update(usuario.getIdUsuario(), novaSenha);
				modelAndView.addObject("mensagem_sucesso", "Senha de acesso alterada com sucesso.");
			} else {
				modelAndView.addObject("mensagem_erro", "Senha atual inválida.");
			}

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao alterar senha: " + e.getMessage());
		}

		return modelAndView;
	}
}
