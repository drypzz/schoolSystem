import java.util.ArrayList;

public class Curso {
    private int id;
    private String nome;
    private int cargaHoraria;
    Professor professor; // Professor responsável pelo curso
    
    ArrayList<Aluno> alunos = new ArrayList<>(); // Lista de alunos matriculados no curso

    public Curso(int id, String nome, int cargaHoraria, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;

        professor.getCursos().add(this); // Adiciona o curso à lista de cursos do professor
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getCargaHoraria() {
        return this.cargaHoraria;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public ArrayList<Aluno> getAlunos() { // Retorna a lista de alunos matriculados no curso
        return this.alunos;
    }

    public String toString() { // Retorna os dados do curso
        return "\nID: " + this.id + " | Curso: " + this.nome + " | Carga Horária: " + this.cargaHoraria + " | Professor: " + this.professor.getNome();
    }
}
