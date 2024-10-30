import java.util.Scanner; // Importa a classe Scanner para receber entradas do usuário

public class Main {
    public static void main(String[] args) {
        Funcoes funcoes = new Funcoes(); // Instancia a classe Funcoes
        Scanner scanner = new Scanner(System.in); // Instancia o Scanner
        int opcao;
        
        do {
            System.out.println("\n\n-----> Menu:");
            System.out.println("1 - Cadastrar Professor");
            System.out.println("2 - Cadastrar Curso");
            System.out.println("3 - Cadastrar Aluno");
            System.out.println("4 - Listar Professores");
            System.out.println("5 - Listar Cursos");
            System.out.println("6 - Listar Alunos");
            System.out.println("0 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    funcoes.cadastrarProfessor(); // Chama o método para cadastrar um professor
                    break;
                case 2:
                    funcoes.cadastrarCurso(); // Chama o método para cadastrar um curso
                    break;
                case 3:
                    funcoes.cadastrarAluno(); // Chama o método para cadastrar um aluno
                    break;
                case 4:
                    funcoes.listarProfessores(); // Chama o método para listar os professores
                    break;
                case 5:
                    funcoes.listarCursos(); // Chama o método para listar os cursos
                    break;
                case 6:
                    funcoes.listarAlunos(); // Chama o método para listar os alunos
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }

            if (opcao != 0) { // Pausa a execução para o usuário visualizar a mensagem
                System.out.println("\n\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
        
        scanner.close();
    };
};