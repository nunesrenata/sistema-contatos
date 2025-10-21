package br.com.renata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.javafaker.Faker;

import br.com.renata.entities.Usuario;
import br.com.renata.messages.EmailMessage;
import br.com.renata.repositories.UsuarioRepository;

@Controller
public class RecuperarSenhaController {

	@RequestMapping(value = "/recuperar-senha")
	public ModelAndView recuperarSenha() {

		ModelAndView modelAndView = new ModelAndView("recuperar-senha");
		return modelAndView;
	}

	@RequestMapping(value = "/recuperar-senha-usuario", method = RequestMethod.POST)
	public ModelAndView recuperarSenhaUsuario(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("recuperar-senha");

		try {

			String email = request.getParameter("email");

			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.findByEmail(email);

			if (usuario != null) {

				Faker faker = new Faker();
				String novaSenha = "@a1" + faker.internet().password(8, 10, true, true);

				String to = usuario.getEmail();
				String subject = "Recuperação de senha de acesso - Sistema Contatos";
				String body = "\nOlá " + usuario.getNome() + "\nUma nova senha foi gerada com sucesso."
						+ "\nAcesse o sistema com a senha: " + novaSenha
						+ "\nE em seguida, use o menu 'Minha Conta' para alterar a senha." + "\n\nAtt,"
						+ "\nEquipe Sistema Contas";

				EmailMessage.sendMessage(to, subject, body);

				usuarioRepository.update(usuario.getIdUsuario(), novaSenha);

				modelAndView.addObject("mensagem_sucesso",
						"Recuperação de senha realizada com sucesso. Verifique sua conta de email.");
			} else {
				modelAndView.addObject("mensagem_erro", "Usuário não encontrado.");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao recuperar senha: " + e.getMessage());
		}

		return modelAndView;
	}
}