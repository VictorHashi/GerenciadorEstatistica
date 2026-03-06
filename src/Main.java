import Class.ProdutoTexto;
import Gerenciador.GerenciaTextoJson;
import Gerenciador.GerenciaTextoSQL;
import Gerenciador.GerenciaTextoXML;
import Gerenciador.GerenciaTexto;
import HTML.GeradorHTML;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a fonte de dados:");
        System.out.println("1 - Banco de Dados (BD)");
        System.out.println("2 - JSON");
        System.out.println("3 - XML");
        System.out.print("Opção: ");
        
        int opcao = scanner.nextInt();
        scanner.close();

        GerenciaTexto gerenciaTexto = null;
        String origem = "";

        switch (opcao) {
            case 1:
                gerenciaTexto = new GerenciaTextoSQL();
                origem = "Banco de Dados";
                break;
            case 2:
                gerenciaTexto = new GerenciaTextoJson();
                origem = "JSON";
                break;
            case 3:
                gerenciaTexto = new GerenciaTextoXML();
                origem = "XML";
                break;
            default:
                System.out.println("Opção inválida. Encerrando o programa.");
                return;
        }

        if (opcao == 2 || opcao == 3) {
            String inputFile = (opcao == 2) ? "produtoTextos.json" : "produtoTextos.xml";
            java.io.File sourceFile = new java.io.File(inputFile);
            java.io.File outputFile = new java.io.File("relatorio_estatistico.html");

            if (outputFile.exists() && sourceFile.exists()) {
                boolean isSameSource = false;
                try {
                    String content = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
                    if (content.contains("Fonte de dados: <strong>" + origem)) {
                        isSameSource = true;
                    }
                } catch (java.io.IOException e) {
                    // ignora
                }

                if (isSameSource) {
                    if (outputFile.lastModified() > sourceFile.lastModified()) {
                        System.out.println("O arquivo HTML gerado (" + outputFile.getName() + ") já está atualizado em relação aos dados de " + origem + ".");
                        System.out.println("Ignorando a geração.");
                        return;
                    }
                }
            }
        }

        System.out.println("Lendo dados de " + origem + "...");
        List<ProdutoTexto> textos = gerenciaTexto.importar();

        if (textos == null || textos.isEmpty()) {
            System.out.println("Nenhum registro encontrado em " + origem + ".");
            return;
        }

        System.out.println("Registros carregados: " + textos.size());

        System.out.println("Gerando HTML baseado nos dados de " + origem + "...");
        GeradorHTML geradorHTML = new GeradorHTML();
        geradorHTML.gerar(textos, origem);
    }
}
