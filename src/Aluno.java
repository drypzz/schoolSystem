import java.sql.Connection; // Importa a classe Connection para realizar a conexão com o banco de dados
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para preparar e executar as instruções SQL
import java.sql.ResultSet; // Importa a classe ResultSet para armazenar o resultado de uma consulta SQL
import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas
import java.time.temporal.ChronoUnit; // Importa a classe ChronoUnit para calcular a diferença entre datas

public class Aluno {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private int idade;
    private Curso curso;

    public Aluno(int id, String nome, LocalDate dataNascimento, String cpf, int idade, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.curso = curso;
        this.idade = calcularIdade(dataNascimento);
    } // Construtor da classe Aluno

    private int calcularIdade(LocalDate dataNascimento) {
        return (int) ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
    } // Calcula a idade do aluno

    public int getId() {
        return this.id;
    } // Retorna o ID do aluno

    public String getNome() {
        return this.nome;
    } // Retorna o nome do aluno

    public Curso getCurso() {
        return this.curso;
    } // Retorna o curso do aluno

    public int getIdade() {
        return this.idade;
    } // Retorna a idade do aluno

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    } // Retorna a data de nascimento do aluno

    public String getCpf() {
        return this.cpf;
    } // Retorna o CPF do aluno

    public void getSave() {
        String sql = "INSERT INTO Aluno (id, nome, data_nascimento, cpf, idade, curso_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.id);
            stmt.setString(2, this.nome);
            stmt.setDate(3, java.sql.Date.valueOf(this.dataNascimento));
            stmt.setString(4, this.cpf);
            stmt.setInt(5, this.idade);
            stmt.setInt(6, this.curso.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Salva o aluno no banco de dados

    public static Aluno getUserID(int id) {
        String sql = "SELECT * FROM Aluno WHERE id = ?";
        Aluno aluno = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso cursoS = Curso.getUserID(rs.getInt("curso_id"));
                    aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getInt("idade"),
                        cursoS
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;
    } // Retorna um aluno específico do banco de dados

    public static Aluno[] getAllUsers() {
        String sql = "SELECT * FROM Aluno";
        Aluno[] Alunoes = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.last();
            Alunoes = new Aluno[rs.getRow()];
            rs.beforeFirst();

            int i = 0;
            while (rs.next()) {
                Curso cursoS = Curso.getUserID(rs.getInt("curso_id"));
                Alunoes[i] = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("cpf"),
                    rs.getInt("idade"),
                    cursoS
                );
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Alunoes;
    } // Retorna todos os alunos cadastrados no banco de dados
    
}
