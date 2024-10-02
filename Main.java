import java.util.Scanner; // Importa a classe Scanner para receber entradas do usuário

public class Main {
    public static void main(String[] args) {
        Funcoes funcoes = new Funcoes(); // Instancia a classe Funcoes
        Scanner scanner = new Scanner(System.in); // Instancia o Scanner
        int opcao;
        
        do {
            System.out.println("\n\nMenu:");
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
                    funcoes.cadastrarProfessor();
                    break;
                case 2:
                    funcoes.cadastrarCurso();
                    break;
                case 3:
                    funcoes.cadastrarAluno();
                    break;
                case 4:
                    funcoes.listarProfessores();
                    break;
                case 5:
                    funcoes.listarCursos();
                    break;
                case 6:
                    funcoes.listarAlunos();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }

            if (opcao != 0) { // Pausa a execução para o usuário visualizar a mensagem
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
        
        scanner.close();
    }
};