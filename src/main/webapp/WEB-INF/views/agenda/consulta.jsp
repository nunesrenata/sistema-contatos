<%@ page import="java.util.List" %>
<%@ page import="br.com.renata.entities.Contato" %>

<%
    List<Contato> lista = (List<Contato>) request.getAttribute("lista_contatos");
    if (lista == null) {
        lista = new java.util.ArrayList<Contato>();
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<head>
    <meta charset="UTF-8" />
    <title>Agenda de contatos - Seja bem-vindo!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <!-- CSS do Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

    <!-- Navbar -->
    <jsp:include page="/WEB-INF/views/components/navbar.jsp" />

    <div class="card m-4">
        <div class="card-body">
            <h5>Consulta de contatos</h5>
            <p>Listagem de contatos cadastrados na sua agenda.</p>
            <hr />

            <div class="table-responsive">
                <table class="table table-sm table-hover">
                    <thead>
                        <tr>
                            <th>Nome do contato</th>
                            <th>Email</th>
                            <th>Telefone</th>
                            <th>Observações</th>
                            <th>Operações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (lista.isEmpty()) {
                        %>
                            <tr>
                                <td colspan="5" class="text-center">
                                    Nenhum contato encontrado.
                                </td>
                            </tr>
                        <%
                            } else {
                                for (Contato contato : lista) {
                        %>
                            <tr>
                                <td><%= contato.getNome() %></td>
                                <td><%= contato.getEmail() %></td>
                                <td><%= contato.getTelefone() %></td>
                                <td><%= contato.getObservacoes() %></td>
                                <td>
                                    <a href="/sistema_contatos/agenda/edicao?idContato=<%= contato.getIdContato() %>" 
                                       class="btn btn-sm btn-primary">Editar</a>

                                    <a href="/sistema_contatos/agenda/exclusao?idContato=<%= contato.getIdContato() %>" 
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Deseja realmente excluir o contato selecionado?');">
                                       Excluir
                                    </a>
                                </td>
                            </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="5">
                                Quantidade de contatos em sua agenda:
                                <strong><%= lista.size() %></strong>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>

    <!-- JS do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
