
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    //Nome do arquivo de dados
    static final String ARQUIVO = "dados.txt";
    static Scanner sc = new Scanner(System.in);

    /**
     * Programma principal.
     *
     * @param args
     */
    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== Cadastro de aluno =====");
            System.out.println("1 - Incluir");
            System.out.println("2 - Excluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Pesquisar");
            System.out.println("5 - Listar Lógico");
            System.out.println("6 - Listar Tudo");
            System.out.println("7 - Sair");
            System.out.print("Opção: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    excluir();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    pesquisar();
                    break;
                case 5:
                    listarLogico();
                    break;
                case 6:
                    listarTudo();
                    break;
                case 7:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 7);
    }

    /**
     * Inclui um novo aluno no arquivo.
     */
    public static void incluir() {
        try {
            //Abre o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true));
            //Leitura dos dados
            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Endereço: ");
            String endereco = sc.nextLine();
            System.out.print("Curso: ");
            String curso = sc.nextLine();
            System.out.print("Idade: ");
            String idade = sc.nextLine();
            System.out.print("Genero: ");            
            String genero = sc.nextLine();
            
            //Monta a linha/registro a ser salva no arquivo
            String linha = matricula + ";" + nome + ";" + endereco + ";"  + curso + ";" + idade + ";" + genero; 
            //Escreve o registro no arquivo
            bw.write(linha);
            //Quebra a linha
            bw.newLine();
            System.out.println("Registro incluído com sucesso!");
            //Fecha o arquivo
            bw.close();

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Exclui logicamente os dados de um aluno.
     *
     * Marca a matrícula com -1 o aluno excluído.
     */
    public static void excluir() {
        System.out.print("Informe a matrícula do aluno a ser ecluído: ");
        String matricula = sc.nextLine();
        
        //Controla se encontrou ou não o registro para ecluir
        boolean encontrou = false;
        String nomeArquivoTemp = "temp.txt";
        try {
            //Abre o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
            //Abreo o arquivo temporário para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivoTemp));
            
            String linha;
            //Percorre até o final do arquivo
            while ((linha = br.readLine()) != null) {
                //Divide os dados da linha pelo ;
                String[] dados = linha.split(";");
                
                if (dados[0].equals(matricula)) {
                    //Marca o aluno como escluído
                    dados[0] = "-1";                    
                    //Refaz a linha a ser gravada no arquivo temporário
                    linha = String.join(";", dados);
                    encontrou = true;
                }
                //Salva a linha no arquivo temporário
                bw.write(linha);
                bw.newLine();

                //Fecha os arquivos
                br.close();
                bw.close();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        //Declara os arquivos
        File arquivoOriginal = new File(ARQUIVO);
        File arquivoTemp = new File(nomeArquivoTemp);

        //Apaga o arquivo original e renomeia o temporário para o original
        if (arquivoOriginal.delete()) {
            arquivoTemp.renameTo(arquivoOriginal);
        }

        if (encontrou) {
            System.out.println("Registro excluído logicamente!");
        } else {
            System.out.println("Registro não encontrado!");
        }
    }

    /**
     * Altera os dados de um aluno.
     */
    public static void alterar() {
        System.out.print("Informe a matrícula do aluno a ser alterado: ");
        String matricula = sc.nextLine();
        
        //Controla se encontrou ou não o registro para alterar
        boolean encontrou = false;        
        String nomeArquivoTemp = "temp.txt";

        try {
            //Abre o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));  
            //Abre o arquivo temporário para escrta
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivoTemp));

            String linha;
            //Percorre até o final do arquivo
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(matricula)) {
                    encontrou = true;
                    System.out.print("Novo nome: ");
                    dados[1] = sc.nextLine();
                    System.out.print("Novo endereço: ");
                    dados[2] = sc.nextLine();
                    System.out.print("Novo curso: ");
                    dados[3] = sc.nextLine();
                    System.out.print("Nova idade: ");
                    dados[4] = sc.nextLine();
                    System.out.print("Novo genero: ");
                    dados[5] = sc.nextLine();

                    //Refaz a linha a ser gravada no arquivo temporário
                    linha = String.join(";", dados);
                }
                //Escreve a linha no arquivo
                bw.write(linha);
                bw.newLine();
                
                //Fecha os arquivos
                br.close();
                bw.close();
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        //Declara os arquivos
        File arquivoOriginal = new File(ARQUIVO);
        File arquivoTemp = new File(nomeArquivoTemp);

        //Apaga o arquivo original e renomeia o temporário para o original
        if (arquivoOriginal.delete()) {
            arquivoTemp.renameTo(arquivoOriginal);
        }

        if (encontrou) {
            System.out.println("Registro alterado com sucesso!");
        } else {
            System.out.println("Registro não encontrado!");
        }
    }

    /**
     * Pesquisa o aluno pela matricula no arquivo.
     *
     * Mostra os dados do aluno se encontrou.
     */
    public static void pesquisar() {
        System.out.print("Informe a matrícula a ser pesquisada: ");
        String matricula = sc.nextLine();
        
        //Controla se encontrou ou não o registro pesquisado
        boolean encontrou = false;
        try {
            //Abre o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
            
            String linha;
            while ((linha = br.readLine()) != null) {
                //Divide a linha pelo ;
                String[] dados = linha.split(";");
                if (dados[0].equals(matricula)) {
                    encontrou = true;
                    System.out.println("\nRegistro encontrado:");
                    System.out.println("Matrícula: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Endereço: " + dados[2]);
                    System.out.println("Curso: " + dados[3]);
                    System.out.println("Idade: " + dados[4]);
                    System.out.println("Genero: " + dados[5]);
                }
            }
            //Fecha o arquivo
            br.close();
            
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        if (!encontrou) {
            System.out.println("Registro não encontrado!");
        }
    }

    /**
     * Lista logicamente os dados dos alunos ativos.
     *
     * Lista somente os alunos com matricula diferente de -1.
     */
    public static void listarLogico() {
        try {
            //Abre o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
            
            String linha;
            System.out.println("\n=== Listagem Lógica ===");
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (!dados[0].equals("-1")) {
                    System.out.println(linha);
                }
            }
            //Fecha o arquivo
            br.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Lista todos os dados de alunos, inclusíve os ecluídos.
     */
    public static void listarTudo() {
        try {
            //Abre o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
            
            String linha;
            System.out.println("\n=== Listar tudo ===");
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            //Fecha o arquivo
            br.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
