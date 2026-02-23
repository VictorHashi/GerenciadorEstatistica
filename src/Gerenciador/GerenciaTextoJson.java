package Gerenciador;

import Class.ProdutoTexto;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GerenciaTextoJson implements GerenciaTexto{

    @Override
    public void exportar(List<ProdutoTexto> textos) {
        Gson gson = new Gson();
        String json = gson.toJson(textos);

        try {
            FileWriter fileWriter = new FileWriter("produtoTextos.json");
            fileWriter.write(json);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("JSON exportado");

    }

    @Override
    public List<ProdutoTexto> importar() {

        Gson gson = new Gson();
        List<ProdutoTexto> textos = new ArrayList<ProdutoTexto>();

        try(JsonReader reader = new JsonReader(new FileReader("produtoTextos.json"))){

            reader.beginArray();
            while (reader.hasNext()){
                textos.add(gson.fromJson(reader, ProdutoTexto.class));
            }
            reader.endArray();

        }catch (Exception e){
            e.printStackTrace();
        }
        return textos;
    }
}
