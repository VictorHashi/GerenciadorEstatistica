package Estatistica;

import Class.ProdutoTexto;
import java.util.*;

public class CalculadorEstatistica {

    private List<ProdutoTexto> textos;

    public CalculadorEstatistica(List<ProdutoTexto> textos) {
        this.textos = textos;
    }


    public double mediaComplexity() {
        return textos.stream().mapToDouble(ProdutoTexto::getComplexity).average().orElse(0);
    }

    public double medianaComplexity() {
        List<Double> valores = new ArrayList<>();
        for (ProdutoTexto t : textos) valores.add(t.getComplexity());
        Collections.sort(valores);
        int n = valores.size();
        if (n == 0) return 0;
        return (n % 2 == 0) ? (valores.get(n/2 - 1) + valores.get(n/2)) / 2.0 : valores.get(n/2);
    }

    public double modaComplexity() {
        return calcularModa(textos, true);
    }

    public double varianciaComplexity() {
        int n = textos.size();
        if (n <= 1) return 0;
        double media = mediaComplexity();
        double somaQuad = textos.stream()
                .mapToDouble(t -> Math.pow(t.getComplexity() - media, 2))
                .sum();
        return somaQuad / (n - 1);
    }

    public double desvioPadraoComplexity() {
        return Math.sqrt(varianciaComplexity());
    }


    public double mediaSemanticCoherence() {
        return textos.stream().mapToDouble(ProdutoTexto::getSemanticCoherence).average().orElse(0);
    }

    public double medianaSemanticCoherence() {
        List<Double> valores = new ArrayList<>();
        for (ProdutoTexto t : textos) valores.add(t.getSemanticCoherence());
        Collections.sort(valores);
        int n = valores.size();
        if (n == 0) return 0;
        return (n % 2 == 0) ? (valores.get(n/2 - 1) + valores.get(n/2)) / 2.0 : valores.get(n/2);
    }

    public double modaSemanticCoherence() {
        return calcularModa(textos, false);
    }

    public double varianciaSemanticCoherence() {
        int n = textos.size();
        if (n <= 1) return 0;
        double media = mediaSemanticCoherence();
        double somaQuad = textos.stream()
                .mapToDouble(t -> Math.pow(t.getSemanticCoherence() - media, 2))
                .sum();
        return somaQuad / (n - 1);
    }

    public double desvioPadraoSemanticCoherence() {
        return Math.sqrt(varianciaSemanticCoherence());
    }

    private double calcularModa(List<ProdutoTexto> lista, boolean useComplexity) {
        Map<Double, Integer> freq = new HashMap<>();
        for (ProdutoTexto t : lista) {
            // Arredonda em 2 casas para agrupar valores próximos
            double val = useComplexity ? t.getComplexity() : t.getSemanticCoherence();
            double rounded = Math.round(val * 100.0) / 100.0;
            freq.put(rounded, freq.getOrDefault(rounded, 0) + 1);
        }
        return freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0.0);
    }

    public long countAI() {
        return textos.stream().filter(t -> "AI".equalsIgnoreCase(t.getAuthor())).count();
    }

    public long countHuman() {
        return textos.stream().filter(t -> "Human".equalsIgnoreCase(t.getAuthor())).count();
    }

    public double minComplexity() {
        return textos.stream().mapToDouble(ProdutoTexto::getComplexity).min().orElse(0);
    }

    public double maxComplexity() {
        return textos.stream().mapToDouble(ProdutoTexto::getComplexity).max().orElse(0);
    }

    public double minSemanticCoherence() {
        return textos.stream().mapToDouble(ProdutoTexto::getSemanticCoherence).min().orElse(0);
    }

    public double maxSemanticCoherence() {
        return textos.stream().mapToDouble(ProdutoTexto::getSemanticCoherence).max().orElse(0);
    }

    public int total() {
        return textos.size();
    }
}
