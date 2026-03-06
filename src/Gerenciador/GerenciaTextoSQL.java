package Gerenciador;

import Class.ProdutoTexto;
import DB.TextoDAO;

import java.util.ArrayList;
import java.util.List;

public class GerenciaTextoSQL implements GerenciaTexto {

    @Override
    public void exportar(List<ProdutoTexto> textos) {
        TextoDAO textoDAO = new TextoDAO();
        for (ProdutoTexto texto : textos) {
            textoDAO.insert(texto);
        }
    }

    @Override
    public List<ProdutoTexto> importar() {
        List<ProdutoTexto> textos = new ArrayList<ProdutoTexto>();
        TextoDAO textoDAO = new TextoDAO();
        textos = textoDAO.selectAll();
        return textos;
    }
}