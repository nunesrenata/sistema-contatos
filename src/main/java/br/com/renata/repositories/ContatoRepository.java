package br.com.renata.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.renata.entities.Contato;
import br.com.renata.factories.ConnectionFactory;

public class ContatoRepository {

	public void save(Contato contato) throws Exception {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"insert into contato(nome, email, telefone, observacoes, idusuario) values(?, ?, ?, ?, ?)");
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEmail());
		statement.setString(3, contato.getTelefone());
		statement.setString(4, contato.getObservacoes());
		statement.setInt(5, contato.getIdUsuario());
		statement.execute();
		connection.close();
	}

	public void update(Contato contato) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("update contato set nome=?, telefone=?, email=?, observacoes=? where idcontato=?");
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getTelefone());
		statement.setString(3, contato.getEmail());
		statement.setString(4, contato.getObservacoes());
		statement.setInt(5, contato.getIdContato());

		statement.execute();
		connection.close();
	}

	public void delete(Contato contato) throws Exception {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from contato where idcontato=?");
		statement.setInt(1, contato.getIdContato());
		statement.execute();
		connection.close();
	}

	public List<Contato> findAll(Integer idUsuario) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from contato where idusuario = ?");
		statement.setInt(1, idUsuario);
		ResultSet resultSet = statement.executeQuery();

		List<Contato> lista = new ArrayList<Contato>();

		while (resultSet.next()) {
			Contato contato = new Contato();
			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));
			contato.setObservacoes(resultSet.getString("observacoes"));
			contato.setIdUsuario(resultSet.getInt("idusuario"));
			lista.add(contato);
		}
		connection.close();
		return lista;
	}

	public Contato findById(Integer idContato) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from contato where idcontato=?");
		statement.setInt(1, idContato);
		ResultSet resultSet = statement.executeQuery();

		Contato contato = null;

		if (resultSet.next()) {
			contato = new Contato();
			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));
			contato.setObservacoes(resultSet.getString("observacoes"));
			contato.setIdUsuario(resultSet.getInt("idusuario"));
		}

		connection.close();
		return contato;
	}

}
