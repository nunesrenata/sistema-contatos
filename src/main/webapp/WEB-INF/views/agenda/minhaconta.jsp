<html>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<head>
<title>Agenda de contatos - Seja bem-vindo!</title>
<meta name="viewport" content="width=device-width" />

<!-- CSS do Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>

	<jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

	<div class="card m-4">
		<div class="card-body">

			<h5>Minha conta de usu�rio</h5>
			<p>Dados do usu�rio autenticado na agenda</p>
			<hr />

			<table class="table table-sm table-bordered mb-4">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome do usu�rio</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${usuario_autenticado.idUsuario}</td>
						<td>${usuario_autenticado.nome}</td>
						<td>${usuario_autenticado.email}</td>
					</tr>
				</tbody>
			</table>

			<h5>Alterar Senha de acesso</h5>
			<p>Utilize o formul�rio abaixo para alterar sua senha de acesso:</p>

			<form method="post" action="alterar-senha">

				<div class="text-success">
					<h4>${mensagem_sucesso}</h4>
				</div>
				<div class="text-danger">
					<h4>${mensagem_erro}</h4>
				</div>

				<div class="row mb-2">
					<div class="col-md-3">
						<label>Informe a senha atual:</label> <input type="password"
							name="senhaAtual" class="form-control" required />
					</div>

					<div class="col-md-3">
						<label>Informe a nova senha:</label> <input type="password"
							name="novaSenha" class="form-control" required
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}"
							title="A senha deve ter pelo menos 8 caracteres, incluindo pelo menos uma letra min�scula, uma letra mai�scula, um n�mero e um caractere especial (!@#$%^&*)." />
					</div>
				</div>

				<div class="row mb-2">
					<div class="col-md-12">
						<input type="submit" class="btn btn-success"
							value="Redefinir Senha" />
					</div>
				</div>

			</form>
		</div>
	</div>

	<!-- JS do Bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
