package JavaFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * Classe CentroDeInvestigacao é uma clase onde contem os grupos de investigação
 * @author josepedroaguiar
 */
public class CentroDeInvestigacao {
    private ArrayList<Grupo> grupos = new ArrayList<> ();


    /**
     *Construtor quando chamamdo le os ficheiro objetos caso nao consiga
     *vai ler o ficheiros textos dos grupos,investigadores e publicações
     */
    public CentroDeInvestigacao() {

        try {
            FileInputStream fi = new FileInputStream (new File ("grupos.obj"));
            ObjectInputStream oi = new ObjectInputStream (fi);

            // lê objectos

            grupos = ( ArrayList<Grupo> ) oi.readObject ();


            oi.close ();
            fi.close ();

        } catch (FileNotFoundException e) {
            System.out.println ("Ficheiro De Objetos Não Encontrado\n" + lerFicheiroDeGrupos ()  +
                    lerFicheiroDeInvestigadores()+ lerFicheiroDePublicacoes ());


        } catch (IOException exception) {
            exception.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
// LEITURA DE FICHEIROS TEXTO


    /*
     * @return s que é uma string  de todos os erros/exceçoes que ocorreram
     */
    private String lerFicheiroDePublicacoes() {
        StringBuilder s = new StringBuilder();
        try {
            ArrayList<Investigador> investigadorsCISUC = investigadoresCISUC();
            FileReader f3 = new FileReader("publicaçoes.txt");
            BufferedReader br = new BufferedReader(f3);
            String line;
            while ( (line = br.readLine()) != null ) {
                String[] arrOfStr = line.split("\t");
                try {

                    if (arrOfStr[0].toUpperCase().equals("Livro".toUpperCase()) && arrOfStr.length == 9) {
                        int ano = Integer.parseInt(arrOfStr[6].replace(" ", ""));
                        int aud = Integer.parseInt(arrOfStr[4].replace(" ", ""));
                        long isbn = Long.parseLong(arrOfStr[7].replace("ISBN-", ""));
                        Publicacao pu = new Livro(arrOfStr[2], arrOfStr[3], arrOfStr[0], aud, ano, arrOfStr[3], isbn, arrOfStr[8]);

                        for (Investigador i : investigadorsCISUC) {
                            if(pu.setAutores(arrOfStr[1], i))
                            i.publicacoes.add(pu);


                        }


                    } else if (arrOfStr[0].toUpperCase().equals("Livro de Artigos de Conferencia".toUpperCase()) && arrOfStr.length == 11) {
                        int ano = Integer.parseInt(arrOfStr[6].replace(" ", ""));
                        int aud = Integer.parseInt(arrOfStr[4].replace(" ", ""));
                        long isbn = Long.parseLong(arrOfStr[7].replace("ISBN-", ""));
                        int na = Integer.parseInt(arrOfStr[9].replace("Artigo nº-", ""));
                        Publicacao pu = new ArtigosDeConferencia(arrOfStr[2], arrOfStr[3], arrOfStr[0], aud, ano, arrOfStr[5], isbn, arrOfStr[8], na, arrOfStr[10]);
                        for (Investigador i : investigadorsCISUC) {
                            if(pu.setAutores(arrOfStr[1], i))
                            i.publicacoes.add(pu);


                        }

                    } else if (arrOfStr[0].toUpperCase().equals("Artigos de conferencia".toUpperCase()) && arrOfStr.length == 10) {
                        int ano = Integer.parseInt(arrOfStr[5].replace(" ", ""));
                        int aud = Integer.parseInt(arrOfStr[4].replace(" ", ""));
                       Conferencia pu = new Conferencia(arrOfStr[2], arrOfStr[3], arrOfStr[0], aud, ano, arrOfStr[6], arrOfStr[7], arrOfStr[8], arrOfStr[9]);
                        for (Investigador i : investigadorsCISUC) {
                            if (pu.getData() != null && !(pu.getData().getERRO())) {
                                if(pu.setAutores(arrOfStr[1], i))
                                i.publicacoes.add(pu);
                            } else{
                                s.append("Data invalida ('").append(line).append("')\n");
                              break;}
                        }


                    } else if (arrOfStr[0].toUpperCase().equals("Artigo de revista".toUpperCase()) && arrOfStr.length == 13) {
                        int ano = Integer.parseInt(arrOfStr[5].replace(" ", ""));
                        int aud = Integer.parseInt(arrOfStr[4].replace(" ", ""));
                        int nRevista = Integer.parseInt(arrOfStr[10].replace("vol. ", ""));
                        Revista pu = new Revista(arrOfStr[2], arrOfStr[3], arrOfStr[0], aud, ano, arrOfStr[6], arrOfStr[7], arrOfStr[8], arrOfStr[9], nRevista, arrOfStr[12]);
                        for (Investigador i : investigadorsCISUC) {
                            if (pu.getData() != null && !(pu.getData().getERRO())) {
                                if(pu.setAutores(arrOfStr[1], i))
                                i.publicacoes.add(pu);
                            } else{
                                s.append("Data invalida ('").append(line).append("')\n");
                                break;}

                        }



                    } else if (arrOfStr[0].toUpperCase().equals("Capitulos de Livro".toUpperCase()) && arrOfStr.length == 11) {
                        int ano = Integer.parseInt(arrOfStr[6].replace(" ", ""));
                        int aud = Integer.parseInt(arrOfStr[4].replace(" ", ""));
                        long isbn = Long.parseLong(arrOfStr[7].replace(" ", ""));
                        String[] arrOfStr2=arrOfStr[10].replace("pp.","").split("-");
                        int incio =Integer.parseInt(arrOfStr2[0].replace(" ",""));
                        int fim =Integer.parseInt(arrOfStr2[1].replace(" ",""));
                        if(incio<=fim) {
                            Publicacao pu = new Capitulo(arrOfStr[2], arrOfStr[3], arrOfStr[0], aud, ano, arrOfStr[5], isbn, arrOfStr[8], arrOfStr[9], incio, fim);
                            for (Investigador i : investigadorsCISUC) {
                                if (pu.setAutores(arrOfStr[1], i))
                                    i.publicacoes.add(pu);
                            }
                        }

                    } else {
                        s.append("Linha:' ").append(line).append("' é uma publicação invalida invalida\n");
                    }



                } catch (NumberFormatException exception) {
                    s.append("Valores da publicaçao invalidos na linha'").append(line).append("'\n");
                }
            }
                } catch (IOException exception) {
                    return ("Ficheiro das Publicações invalido");
                }

        return s.toString();
    }




    /*
     *
     * @return s que é uma string  de todos os erros/exceçoes que ocorreram na leitura dos ficheiros de Investigadores
     */
    private String lerFicheiroDeInvestigadores () {
            StringBuilder s = new StringBuilder();
            try {
                FileReader f2 = new FileReader("investigadores.txt");
                BufferedReader br = new BufferedReader(f2);
                int nlinha = 0;
                String line;
                while ( (line = br.readLine()) != null ) {
                    String[] arrOfStr = line.split("\t");

                    if (arrOfStr.length == 6) {
                        int i;
                        for (i = 0; i < grupos.size(); i++) {

                            if (arrOfStr[2].equals(grupos.get(i).getNome())) {
                                break;
                            }
                        }
                        if (i != grupos.size() && arrOfStr[2].equals(grupos.get(i).getNome())) {
                            double gabinete = 0;
                            long telefone = 0;
                            try {
                                gabinete = Double.parseDouble(arrOfStr[4]);

                            } catch (NumberFormatException ex) {
                                s.append("Dados do gabinete no ficheiro dos Efetivos invalidos").append(nlinha).append("\n");
                            }
                            try {
                                telefone = Long.parseLong(arrOfStr[5]);

                            } catch (NumberFormatException ex) {
                                s.append("Dados do telefone no ficheiro dos Efetivos invalidos").append(nlinha).append("\n");
                            }

                            if (telefone != 0 && gabinete != 0) {
                                Efetivo in = new Efetivo(arrOfStr[0], arrOfStr[1], grupos.get(i), telefone, gabinete);

                                if (in.nome.toUpperCase().equals(grupos.get(i).getNomeMembroResponsavel().toUpperCase())) {
                                    grupos.get(i).setMembroResponsavel(in);
                                }
                                for (Investigador aux :investigadoresCISUC())   {
                                    if (aux.getNome().toUpperCase().equals(in.getNome().toUpperCase()) ) {
                                        s.append("Investigador já existe na Centro de Investigação-"+in.toString());
                                        in=null;
                                     break;}

                                }

                                
                                if(in!=null)
                                grupos.get(i).addInvestigador(in);
                            }

                        } else {
                            s.append("Grupo de investigaçao nao existente\n");
                        }


                    } else if (arrOfStr.length == 7) {
                        int i;
                        for (i = 0; i < grupos.size(); i++) {

                            if (arrOfStr[2].equals(grupos.get(i).getNome()) && arrOfStr[3].equals(grupos.get(i).getAcronimo())) {

                                break;
                            }
                        }
                        if (i != grupos.size() && arrOfStr[2].equals(grupos.get(i).getNome())) {
                            Investigador in = new Estudante(arrOfStr[0], arrOfStr[1], grupos.get(i), arrOfStr[4], arrOfStr[5], arrOfStr[6]);
                            for (Investigador aux :investigadoresCISUC()){
                                if (aux.getNome().toUpperCase().equals(in.getNome().toUpperCase())){
                                    s.append("Investigador já existe na Centro de Investigação-"+in.toString());
                                    in=null;
                                    break;}}
                            if(in!=null) {
                                if (in.getData() != null && !(in.getData().getERRO()))
                                    grupos.get(i).addInvestigador(in);
                                else
                                    s.append("Data de entrega de tese invalida do aluno ").append(in.getNome()).append("\n");
                            }
                        } else {
                            s.append("Grupo de investigaçao nao existente\n");
                        }


                    } else {
                        s.append("Dados Invalidos para Investigador da linha ").append(nlinha).append("\n");
                    }
                    nlinha++;

                }
                for (Investigador i : investigadoresCISUC()) {
                    if (i.categoria().equals("Estudante")) {
                        Estudante e = ( Estudante ) i;
                        s.append(e.defineOrientador());
                        if (!e.temOrientador())
                            e.getGrupoInvestigacao().getInvestigadores().remove(e);
                    }
                }

                for (int i = 0; i < grupos.size(); i++) {
                    if (grupos.get(i) != null && grupos.get(i).getMembroResponsavel() == null) {
                        s.append("GRUPO DE INVETIGAÇAO REMOVIDO-(falta de membro responsavel no grupo ").append(grupos.get(i).getAcronimo()).append(")\n");
                        grupos.remove(grupos.get(i));
                    }
                }
            } catch (IOException ex) {
                return ("Exceção ao ler o ficheiro dos Estudantes");
            }

            return s.toString();

        }


    /*
     *
     * @return s que é uma string  de todos os erros/exceçoes que ocorreram na leitura dos ficheiros dos Grupos
     */
    private String lerFicheiroDeGrupos() {
        StringBuilder s = new StringBuilder();
        try {
            FileReader f1 = new FileReader ("grupos.txt");
            BufferedReader br = new BufferedReader (f1);
            String line;

            while ( (line = br.readLine ()) != null ) {
                String[] arrOfStr = line.split ("\t");
                if (arrOfStr.length == 3) {
                    Grupo G = new Grupo(arrOfStr[0], arrOfStr[1], arrOfStr[2]);
                    grupos.add (G);
                } else {
                    s.append("Linha:'").append(line).append("' invalida");
                }
            }

        } catch (IOException ex) {
            return ("ERRO a ler o fichiro dos grupos de investigação");
        }
        return s.toString();
    }

    /*FIM LEITURA DE FICHEIROS */

    //
    /* ESCRITA DE FICHEIROS OBJETOS*/
//

    /**
     *Metodo responsavel por escrever os ficheiros Objetos (sendo este chamado quando o ultilizador fecha o programa corretamente)
     * @return  String com a informaçao se o ficheiro foi criado ou não
     */
    public String escreverFicheirosObj() {

        try {
            ///Grupos
            FileOutputStream f = new FileOutputStream (new File ("Grupos.obj"));
            ObjectOutputStream o = new ObjectOutputStream (f);
            // Escrever o  objectos dos Grupos ( o ArrayList ) no ficheiro

            o.writeObject (grupos);
            o.close ();
            f.close ();
            return "Ficheiro de Objetos criado";
        } catch (FileNotFoundException exception) {
            return "Impossivel escrever ficheiro";
        } catch (IOException exception) {
            return "Impossivel escrever objeto no ficheiro ";
        }


    }

    // METODOS AUXILARES  PARA OBTER TODAS AS PUBLICAÇOES/INVESTIGADORES

    /*
     * Percorre todos os Grupos de Investigação e da return de todas as publicaçoes de todos os grupos seguindo sem repeticçoes de publicaçoes
     *      bem dos  ultimos n anos sendo este n definido pelo argumento filtro
     * @param filtro
     * @return
     */
    private ArrayList<Publicacao> publicacoesDoCentro (int filtro) {

        ArrayList<Publicacao> ultimasPublicacoesDoCI = new ArrayList<> ();
        for (Grupo g : grupos) {

            for (Publicacao p : g.getUltimasPublicacoes(filtro)) {
                boolean existe = false;
                for (Publicacao pDeComparacao : ultimasPublicacoesDoCI) {
                    if (p.getTitulo ().equals (pDeComparacao.getTitulo ())) {
                        existe = true;//caso o já se encotre na lista das Publicaçoes da Cisuc esta nao é adicionada
                        break;
                    }


                }
                if (!existe)
                    ultimasPublicacoesDoCI.add (p);
            }

        }
        return ultimasPublicacoesDoCI;

    }

    /*
     * Percorre todos os Grupos de Investigação e da return de todas os Investigadores
     * @return ArrayList </ Investigadores> Arraylist de investigadores
     */
    private ArrayList<Investigador> investigadoresCISUC() {
        ArrayList<Investigador> investigadores = new ArrayList<> ();
        for (Grupo g : grupos) {
            boolean existe = false;
            for (Investigador i : g.getInvestigadores ()) {
                for (Investigador iDeComparacao : investigadores) {
                    if (i.getNome ().equals (iDeComparacao.getNome ())) {
                        existe = true;//caso o já se encotre na lista dos Investigadores da Cisuc este nao é adicionado
                        break;
                    }


                }
                if (!existe)
                    investigadores.add (i);
            }

        }
        return investigadores;
    }

//

    /**
     *Metodo que da o numero de publicaçoes consoante o filtro
     * @param r r é o filtor se este for -1 sera dado o n de todas as publcaçoes desde sempre se for 0,1,2...
     *          seram as publicaaçoes dos ultimos 0,1,2,.. anos respetivamente
     * @return publicaçoesCisuc (r).size ()- valor do tamalho do ArrayList que corresponde por sua vez ao nº de publicações
     */
    public int nDasUltimasPublicacoes (int r) {

        return publicacoesDoCentro(r).size ();

    }


//Metodos que fazem search/PESQUISA de Objetos (que apresentam certo atributo)

    /**
     * Metodo que pesquisa o grupo de Investigação atraves nome/acronimo
     * @param nomeG nome /acronimo do grupo
     * @return Um (objeto) Grupo em que se verifica que o nome ou acronimo é igual ao nomeG
     */
    public Grupo pesquisaDeGrupos(String nomeG) {
        Grupo aux = null;//só aceita se nomeG for igual ao acronimo ou nome  de um grupo
        for (Grupo g : grupos)
            if (g.getNome ().toUpperCase ().equals (nomeG.toUpperCase ()) || g.getAcronimo ().toUpperCase ().equals (nomeG.toUpperCase ())) {
                //só aceita se nomeG for igual ao acronimo ou nome  de um grupo
                aux = g;
                break;
            }
        return aux;
    }

    /**
     *Metodo que indica se o Investigador existe ou nao
     * @param nomeI nome /primeiro e ultimo nome do investigador que pertendemos saber se existe
     * @return se o investigador existe uma vez na base de dados
     */
    public boolean existeInvestigador(String nomeI) {
        ArrayList<Investigador> investigadoresCISUC= investigadoresCISUC ();
        for (Investigador i : investigadoresCISUC)
            if (i.getNome ().toUpperCase ().equals (nomeI.toUpperCase ())||i.getNomePeU().toUpperCase().equals(nomeI.toUpperCase ())) {
               return true;

            }

        return false;
    }

    /*
     *Metodo que pesquisa pelos investigadores por um que tenha como nome (completo/primeiro e ultimo)aos forencido em nomeI
     * @param nomeI
     * @return Investigador que satizfaz a condiçao(nome ser igual ao dado em nomeI)caso nao encontre este dara return de null
     */
    private Investigador pesquisaDeInvestigador(ArrayList<Investigador> investigadoresCISUC, String nomeI) {
        ///Metodo que pesquisa Investigador atraves  do nome(fornecido por arguemto)
        Investigador aux = null;
        for (Investigador i : investigadoresCISUC)
            if (i.getNome ().toUpperCase ().equals (nomeI.toUpperCase ())||i.getNomePeU().toUpperCase().equals(nomeI.toUpperCase ())) {
                aux = i;
                break;
            }
        return aux;
    }



///METODOS DE SORT (ORGANIZAÇÃO)QUE APENAS  DAO RETURN AO Nº DE OCORRENCIAS
// de uma certo atributo/(metodo exemplo Categoria)

    private String ndeInvestigadoresPorCategoria (ArrayList<Investigador> investigadoresCISUC) {
        String s = "";
        int count = 0;
        Collections.sort (investigadoresCISUC);//sort do array
        Investigador iaux = null;
        for (Investigador i : investigadoresCISUC) {
            if (iaux != null && (!i.categoria ().equals (iaux.categoria ()) || investigadoresCISUC.indexOf (i) == investigadoresCISUC.size () - 1)) {
                //se a categoria for diferente ou se for o ultimo investigador entra nesta condiçao assim obtemos o numero de elemetos para cada categoria
                s = s + "\n" + iaux.categoria ().toUpperCase () + ":" + count + "\n";
                s = s + "\n" + i.categoria ().toUpperCase () + ":" + (investigadoresCISUC.size () - count) + "\n";
                break;
            }//apenas esta condiçao é valida devido a ja ter sido organizado o array bem como devido apenas  existir 2 categorias de Investigador
            iaux = i;
            count++;
        }
        return s;

    }

    private String nTotalDePublicacoesPorTipo (ArrayList<Publicacao> publicacoes) {
        ComparacaoPorTipo comparacaoPorTipo = new ComparacaoPorTipo();
        Collections.sort (publicacoes, comparacaoPorTipo);
        Publicacao ia = null;
        StringBuilder string = new StringBuilder();
        int count = 0;
        for (Publicacao i : publicacoes) {
            if (publicacoes.indexOf (i) == publicacoes.size () - 1) {
                if (ia != null && i.getTipo().equals (ia.getTipo()))
                    string.append("\n").append(ia.getTipo()).append(":").append(count + 1).append("\n");
                else if (ia != null) {
                    string.append("\n").append(ia.getTipo()).append(":").append(count).append("\n");
                    string.append("\n").append(i.getTipo()).append(":").append(1).append("\n");
                } else {
                    string.append("\n").append(i.getTipo()).append(":").append(1).append("\n");
                }
            } else if (ia != null && !i.getTipo().equals (ia.getTipo())) {
                string.append("\n").append(ia.getTipo().toUpperCase()).append(":").append(count).append("\n");
                count = 0;
            }
            ia = i;
            count++;

        }
        return string.toString();
    }

///
///
    /**
     * Metodo da sort das publicaçoes do investigador que tem o nome (completo) igual a String  que é dada como 1º argumento
     * organizando as publicaçoes  consoante ao tipo dado (2º argumeto).
     * @param nomeDeI nome de investigador
     * @param tipo tipo de sort que vai ser dado as Publicaçoes do Investigador
     * @return String com o resultado (perante o tipo escolhido) caso o tipo nao seja valido este da return de uma string a dizer que "Seleçao" nao é valido
     */

    public String listaDePublicacoesDeI (String nomeDeI, String tipo) {
        if (pesquisaDeInvestigador (investigadoresCISUC (), nomeDeI) != null) {
            return switch (tipo) {
                case "A" -> (pesquisaDeInvestigador(investigadoresCISUC(), nomeDeI).listaDePublicacoesPorAnoTipoFI());
                case "B" -> (pesquisaDeInvestigador(investigadoresCISUC(), nomeDeI).listaDePublicacoesPorTipo());
                case "C" -> (pesquisaDeInvestigador(investigadoresCISUC(), nomeDeI).listaDePublicacoesPorFatorDeImpacto());
                case "D" -> (pesquisaDeInvestigador(investigadoresCISUC(), nomeDeI).listaDePublicacoesPorAno());
                case ("S"), ("0"), ("SAIR") -> "Menu Principal\n";
                default -> "Seleção Invalida\n";
            };
        } else return "Investigador nao existe na base de dados";
    }

    /**
     *Metodo que da return dos nº de cada grupo(Total de investigadores,Nº Investigadores de cada Categoria, Nº TOTAL DAS ULTIMAS PUBLICAÇÕES e Nº de publicaçoes
     * consoante ano/tipo/fator de impacto,tipo(somente),fator de impacto(somente) e ano(apenas).)
     *      * organizando as publicaçoes  consoante ao tipo dado (2º argumeto).
     * @param tipo qual das 4 principais escolhas se pertende obter
     * @return String com o resultado (perante o tipo escolhido) caso o tipo nao seja valido este da return de uma string a dizer que "Seleçao" nao é valido
     */
    public String nsDosGrupos (String tipo) {
        StringBuilder s;
        switch (tipo) {
            case "A": {
                s = new StringBuilder("\nNº TOTAL DE INVESTIGADORES:");
                for (Grupo g : grupos) {
                    if (g != null)
                        s.append("\n___________________________________________________\n").append(g.getNome()).append("\n___________________________________________________\nNº de membros: ").append(g.getInvestigadores().size()).append("\n");
                }
                return s.toString();
            }

            case "B": {
                s = new StringBuilder("\nInvestigadores Categoria".toUpperCase());
                for (Grupo g : grupos) {
                    if (g != null)
                        s.append("\n___________________________________________________\n").append(g.getNome()).append("\n___________________________________________________\nESTUDANTE: ").append(g.ndeInvestigadoresPorCategoria()[0]).append("\n").append("EFETIVO: ").append(g.ndeInvestigadoresPorCategoria()[1]).append("\n___________________________________________________\n");
                }
                return s.toString();
            }

            case "C": {
                s = new StringBuilder("\nNº TOTAL DAS ULTIMAS PUBLICAÇÕES");
                for (Grupo g : grupos) {
                    if (g != null)
                        s.append("\n___________________________________________________\n").append(g.getNome()).append("\n___________________________________________________\nTotal de publicações dos últimos 5 anos é ").append(g.nDePublicacoes(5)+"\n\n");
                }
                return (s.toString());
            }
            case ("D"): {
                String subEscolha2;
                do {
                    System.out.println ("Listar todos os grupos de investigação por:\n" +
                            "\tA-Organizar Por Ano/Tipo/Fator de Impacto\n" +
                            "\tB-Organizar Por Tipo\n" +
                            "\tC-Organizar Por Fator Impacto\n" +
                            "\tD-Organizar Por Ano\n" +
                            "\tS/Sair-Sair para o menu principal\n");
                    System.out.print ("Seleçao: ");
                    Scanner sc = new Scanner (System.in);
                    subEscolha2 = sc.nextLine ().toUpperCase ();
                    StringBuilder s2 = new StringBuilder();
                    for (Grupo g : grupos)
                        s2.append("\n___________________________________________________\n").append(g.getNome()).append("- ").append(g.nDePublicacoes(5)).append(" Publicações").append("\n___________________________________________________\n ").append(g.nDePublicacoesDeG(subEscolha2)).append("\n");
                    System.out.println (s2);
                } while ( !subEscolha2.equals ("S") && !subEscolha2.equals ("SAIR") );
            }

            case ("S"):
            case "0":
            case ("SAIR"):
                return "";


            default:
                return "Seleção Invalida\n";

        }
    }

    /**
     * Metodo que da return dos indicadores gerais (Nº de investigadores do CISUC,Nº de investigadores do CISUC de cada categoria,
     * Nº das publicações dos ultimos 5 anos)
     * @param escolha string que serve para qual indicador geral nos queremos obter
     * @return String com o indicador geral selecionado com a escolha
     */

    public String indicadoresGerais(String escolha) {


        switch (escolha) {
            case "A": {
                return "Nº de investigadores do CISUC:" + investigadoresCISUC ().size ();

            }
            case "B": {
                return ndeInvestigadoresPorCategoria(investigadoresCISUC ());
            }
            case "C": {
                return "Ultimas publicações:" + nDasUltimasPublicacoes(5);

            }
            case "D": {
                return "_________________________________\n" +
                        "Todas as Publicações por Tipo :\n_________________________________\n".toUpperCase () + nTotalDePublicacoesPorTipo(publicacoesDoCentro(-1)) +
                        "_________________________________\n" +
                        "Publicações dos Ultimos 5 anos por Tipo :\n_________________________________\n ".toUpperCase () + nTotalDePublicacoesPorTipo(publicacoesDoCentro(5));

            }
            case "Sair":
            case "0":
            case "S":
                return "";

            default: {
                return "Seleção Invalida\n";
            }
        }
    }
//GET

    /**
     *Metodo que devolve um valor ao Grupos
     * @return do ArrayList dos Grupos
     */

    public ArrayList<Grupo> getGrupos () {
        return grupos;
    }


//SET
    /**
     *Metodo que atribui um valor ao Grupos
     * @param grupos valor atribuido aos grupos
     */
    public void setGrupos (ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }
}