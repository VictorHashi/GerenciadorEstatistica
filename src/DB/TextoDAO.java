package DB;

import Class.ProdutoTexto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TextoDAO {

    final String TABLE = "produtotexto";

    public boolean insert(ProdutoTexto texto) {
        try {
            Connection connection = ConnectDB.connect();
            String sql = "INSERT INTO " + TABLE
                    + " (text_id, content_text, author_type, model_source, prompt_complexity_score, semantic_coherence_score) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, texto.getId());
            preparedStatement.setString(2, texto.getContent());
            preparedStatement.setString(3, texto.getAuthor());
            preparedStatement.setString(4, texto.getModel());
            preparedStatement.setDouble(5, texto.getComplexity());
            preparedStatement.setDouble(6, texto.getSemanticCoherence());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProdutoTexto> selectAll() {
        try {
            Connection connection = ConnectDB.connect();
            String sql = "SELECT * FROM " + TABLE + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProdutoTexto> textos = montarLista(resultSet);
            return textos;
        } catch (Exception e) {
            System.err.println("Erro ao buscar registros no banco de dados:");
            e.printStackTrace();
            return null;
        }
    }

    public List<ProdutoTexto> montarLista(ResultSet resultSet) {
        List<ProdutoTexto> textos = new ArrayList<ProdutoTexto>();
        try {
            while (resultSet.next()) {
                ProdutoTexto texto = resultSetToProdutoTexto(resultSet);
                textos.add(texto);
            }
            return textos;
        } catch (Exception e) {
            return null;
        }
    }

    private ProdutoTexto resultSetToProdutoTexto(ResultSet resultSet) throws Exception {
        ProdutoTexto texto = new ProdutoTexto("", "", "", "", 0.0, 0.0);
        
        texto.setId(resultSet.getString("text_id"));
        texto.setContent(resultSet.getString("content_text"));
        texto.setAuthor(resultSet.getString("author_type"));
        texto.setModel(resultSet.getString("model_source"));
        texto.setComplexity(resultSet.getDouble("prompt_complexity_score"));
        texto.setSemanticCoherence(resultSet.getDouble("semantic_coherence_score"));

        return texto;
    }
}
