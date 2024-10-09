import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas
import java.time.temporal.ChronoUnit; // Importa a classe ChronoUnit para calcular a diferença entre datas

public class Aluno {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    Curso curso;
    private int idade;

    public Aluno(int id, String nome, LocalDate dataNascimento, String cpf, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = getCpf(cpf);
        this.curso = curso;
        this.idade = getIdade(dataNascimento);
        
        curso.getAlunos().add(this); // Adiciona o aluno à lista de alunos do curso
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public int getIdade(LocalDate dataNascimento) { // Calcula a idade do aluno
        LocalDate hoje = LocalDate.now(); // Pega a data atual

        return (int) ChronoUnit.YEARS.between(dataNascimento, hoje); // Retorna a diferença entre as datas em anos
    }

    public static String getCpf(String cpf) { // Formata o CPF
        return cpf.substring(0, 3) + "." +  cpf.substring(3, 6) + "." +  cpf.substring(6, 9) + "-" + cpf.substring(9);
    }

    public String toString() { // Retorna os dados do aluno
        return "\nID: " + id + " | Nome: " + nome + " | Data de Nascimento: " + dataNascimento + " | Idade: " + idade + " | CPF: " + cpf + " | Curso: " + curso.getNome();
    }
};