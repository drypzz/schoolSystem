import java.util.ArrayList;

public class Curso {
    int id;
    String nome;
    int cargaHoraria;
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
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ArrayList<Aluno> getAlunos() { // Retorna a lista de alunos matriculados no curso
        return alunos;
    }

    public String toString() { // Retorna os dados do curso
        return "\nID: " + id + " | Curso: " + nome + " | Carga Horária: " + cargaHoraria + " | Professor: " + professor.getNome();
    }
}
