package Gerenciador;

import Class.ProdutoTexto;
import java.util.List;

public interface GerenciaTexto {
    public void exportar(List<ProdutoTexto> textos);

    public List<ProdutoTexto> importar();
}
