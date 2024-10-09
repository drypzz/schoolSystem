import java.util.ArrayList; // Importa a classe ArrayList para trabalhar com listas

public class Professor {
    private int id;
    private String nome;
    private String departamento;
    
    ArrayList<Curso> cursos = new ArrayList<>(); // Lista de cursos ministrados pelo professor

    public Professor(int id, String nome, String departamento) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public ArrayList<Curso> getCursos() { // Retorna a lista de cursos ministrados pelo professor
        return cursos;
    }

    public String toString() { // Retorna os dados do professor
        return "\nID: " + id + " | Nome: " + nome + " | Departamento: " + departamento;
    }
}
