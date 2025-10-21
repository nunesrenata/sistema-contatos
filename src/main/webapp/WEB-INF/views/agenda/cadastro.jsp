<!DOCTYPE html>
<html lang="pt-BR">
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<head>
    <meta charset="UTF-8">
    <title>Agenda de contatos - Seja bem-vindo!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Folha de estilos CSS do Bootstrap -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" 
        rel="stylesheet"
    >
</head>
<body>

    <!-- Barra de navega��o -->
    <jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

    <div class="card m-4">
        <div class="card-body">
            <h5>Cadastro de contatos</h5>
            <p>Preencha os campos abaixo para incluir um contato.</p>
            <hr />
            
            	<div class="text-center text-success">
							<h4>${mensagem_sucesso}</h4>
						</div>

						<div class="text-center text-danger">
							<h4>${mensagem_erro}</h4>
						</div>

            <form method="post" action="cadastrar-contato">
                <div class="row mb-2">
                    <div class="col-md-6">
                        <label for="nome">Nome do Contato:</label>
                        <input 
                            type="text"
                            class="form-control"
                            id="nome"
                            name="nome_contato"
                            placeholder="Digite o nome completo do contato"
                            required
                            pattern="[a-zA-Z�-�\s]{8,150}"
                            title="Por favor, informe um nome v�lido de 8 a 150 caracteres."
                        />
                    </div>

                    <div class="col-md-3">
                        <label for="email">Email:</label>
                        <input 
                            type="email"
                            class="form-control"
                            id="email"
                            name="email_contato"
                            placeholder="Digite o endere�o de email"
                            required
                            title="Por favor, informe um endere�o de email v�lido."
                        />
                    </div>

                    <div class="col-md-3">
                        <label for="telefone">Telefone:</label>
                        <input 
                            type="text"
                            class="form-control"
                            id="telefone"
                            name="telefone_contato"
                            placeholder="Digite o telefone do contato"
                            required
                            pattern="[0-9]{11}"
                            title="Por favor, informe um telefone com somente 11 d�gitos num�ricos."
                        />
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-md-12">
                        <label for="observacoes">Observa��es do contato:</label>
                        <textarea
                            id="observacoes"
                            name="observacoes_contato"
                            class="form-control"
                            required
                            title="Por favor, informe as observa��es do contato."
                        ></textarea>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-md-12">
                        <input 
                            type="submit" 
                            class="btn btn-success" 
                            value="Realizar Cadastro"
                        />
                        <input 
                            type="reset" 
                            class="btn btn-light" 
                            value="Limpar"
                        />
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Arquivos JS do Bootstrap -->
    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
    ></script>
</body>
</html>
