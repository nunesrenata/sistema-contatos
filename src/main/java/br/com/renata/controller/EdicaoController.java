package br.com.renata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.renata.entities.Contato;
import br.com.renata.entities.Usuario;
import br.com.renata.repositories.ContatoRepository;

@Controller
public class EdicaoController {

    @RequestMapping(value = "/agenda/edicao")
    public ModelAndView edicao(HttpServletRequest request) {
    	
        ModelAndView modelAndView = new ModelAndView("agenda/edicao");

        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
            Integer idContato = Integer.parseInt(request.getParameter("idContato"));
            ContatoRepository contatoRepository = new ContatoRepository();
            Contato contato = contatoRepository.findById(idContato);

            if (contato != null && contato.getIdUsuario().equals(usuario.getIdUsuario())) {
                modelAndView.addObject("contato", contato);
            } else {
                modelAndView.setViewName("redirect:/agenda/consulta");
            }

        } catch (Exception e) {
            modelAndView.addObject("mensagem_erro", "Falha ao obter contato: " + e.getMessage());
        }

        return modelAndView;
    }

    @RequestMapping(value = "/agenda/editar-contato", method = RequestMethod.POST)
    public ModelAndView editarContato(HttpServletRequest request) {
    	
        ModelAndView modelAndView = new ModelAndView("agenda/edicao");

        try {
            Contato contato = new Contato();
            
            contato.setIdContato(Integer.parseInt(request.getParameter("idContato")));
            contato.setNome(request.getParameter("nome_contato"));
            contato.setEmail(request.getParameter("email_contato"));
            contato.setTelefone(request.getParameter("telefone_contato"));
            contato.setObservacoes(request.getParameter("observacoes_contato"));

            ContatoRepository contatoRepository = new ContatoRepository();
            contatoRepository.update(contato);

            modelAndView.addObject("mensagem_sucesso", "Contato atualizado com sucesso.");
            modelAndView.addObject("contato", contato);

        } catch (Exception e) {
            modelAndView.addObject("mensagem_erro", "Falha ao atualizar o contato: " + e.getMessage());
        }

        return modelAndView;
    }

}
