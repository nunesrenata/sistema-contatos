package br.com.renata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.renata.entities.Contato;
import br.com.renata.entities.Usuario;
import br.com.renata.repositories.ContatoRepository;

@Controller
public class ExclusaoController {

	@RequestMapping(value = "/agenda/exclusao")
	public ModelAndView exclusao(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("agenda/consulta");

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			Integer idContato = Integer.parseInt(request.getParameter("idContato"));

			ContatoRepository contatoRepository = new ContatoRepository();

			Contato contato = contatoRepository.findById(idContato);

			if (contato != null && contato.getIdUsuario() == usuario.getIdUsuario()) {

				contatoRepository.delete(contato);

				modelAndView.addObject("mensagem_sucesso", "Contato excluído com sucesso.");
			}

			List<Contato> lista = contatoRepository.findAll(usuario.getIdUsuario());

			modelAndView.addObject("lista_contatos", lista);
		}

		catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao excluir contato: " + e.getMessage());

		}

		return modelAndView;

	}
}
