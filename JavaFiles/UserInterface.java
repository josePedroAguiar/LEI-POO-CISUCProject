package JavaFiles;

import java.util.Scanner;

/**
 *Classe que apenas serve de para interagir com o user
 *@author josepedroaguiar
 */
public class UserInterface {
    private final CentroDeInvestigacao CISUC;

    /**
     *Construtor da "Inteface grafica"-UserInterface
     * cria um objeto da class CentroDeInvestigacao;
     */
    public UserInterface () {
        CISUC = new CentroDeInvestigacao ();

    }


    private void opcao1 () {

        String subEscolha;
        do {
            System.out.println (
                    "__________________________________________________________________________\n" +
                            "1-Apresentar os indicadores gerais do CISUC:" +
                            "\n__________________________________________________________________________\n" +
                            "\tA-Numero total de membros\n" +
                            "\tB-Número de membros de cada categoria\n" +
                            "\tC-Número total de publicações dos últimos 5 anos\n" +
                            "\tD-Número de publicações por tipo\n" +
                            "\tS/Sair-Sair para interface principal\n" +
                            "__________________________________________________________________________\n");


            System.out.print ("Escolha: ");
            Scanner sc = new Scanner (System.in);
            subEscolha = sc.nextLine ().toUpperCase ();
            System.out.println (CISUC.indicadoresGerais (subEscolha));
        }
        while ( !subEscolha.equals ("SAIR") && !subEscolha.equals ("S") );


    }

    private void opcao2 () {
        System.out.print ("Nome/Acronimo do Grupo de Investigação:");
        Scanner sc = new Scanner (System.in);
        String grupo = sc.nextLine ().toUpperCase ();
        if (CISUC.pesquisaDeGrupos (grupo) != null) {
            String subEscolha;
            do {
                System.out.println ("__________________________________________________________________________\n" +
                        "2-Listar as publicações de um grupo de investigação, dos últimos 5 anos:\n" +
                        "__________________________________________________________________________\n" +
                        "\tA-Organizar Por Ano(tendo em conta o tipo e fator de imapacto)\n" +
                        "\tB-Organizar Por Tipo\n" +
                        "\tC-Organizar Por Fator Impacto\n" +
                        "\tD-Organizar Por Ano:\n" +
                        "\tS/Sair-Sair para o menu principal\n");


                System.out.print ("Escolha:  ");
                sc = new Scanner (System.in);
                subEscolha = sc.nextLine ().toUpperCase ();
                System.out.println (CISUC.pesquisaDeGrupos (grupo).listasDePublicacoes(subEscolha));

            } while ( !subEscolha.equals ("SAIR") && !subEscolha.equals ("S") );
        } else {
            System.out.println ("Grupo de Investigação Inexistente\n");
        }
    }

    private void opcao3() {
        System.out.print ("Grupo de Investigação/Acronimo:");
        Scanner sc = new Scanner (System.in);
        String grupo = sc.nextLine ().toUpperCase ();
        if (CISUC.pesquisaDeGrupos (grupo) != null) {
            System.out.println (CISUC.pesquisaDeGrupos (grupo).listaDeInvestigadores ());
        }
        else {
            System.out.println ("Grupo de Investigação Inexistente\n");
        }

    }

    private void opcao4() {
        System.out.print ("Investigador:");
        Scanner sc = new Scanner (System.in);
        String nomeDeI = sc.nextLine ().toUpperCase ();
        if (CISUC.existeInvestigador (nomeDeI)) {
            String subEscolha;
            do {

                System.out.println (
                        "__________________________________________________________________________\n" +
                                "4-Listar as publicações de " + nomeDeI + " agrupadas por ano\n" +
                                "__________________________________________________________________________\n" +
                                "\tA-Organizar Por Ano(tendo em conta o tipo e fator de imapacto)\n" +
                                "\tB-Organizar Por Tipo\n" +
                                "\tC-Organizar Por Fator Impacto\n" +
                                "\tD-Organizar Por Ano\n" +
                                "\tS/Sair-Sair para o menu principal\n");
                sc = new Scanner (System.in);
                System.out.print ("Escolha: ");
                subEscolha = sc.nextLine ().toUpperCase ();
                System.out.println (CISUC.listaDePublicacoesDeI(nomeDeI, subEscolha));

            } while ( !subEscolha.equals ("SAIR") && !subEscolha.equals ("S") );
        } else {
            System.out.println ("Investigador Inexistente\n");
        }
    }

    private void opcao5() {
        String subEscolha;
        do {

            System.out.println (
                    "__________________________________________________________________________\n" +
                            "5-Listar todos os grupos de investigação, e apresentar para cada grupo\n" +
                            "__________________________________________________________________________\n" +
                            "\tA-Numero total de membros\n" +
                            "\tB-Número de membros de cada categoria\n" +
                            "\tC-Número total de publicações dos últimos 5 anos\n" +
                            "\tD-Número de publicações por:\n" +
                            "\tS/Sair-Sair para interface principal\n");


            System.out.print ("Escolha: ");
            Scanner sc = new Scanner (System.in);
            subEscolha = sc.nextLine ().toUpperCase ();
            System.out.println (CISUC.nsDosGrupos(subEscolha));

        } while ( !subEscolha.equals ("SAIR") && !subEscolha.equals ("S") );
    }

    /**
     * Metodo Menu tem como funçao interagir com o utilizador tanto,
     * dando ao utilizador as opçoes de varios metodos,bem como,de selecionar quais quer realizar
     */
    public    void menu() {
        String escolha;
        do {

                System.out.println (
                        "__________________________________________________________________________\n" +
                                "Menu Principal:\n" +
                                "__________________________________________________________________________");
                System.out.println (
                        "1-Apresentar os indicadores gerais do CISUC\n" +
                                "2-Listar as publicações de um grupo de investigação, dos últimos 5 anos\n" +
                                "3-Listar os membros de um grupo de investigação agrupados por categoria\n" +
                                "4-Listar as publicações de um investigador agrupadas por:\n" +
                                "5-Listar todos os grupos de investigação por:\n" +
                                "0-SAIR\n" +
                                "__________________________________________________________________________");
                System.out.print ("Escolha:");
                Scanner sc = new Scanner (System.in);
                escolha = sc.nextLine ().toUpperCase ();
            switch (escolha) {
                case "1" -> opcao1();
                case "2" -> opcao2();
                case "3" -> opcao3();
                case "4" -> opcao4();
                case "5" -> opcao5();
                case "0" -> System.out.println(CISUC.escreverFicheirosObj());
                default -> System.out.println("Escolha invalida");
            }

        } while ( !escolha.equals("0"));


    }

    /**
     * Onde é criado um objeto da interface e chamado o seu metodo menu
     * @param args neste caso argumeto recebido é irrelevante
     */
    public static void main(String[] args) {
        UserInterface CISUC = new UserInterface();
        CISUC.menu ();

    }

}