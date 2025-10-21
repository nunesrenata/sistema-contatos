package br.com.renata.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.renata.entities.Usuario;
import br.com.renata.factories.ConnectionFactory;

public class UsuarioRepository {

	public void save(Usuario usuario) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("insert into usuario(nome, email, senha) values(?, ?, md5(?))");
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());
		statement.execute();

		connection.close();
	}

	public Usuario findByEmail(String email) throws Exception {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE email = ?");
		statement.setString(1, email);

		ResultSet resultSet = statement.executeQuery();

		Usuario usuario = null;

		if (resultSet.next()) {

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}

		connection.close();
		return usuario;
	}

	public Usuario findByEmailAndSenha(String email, String senha) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from usuario where email=? and senha=md5(?)");
		statement.setString(1, email);
		statement.setString(2, senha);

		ResultSet resultSet = statement.executeQuery();

		Usuario usuario = null;

		if (resultSet.next()) {

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}

		connection.close(); 
		return usuario;
	}
	
	public void update(Integer idUsuario, String novaSenha) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("UPDATE usuario set senha=md5(?) where idusuario=?");
		statement.setString(1, novaSenha);
		statement.setInt(2, idUsuario);
		statement.execute();
		
		connection.close();
	}
}
