import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas
import java.time.format.DateTimeParseException; // Importa a classe DateTimeParseException para tratar exceções de datas inválidas
import java.util.ArrayList;
import java.util.Scanner;

public class Funcoes {
    private ArrayList<Professor> professores = new ArrayList<>(); // Lista de professores
    private ArrayList<Curso> cursos = new ArrayList<>(); // Lista de cursos
    private ArrayList<Aluno> alunos = new ArrayList<>(); // Lista de alunos
    private Scanner scanner = new Scanner(System.in);

    public void cadastrarProfessor() {
        System.out.println("\nDigite o ID do Professor:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (professores.stream().anyMatch(e -> e.getId() == id)) { // Verifica se já existe um professor com o mesmo ID
            System.out.println("\nProfessor já cadastrado.");
            return;
        }

        System.out.println("\nDigite o nome do Professor:");
        String nome = scanner.nextLine();

        System.out.println("\nDigite o departamento do Professor:");
        String departamento = scanner.nextLine();

        Professor professor = new Professor(id, nome, departamento); // Cria o professor
        professores.add(professor); // Adiciona o professor à lista de professores

        System.out.println("\nProfessor cadastrado com sucesso!");
    }
    
    public void cadastrarCurso() {
        System.out.println("\nDigite o ID do Curso:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (cursos.stream().anyMatch(e -> e.getId() == id)) { // Verifica se já existe um curso com o mesmo ID
            System.out.println("\nCurso já cadastrado.");
            return;
        }

        System.out.println("\nDigite o nome do Curso:");
        String nome = scanner.nextLine();

        System.out.println("\nDigite a carga horária do Curso:");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDigite o ID do Professor responsável:");
        if (professores.isEmpty()) { // Verifica se há professores cadastrados
            System.out.println("\nNenhum professor cadastrado.");
            return;
        }else{
            listarProfessores(); // Lista os professores para o usuário escolher
            int idProfessor = scanner.nextInt();
            scanner.nextLine();
    
            Professor professor = professores.stream().filter(p -> p.getId() == idProfessor).findFirst().orElse(null); // Busca o professor pelo ID
            if (professor != null) { // Verifica se o professor foi encontrado
                Curso curso = new Curso(id, nome, cargaHoraria, professor); // Cria o curso associando-o ao professor
                cursos.add(curso); // Adiciona o curso à lista de cursos
                System.out.println("\nCurso cadastrado com sucesso!");
            } else {
                System.out.println("\nProfessor não encontrado.");
            }
        }
    }

    public void cadastrarAluno() {
        System.out.println("\nDigite o ID do Aluno:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (alunos.stream().anyMatch(e -> e.getId() == id)) { // Verifica se já existe um aluno com o mesmo ID
            System.out.println("\nAluno já cadastrado.");
            return;
        }

        System.out.println("\nDigite o nome do Aluno:");
        String nome = scanner.nextLine();

        LocalDate dataNascimento = null; // Inicializa a variável para entrar no loop
        while (dataNascimento == null) { // Loop para validar a data de nascimento
            System.out.println("\nDigite a data de nascimento (AAAA-MM-DD):");
            String dataStr = scanner.nextLine();
            try {
                dataNascimento = LocalDate.parse(dataStr); // Converte a string para LocalDate
            } catch (DateTimeParseException e) {
                System.out.println("\nData inválida. Por favor, use o formato correto (AAAA-MM-DD).");
            }
        } // Validação da data de nascimento

        System.out.println("\nDigite o CPF do Aluno:");
        String cpf = scanner.nextLine().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

        if (cpf.length() != 11) {
            System.out.println("\nCPF inválido. Deve conter exatamente 11 dígitos numéricos.");
            return;
        } // Validação do CPF

        System.out.println("\nDigite o ID do Curso:");
        if (cursos.isEmpty()) { // Verifica se há cursos cadastrados
            System.out.println("\nNenhum curso cadastrado.");
            return;
        }else{
            listarCursos(); // Lista os cursos para o usuário escolher
            int idCurso = scanner.nextInt();
            scanner.nextLine();
    
            Curso curso = cursos.stream().filter(c -> c.getId() == idCurso).findFirst().orElse(null); // Busca o curso pelo ID
            if (curso != null) { // Verifica se o curso foi encontrado
                Aluno aluno = new Aluno(id, nome, dataNascimento, cpf, curso); // Cria o aluno associando-o ao curso
                alunos.add(aluno); // Adiciona o aluno à lista de alunos
                System.out.println("\nAluno cadastrado com sucesso!");
            } else {
                System.out.println("\nCurso não encontrado.");
            }
        }
    }

    public void listarProfessores() {
        if (professores.isEmpty()) { // Verifica se há professores cadastrados
            System.out.println("\nNenhum professor cadastrado.");
            return;
        }

        for (Professor professor : professores) { // Lista os professores
            System.out.println(professor);
            boolean associado = false; // Variável para verificar se o professor está associado a pelo menos um curso
            for (Curso curso : professor.getCursos()) { // Lista os cursos associados ao professor
                System.out.println("Curso: " + curso.getNome() + " | Alunos no Curso: " + curso.getAlunos().size());
                associado = true; // Marca que o professor está associado a pelo menos um curso
            }

            if (!associado) { // Verifica se o professor não está associado a nenhum curso
                System.out.println("Curso: Nenhum curso associado ao professor.");
            }
        }

    }

    public void listarCursos() {
        if (cursos.isEmpty()) { // Verifica se há cursos cadastrados
            System.out.println("\nNenhum curso cadastrado.");
            return;
        }
        cursos.forEach(System.out::println); // Imprime cada curso
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) { // Verifica se há alunos cadastrados
            System.out.println("\nNenhum aluno cadastrado.");
            return;
        }
        alunos.forEach(System.out::println); // Imprime cada aluno
    }
}
