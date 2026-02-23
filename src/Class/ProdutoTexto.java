package Class;

public class ProdutoTexto {

    private String id;
    private String content;
    private String author;
    private String model;
    private double complexity;
    private double semanticCoherence;

    public ProdutoTexto(String id, String content, String author, String model, double complexity, double semanticCoherence) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.model = model;
        this.complexity = complexity;
        this.semanticCoherence = semanticCoherence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    public double getSemanticCoherence() {
        return semanticCoherence;
    }

    public void setSemanticCoherence(double semanticCoherence) {
        this.semanticCoherence = semanticCoherence;
    }
}

