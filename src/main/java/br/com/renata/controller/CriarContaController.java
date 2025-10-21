package br.com.renata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.renata.entities.Usuario;
import br.com.renata.repositories.UsuarioRepository;

@Controller
public class CriarContaController {

	@RequestMapping(value = "/criar-conta")
	public ModelAndView criarConta() {
		ModelAndView modelAndView = new ModelAndView("criar-conta");
		return modelAndView;
	}

	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("criar-conta");

		try {
			Usuario usuario = new Usuario();

			usuario.setNome(request.getParameter("nome_usuario"));
			usuario.setEmail(request.getParameter("email_usuario"));
			usuario.setSenha(request.getParameter("senha_usuario"));

			UsuarioRepository usuarioRepository = new UsuarioRepository();

			if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
				modelAndView.addObject("mensagem_erro", "O email " + usuario.getEmail() + " já está cadastrado. Tente outro");
			} else {
				usuarioRepository.save(usuario);
				modelAndView.addObject("mensagem_sucesso", "Parabéns " + usuario.getNome() + ", sua conta de usuário foi criada com sucesso!");
			}

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha: " + e.getMessage());
		}

		return modelAndView;
	}

}
