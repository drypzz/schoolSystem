import java.sql.Connection; // Importa a classe Connection para realizar a conexão com o banco de dados
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para preparar a query SQL
import java.sql.ResultSet; // Importa a classe ResultSet para armazenar o resultado da query SQL

public class Curso {
    private int id;
    private String nome;
    private int cargaHoraria;
    private Professor professor;

    public Curso(int id, String nome, int cargaHoraria, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }; // Construtor da classe Curso

    public int getId() {
        return this.id;
    }; // Retorna o ID do curso

    public String getNome() {
        return this.nome;
    }; // Retorna o nome do curso

    public int getCargaHoraria() {
        return this.cargaHoraria;
    }; // Retorna a carga horária do curso

    public Professor getProfessor() {
        return this.professor;
    }; // Retorna o professor do curso

    public int getCountAlunos(){
        String sql = "SELECT * FROM Aluno WHERE curso_id = ?";
        int count = 0;

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }; // Retorna a quantidade de alunos no curso

    public void getSave() {
        String sql = "INSERT INTO Curso (id, nome, carga_horaria, professor_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.id);
            stmt.setString(2, this.nome);
            stmt.setInt(3, this.cargaHoraria);
            stmt.setInt(4, this.professor.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }; // Salva o curso no banco de dados

    public static Curso getUserID(int id) {
        String sql = "SELECT * FROM Curso WHERE id = ?";
        Curso curso = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Professor professor = Professor.getUserID(rs.getInt("professor_id"));
                curso = new Curso(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("carga_horaria"),
                    professor
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curso;
    }; // Retorna um curso pelo ID

    public static Curso[] getAllUsers() {
        String sql = "SELECT * FROM Curso";
        Curso[] cursos = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.last();
            cursos = new Curso[rs.getRow()];
            rs.beforeFirst();

            int i = 0;
            while (rs.next()) {
                Professor professor = Professor.getUserID(rs.getInt("professor_id"));
                cursos[i] = new Curso(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("carga_horaria"),
                    professor
                );
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursos;
    }; // Retorna todos os cursos
    
};
