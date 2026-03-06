package Gerenciador;

import Class.ProdutoTexto;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

public class GerenciaTextoXML implements GerenciaTexto {

    @Override
    public void exportar(List<ProdutoTexto> texto) {

        XStream xStream = new XStream();
        xStream.processAnnotations(ProdutoTexto.class);

        try {

            FileWriter fileWriter = new FileWriter("produtoTextos.xml");
            fileWriter.write(xStream.toXML(texto));
            fileWriter.close();

            System.out.println("XML exportado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProdutoTexto> importar() {

        XStream xStream = new XStream();
        xStream.processAnnotations(ProdutoTexto.class);
        TypePermission textoPermission = AnyTypePermission.ANY;
        xStream.addPermission(textoPermission);

        try {
            FileReader fileReader = new FileReader("produtoTextos.xml");
            List<ProdutoTexto> textos = (List<ProdutoTexto>) xStream.fromXML(fileReader);
            return textos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}