<html>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<head>
    <title>Agenda de contatos - Seja bem vindo!</title>
    <meta name="viewport" content="width=device-width" />
    <!-- folha de estilos CSS do bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

    <jsp:include page="/WEB-INF/views/components/navbar.jsp" />

    <div class="card m-4">
        <div class="card-body">
            <h5>Edi��o de contatos</h5>
            <p>Utilize o formul�rio para alterar os dados do contato.</p>
            <hr />

            <div class="text-success">
                <h4>${mensagem_sucesso}</h4>
            </div>
            <div class="text-danger">
                <h4>${mensagem_erro}</h4>
            </div>

            <form method="post" action="editar-contato">
            
                <input type="hidden" name="idContato" value="${contato.idContato}" />

                <div class="row mb-2">
                    <div class="col-md-6">
                        <label for="nome">Nome do Contato:</label>
                        <input type="text"
                               class="form-control"
                               id="nome"
                               name="nome_contato"
                               placeholder="Digite o nome completo do contato"
                               required="required"
                               pattern="[a-zA-Z�-�\s]{8,150}"
                               title="Por favor, informe um nome v�lido de 8 a 150 caracteres."
                               value="${contato.nome}" />
                    </div>

                    <div class="col-md-3">
                        <label for="email">Email:</label>
                        <input type="email"
                               class="form-control"
                               id="email"
                               name="email_contato"
                               placeholder="Digite o endere�o de email"
                               required="required"
                               title="Por favor, informe um endere�o de email v�lido."
                               value="${contato.email}" />
                    </div>

                    <div class="col-md-3">
                        <label for="telefone">Telefone:</label>
                        <input type="text"
                               class="form-control"
                               id="telefone"
                               name="telefone_contato"
                               placeholder="Digite o telefone do contato"
                               required="required"
                               pattern="[0-9]{11}"
                               title="Por favor, informe um telefone com somente 11 d�gitos num�ricos."
                               value="${contato.telefone}" />
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-md-12">
                        <label for="observacoes">Observa��es do contato:</label>
                        <textarea id="observacoes"
                                  name="observacoes_contato"
                                  class="form-control"
                                  required="required"
                                  title="Por favor, informe as observa��es do contato."
                        >${contato.observacoes}</textarea>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-md-12">
                        <input type="submit" class="btn btn-primary" value="Salvar Altera��es" />
                        <a href="/sistema_contatos/agenda/consulta" class="btn btn-light">Voltar</a>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <!-- arquivos JS do bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
