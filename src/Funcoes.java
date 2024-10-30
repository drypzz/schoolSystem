import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas
import java.time.format.DateTimeParseException; // Importa a classe DateTimeParseException para tratar exceções de datas inválidas

import java.util.Scanner;

public class Funcoes {
    private Professor[] professores = Professor.getAllUsers(); // Lista de professores
    private Curso[] cursos = Curso.getAllUsers(); // Lista de cursos
    private Aluno[] alunos = Aluno.getAllUsers(); // Lista de alunos
    private Scanner scanner = new Scanner(System.in);

    public void cadastrarProfessor() {
        System.out.println("\n* Digite o ID do Professor:");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        if (Professor.getUserID(id) != null) { // Verifica se já existe um professor com o mesmo ID
            System.out.println("\n[ERRO] - Professor já cadastrado.");
            return;
        }

        System.out.println("\n* Digite o nome do Professor:");
        String nome = scanner.nextLine();

        System.out.println("\n* Digite o departamento do Professor:");
        String departamento = scanner.nextLine();

        Professor professor = new Professor(id, nome, departamento); // Cria o professor
        professor.getSave(); // Salva o professor no banco de dados

        System.out.println("\n[SUCESSO] - Professor cadastrado com sucesso!");
    }; // Método para cadastrar um professor
    
    public void cadastrarCurso() {
        System.out.println("\n* Digite o ID do Curso:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (Curso.getUserID(id) != null) { // Verifica se já existe um curso com o mesmo ID
            System.out.println("\n[ERRO] - Curso já cadastrado.");
            return;
        }

        System.out.println("\n* Digite o nome do Curso:");
        String nome = scanner.nextLine();

        System.out.println("\n* Digite a carga horária do Curso:");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine();

        if (professores.length == 0) { // Verifica se há professores cadastrados
            System.out.println("\n[ERRO] - Nenhum professor cadastrado.");
            return;
        }else{
            System.out.println("\n* Digite o ID do Professor responsável:");

            listarProfessores(); // Lista os professores para o usuário escolher

            System.out.println("");
            int idProfessor = scanner.nextInt();
            scanner.nextLine();
    
            Professor professor = Professor.getUserID(idProfessor); // Busca o professor pelo ID
            if (professor != null) { // Verifica se o professor foi encontrado
                Curso curso = new Curso(id, nome, cargaHoraria, professor); // Cria o curso associando-o ao professor
                curso.getSave();
                System.out.println("\n[SUCESSO] - Curso cadastrado com sucesso!");
            } else {
                System.out.println("\n[ERRO] - Professor não encontrado.");
            }
        }
    }; // Método para cadastrar um curso

    public void cadastrarAluno() {
        System.out.println("\n* Digite o ID do Aluno:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (Aluno.getUserID(id) != null) { // Verifica se já existe um aluno com o mesmo ID
            System.out.println("\n[ERRO] - Aluno já cadastrado.");
            return;
        }

        System.out.println("\n* Digite o nome do Aluno:");
        String nome = scanner.nextLine();

        LocalDate dataNascimento = null; // Inicializa a variável para entrar no loop
        while (dataNascimento == null) { // Loop para validar a data de nascimento
            System.out.println("\n* Digite a data de nascimento (AAAA-MM-DD):");
            String dataStr = scanner.nextLine();
            try {
                dataNascimento = LocalDate.parse(dataStr); // Converte a string para LocalDate
            } catch (DateTimeParseException e) {
                System.out.println("\n[ERRO] - Por favor, use o formato correto (AAAA-MM-DD).");
            }
        } // Validação da data de nascimento

        System.out.println("\n* Digite o CPF do Aluno:");
        String cpf = scanner.nextLine().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

        if (cpf.length() != 11) {
            System.out.println("\n[ERRO] - CPF deve conter exatamente 11 dígitos numéricos.");
            return;
        } // Validação do CPF

        if (cursos.length == 0) { // Verifica se há cursos cadastrados
            System.out.println("\n[ERRO] - Nenhum curso cadastrado.");
            return;
        }else{
            System.out.println("\n* Digite o ID do Curso:");

            listarCursos(); // Lista os cursos para o usuário escolher

            System.out.println("");
            int idCurso = scanner.nextInt();
            scanner.nextLine();
            

            Curso curso = Curso.getUserID(idCurso); // Busca o curso pelo ID
            if (curso != null) { // Verifica se o curso foi encontrado
                int idade = (LocalDate.now().getYear() - dataNascimento.getYear());
                Aluno aluno = new Aluno(id, nome, dataNascimento, cpf, idade, curso); // Cria o aluno associando-o ao curso
                aluno.getSave();
                System.out.println("\n[SUCESSO] - Aluno cadastrado com sucesso!");
            } else {
                System.out.println("\n[ERRO] - Curso não encontrado.");
            }
        }
    }; // Método para cadastrar um aluno

    public void listarProfessores() {
        professores = Professor.getAllUsers();

        if (professores.length == 0) {
            System.out.println("\n[ERRO] - Nenhum professor cadastrado.");
            return;
        }

        System.out.println("\n-----> Professores:");
        for (Professor professor : professores) {
            System.out.println("ID: " + professor.getId() + " | Nome: " + professor.getNome() + " | Departamento: " + professor.getDepartamento() + " | Cursos: " + professor.getCursos());
        }
    }; // Método para listar os professores

    public void listarCursos() {

        cursos = Curso.getAllUsers();

        if (cursos.length == 0) {
            System.out.println("\n[ERRO] - Nenhum curso cadastrado.");
            return;
        }

        System.out.println("\n-----> Cursos:");
        for (Curso curso : cursos) {
            System.out.println("ID: " + curso.getId() + " | Nome: " + curso.getNome() + " | Carga Horária: " + curso.getCargaHoraria() + " | Professor: " + curso.getProfessor().getNome() + " | Alunos: " + curso.getCountAlunos());
        }
    }; // Método para listar os cursos

    public void listarAlunos() {

        alunos = Aluno.getAllUsers();

        if (alunos.length == 0) {
            System.out.println("\n[ERRO] - Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n-----> Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | Data de Nascimento: " + aluno.getDataNascimento() + " | CPF: " + aluno.getCpf() + " | Idade: " + aluno.getIdade() + " | Curso: " + aluno.getCurso().getNome());
        }
    }; // Método para listar os alunos
    
}