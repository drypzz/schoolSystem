import java.sql.Connection; // Importa a classe Connection para realizar a conexão com o banco de dados
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para preparar a query SQL
import java.sql.ResultSet; // Importa a classe ResultSet para armazenar o resultado da query SQL

public class Professor {
    private int id;
    private String nome;
    private String departamento;

    public Professor(int id, String nome, String departamento) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }; // Construtor da classe Professor

    public int getId() {
        return this.id;
    }; // Método para retornar o ID do professor

    public String getNome() {
        return this.nome;
    }; // Método para retornar o nome do professor

    public String getDepartamento() {
        return this.departamento;
    }; // Método para retornar o departamento do professor

    public String getCursos() {
        String sql = "SELECT * FROM Curso WHERE professor_id = ?";
        String curso = "Nenhum";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                curso = rs.getString("nome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curso;
    }; // Método para retornar o curso do professor

    public void getSave() {
        String sql = "INSERT INTO Professor (id, nome, departamento) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.id);
            stmt.setString(2, this.nome);
            stmt.setString(3, this.departamento);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }; // Método para salvar o professor no banco de dados

    public static Professor getUserID(int id) {
        String sql = "SELECT * FROM Professor WHERE id = ?";
        Professor professor = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("departamento")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return professor;
    }; // Método para retornar um professor pelo ID

    public static Professor[] getAllUsers() {
        String sql = "SELECT * FROM Professor";
        Professor[] professores = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.last();
            professores = new Professor[rs.getRow()];
            rs.beforeFirst();

            int i = 0;
            while (rs.next()) {
                professores[i] = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("departamento")
                );
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return professores;
    }; // Método para retornar todos os professores
}
