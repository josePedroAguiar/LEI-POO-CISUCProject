package JavaFiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Objetos desta classe  encotram se num Centro de Investigaçao
 * @author josepedroaguiar
 */
public class Grupo implements Serializable {
    private static int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
    private static final int REFERENCIA = 5;
    private String nome;
    private String acronimo;
    private String nomeMembroResponsavel;
    private Efetivo membroResponsavel;
    private ArrayList<Investigador> investigadores;

    /**
     *Construtor da classe Grupo
     * @param nome nome do grupo(String)
     * @param acronimo acronimo do grupo(String)
     * @param nomeMebroResponsavel objeto da classe EFETTIVO
     */
    public Grupo(String nome, String acronimo, String nomeMebroResponsavel) {

        this.nome = nome;
        this.nomeMembroResponsavel = nomeMebroResponsavel;
        this.acronimo = acronimo;

        investigadores = new ArrayList<> ();
    }

    /**
     * Funçao que adiciona o investigador ao grupo
     * @param i investigador que se quer adcicionar
     */
    public void addInvestigador(Investigador i) {
        investigadores.add (i);

    }




    /**
     * Metodo que recebe r e devolve o nº de publicaçoe (consoante r escolhido)
     * @param r é o filtro este define ate que anos sao contadas as publicações
     *          (se  for escolhido menos -1 seram dadas todas as publicaçoes existentes indepedentemente do ano)
     * @return nº de publicaçoes do grupo(perante o criterio definido por r)
     */
///
    public int nDePublicacoes (int r){

        return publicacoesDoGrupo (r).size ();
    }

    /*
     * @param referencia
     * @return ultimasPublicaçoes
     */
    private ArrayList<Publicacao> publicacoesDoGrupo(int referencia) {
        ArrayList<Publicacao> ultimasPublicacoes;
        ultimasPublicacoes = new ArrayList<> ();

        for (Investigador i : investigadores) {
            Collections.sort (i.publicacoes);
            for (Publicacao p : i.publicacoes) {
                if (anoAtual- p.getAno () > referencia&&referencia!=-1) {
                    continue;
                } else {
                    Publicacao aux=null;
                    for (Publicacao p1 : i.publicacoes) {
                    if(p.getTitulo ().equals(p1.getTitulo ())){
                        aux=p1;
                        break;}}
                    if(aux!=null)
                    ultimasPublicacoes.add (p);
                }
            }
        }
    return ultimasPublicacoes;}
//
///ANO,TIPO e FATOR DE IMPACTO
//
    private String nDePublicacoesPorAnoTipoFatorDeImpacto () {
        Publicacao com=null;
        ArrayList<Publicacao> p=getUltimasPublicacoes(5);
        Collections.sort (p);
        ArrayList<Publicacao> aux=new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for (Publicacao i:p){
            if(p.indexOf(i)==p.size()-1){//É O ULTIMO ELEMENTO
                if(com!=null){//NAO  TEM APENAS UM ELELEMTO
                    if (i.getAno()==com.getAno()){//o elemeto atual é igual ao anterior
                        aux.add(i);
                        s.append(i.getAno()).append(": ").append(aux.size()).append("\n").append(nDePublicacoesPorTipo(aux)).append("\n");}
                    else {//o elemeto é diferente ao anterior
                        s.append(com.getAno()).append(": ").append(aux.size()).append("\n").append(nDePublicacoesPorTipo(aux)).append("\n");
                        aux=new ArrayList<>();
                        aux.add(i);
                        s.append(i.getAno()).append(": ").append(1).append("\n").append(nDePublicacoesPorTipo(aux)).append("\n");}

                }else {//tem apenas um elemeto
                    aux=new ArrayList<>();
                    aux.add(i);
                    s.append(i.getAno()).append(": ").append(aux.size()).append("\n").append(nDePublicacoesPorTipo(aux)).append("\n");}

            }
            else {
                if (com != null && i.getAno() != com.getAno()) {
                    s.append(com.getAno()).append(": ").append(aux.size()).append("\n").append(nDePublicacoesPorTipo(aux)).append("\n");
                    aux = new ArrayList<>();


                }
                aux.add(i);

            }
            com=i;
        }
        return s.toString();
    }

    private String nDePublicacoesPorTipo (ArrayList<Publicacao> p) {
        Publicacao com=null;
        ArrayList<Publicacao> aux=new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for (Publicacao i:p){
            if(p.indexOf(i)==p.size()-1){//É O ULTIMO ELEMENTO
                if(com!=null){//NAO TEM APENAS UM ELELEMTO
                    if(i.getTipo().equals(com.getTipo())){//o elemeto atual é igual ao anterior
                        aux.add(i);
                        s.append(i.getTipo()).append(": ").append(aux.size()).append("\n").append(nDePublicaoesPorFI(aux)).append("\n");}
                    else {//o elemeto é diferente ao anterior
                        s.append("\t\t-").append(com.getTipo()).append(": ").append(aux.size()).append("\n").append(nDePublicaoesPorFI(aux)).append("\n");
                        aux=new ArrayList<>();
                        aux.add(i);
                        s.append("\t\t-").append(i.getTipo()).append(": ").append(aux.size()).append("\n").append(nDePublicaoesPorFI(aux)).append("\n");}

                }else {//tem apenas um elemeto
                    aux=new ArrayList<>();
                    aux.add(i);
                    s.append("\t\t-").append(i.getTipo()).append(": ").append(aux.size()).append("\n").append(nDePublicaoesPorFI(aux)).append("\n");
                }
            }
            else {
                if (com != null && !i.getTipo().equals(com.getTipo())) {
                    s.append("\n\t\t-").append(com.getTipo()).append(": ").append(aux.size()).append(nDePublicaoesPorFI(aux)).append("\n");
                    aux = new ArrayList<>();


                }
                aux.add(i);


            }

            com=i;}

        return s.toString();}
    private String nDePublicaoesPorFI (ArrayList<Publicacao> p) {
        Publicacao com=null;
        ArrayList<Publicacao> aux=new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for (Publicacao i:p){
            if(p.indexOf(i)==p.size()-1){
                if(com!=null){
                    if (i.fatorDeImpacto() != (com.fatorDeImpacto())) {
                        s.append("\n\t\t\t->Fator de impacto  ").append(com.fatorDeImpacto()).append(" : ").append(aux.size()).append("\n");
                        aux = new ArrayList<>();
                    }

                }
                else {
                    aux=new ArrayList<>();
                }
                aux.add(i);
                s.append("\n\t\t\t->Fator de impacto  ").append(i.fatorDeImpacto()).append(" : ").append(aux.size()).append("\n");

            }
            else {
                if (com != null && i.fatorDeImpacto() != (com.fatorDeImpacto())) {
                    s.append("\n\t\t\tFator de impacto  ").append(com.fatorDeImpacto()).append(" : ").append(aux.size()).append("\n");
                    aux = new ArrayList<>();


                }
                aux.add(i);

            }
            com=i;}
        return s.toString();}

    private String listaDePublicacoesPorAnoTipoFatorDeImpacto () {
        ArrayList<Publicacao>ultimasP= publicacoesDoGrupo (REFERENCIA);
        Collections.sort ( ultimasP);
        StringBuilder string = new StringBuilder();
        if (!   ultimasP.isEmpty ()) {
            Publicacao aux = null;
            for (Publicacao i :   ultimasP) {
                if (aux == null || i.getAno () != aux.getAno ()){//ano é diferente
                    string.append("\n").append(i.getAno()).append(":\n");
                aux = null; }//para dar print ao restantes elementos visto que o ano mudou
                if(aux == null || !i.getTipo().equals(aux.getTipo()))
                    string.append("\n\t").append(i.getTipo()).append(":\n");
                if(aux == null || i.fatorDeImpacto() != aux.fatorDeImpacto())
                    string.append("\n\t\t").append(i.fatorDeImpacto()).append(":\n");
                string.append("\t\t\t\t").append(i.toString()).append("\n");
                aux = i;
            }
            return string.toString();
        } else {
            return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
        }
    }

    /*
     * TIPO
     */
    private String nDePublicacoesPorTipo () {
        ArrayList<Publicacao>auxf= publicacoesDoGrupo (REFERENCIA);
        ComparacaoPorTipo comparacaoPorTipo =new ComparacaoPorTipo();
        Collections.sort (  auxf, comparacaoPorTipo);
        StringBuilder s = new StringBuilder();
        int count = 0;
        if (!auxf.isEmpty ()) {
            Publicacao aux = null;
            for (Publicacao i :auxf) {
                if (auxf.indexOf (i) == auxf.size () - 1) {
                    if(aux!=null&&i.getTipo().equals(aux.getTipo()))
                        s.append("\n").append(aux.getTipo()).append(":").append(count).append("\n");
                    else if(aux!=null){
                        s.append("\n").append(aux.getTipo()).append(":").append(count).append("\n");
                        s.append("\n").append(i.getTipo()).append(":").append(1).append("\n");}
                    else {
                        s.append("\n").append(i.getTipo()).append(":").append(1).append("\n");
                    }
                } else if (aux != null && !(i.getTipo().equals (aux.getTipo()))) {
                    s.append("\n").append(aux.getTipo()).append(":").append(count).append("\n");
                    count = 0;
                }
                aux = i;
                count++;

            }
            return s.toString();
        } else {
            return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
        }
    }

    private String listaDePublicacoesPorTipo () {
        StringBuilder s = new StringBuilder();
        ArrayList<Publicacao>ultimasP= publicacoesDoGrupo (REFERENCIA);
        if (!ultimasP.isEmpty ()) {
            ComparacaoPorTipo comparacaoPorTipo =new ComparacaoPorTipo();
            Collections.sort (    ultimasP, comparacaoPorTipo);
            Publicacao aux = null;
            for (Publicacao i :    ultimasP) {
                if (aux == null || !(i.getTipo().equals (aux.getTipo())))
                    s.append("\n").append(i.getTipo().toUpperCase()).append(":\n\n");
                aux = i;
                s.append(i.toString()).append("\n");

            }
            return s.toString();
        } else {
            return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
        }
    }


    /*
     * FATOR DE IMPACTO
     */

    private String nDePublicaoesPorFI () {
        ArrayList<Publicacao>ultimasP=publicacoesDoGrupo (REFERENCIA);
        ComparacaoPorFatorDeImpacto comparacaoPorFatorDeImpacto = new ComparacaoPorFatorDeImpacto();
        Collections.sort (    ultimasP, comparacaoPorFatorDeImpacto);
        StringBuilder s = new StringBuilder();
        int count = 0;
        if (! ultimasP.isEmpty ()) {
            Publicacao aux = null;
            for (Publicacao i :   ultimasP) {
                if   (ultimasP.indexOf (i) == ultimasP.size () - 1) {
                    if(aux!=null&&i.fatorDeImpacto ()== aux.fatorDeImpacto ())
                        s.append("\n").append(aux.fatorDeImpacto()).append(":").append(count + 1).append("\n");
                    else if(aux!=null){
                        s.append("\n").append(aux.fatorDeImpacto()).append(":").append(count).append("\n");
                        s.append("\n").append(i.fatorDeImpacto()).append(":").append(1).append("\n");}
                    else {
                        s.append("\n").append(i.fatorDeImpacto()).append(":").append(1).append("\n");
                    }
                } else if (aux != null && i.fatorDeImpacto () != aux.fatorDeImpacto ()) {
                    s.append("\n").append(aux.fatorDeImpacto()).append(":").append(count).append("\n");
                    count = 0;
                }
                aux = i;
                count++;

            }
            return s.toString();
        } else {
            return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
        }
    }


    private String listaDePublicacoesPorFatorImpacto () {
        StringBuilder s = new StringBuilder();
        ArrayList<Publicacao>ultimasP= publicacoesDoGrupo (REFERENCIA);
        if (!ultimasP.isEmpty ()) {
            ComparacaoPorFatorDeImpacto comparacaoPorFatorDeImpacto = new ComparacaoPorFatorDeImpacto();
            Collections.sort (ultimasP, comparacaoPorFatorDeImpacto);
            Publicacao aux = null;
            for (Publicacao i : ultimasP) {
                if (aux == null || (i.fatorDeImpacto () != aux.fatorDeImpacto ()))
                    s.append("\n").append(i.fatorDeImpacto()).append(":\n\n");
                aux = i;
                s.append(i.toString()).append("\n");

            }
            return s.toString();
        } else {
            return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
        }
    }

    /*
     * Ano(Somente)
     */

    private String listaDePublicacoesPorAno () {
    StringBuilder s = new StringBuilder();
    ArrayList<Publicacao>ultimasP= publicacoesDoGrupo (REFERENCIA);
    if (!ultimasP.isEmpty ()) {
        ComparacaoPorAno comparacaoPorAno = new ComparacaoPorAno();
        Collections.sort (ultimasP, comparacaoPorAno);
        Publicacao aux = null;
        for (Publicacao i : ultimasP) {
            if (aux == null || (i.getAno() != aux.getAno()))
                s.append("\n").append(i.getAno()).append(":\n\n");
            aux = i;
            s.append(i.toString()).append("\n");

        }
        return s.toString();
    } else {
        return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
    }
}

    private String nDePublicaoesPorAno(){
        ArrayList<Publicacao>ultimasP=publicacoesDoGrupo (REFERENCIA);
        ComparacaoPorAno comparacaoPorAno = new ComparacaoPorAno();
        Collections.sort (ultimasP,comparacaoPorAno);
        StringBuilder s = new StringBuilder();
        int count = 0;
        if (! ultimasP.isEmpty ()) {
            Publicacao aux = null;
            for (Publicacao i :   ultimasP) {
                if   (ultimasP.indexOf (i) == ultimasP.size () - 1) {
                    if(aux!=null&&i.getAno ()== aux.getAno())
                        s.append("\n").append(aux.getAno()).append(":").append(count + 1).append("\n");
                    else if(aux!=null){
                        s.append("\n").append(aux.getAno()).append(":").append(count).append("\n");
                        s.append("\n").append(i.getAno()).append(":").append(1).append("\n");}
                    else {
                        s.append("\n").append(i.getAno()).append(":").append(1).append("\n");
                    }
                } else if (aux != null && i.getAno () != aux.getAno ()) {
                    s.append("\n").append(aux.getAno()).append(":").append(count).append("\n");
                    count = 0;
                }
                aux = i;
                count++;

            }
            return s.toString();
        } else {
            return "Grupo de Investigação nao tem nenhuma publicaçao recente\n";
        }
    }

    ///

    /**
     *Metodo que da o nº de publicaçoes constoante o argumento tipo colocado
     * @param tipo é seleçao do tipo da organizçao da apreentaçao dos numeros(String)
     * @return String com  nº das publicaçoes (tendo em consta o tipo escolhido)
     */
    public String nDePublicacoesDeG (String tipo){
        return switch (tipo) {
            case "A" -> nDePublicacoesPorAnoTipoFatorDeImpacto();
            case "B" -> nDePublicacoesPorTipo();
            case "C" -> nDePublicaoesPorFI();
            case "D" -> nDePublicaoesPorAno();
            case ("S"), ("0"), ("SAIR") -> "Menu Secundario";
            default -> "Seleção Invalida";
        };
        }

    /**
     *Metodo que da a lista das publicações da consoante ao que foi "selecionada"-(com argumento)
     * @param escolha maneira de selecionar o tipo de lista que queremos obter
     * @return String com a lista de publicaçoes da forma selecionada
     */
    public String listasDePublicacoes (String escolha) {
        return switch (escolha) {
            case "A" -> (listaDePublicacoesPorAnoTipoFatorDeImpacto());
            case "B" -> (listaDePublicacoesPorTipo());
            case "C" -> listaDePublicacoesPorFatorImpacto();
            case "D" -> (listaDePublicacoesPorAno());
            case ("0"), ("S"), ("SAIR") -> "Menu Principal";
            default -> ("Seleção Invalida\n");
        };
    }

    /**
     *Metodo que da a lista de investigadores organizada por  categoria(estudante,efetivo)
     * @return String com os investigadores organziados
     */

    public String listaDeInvestigadores() {
        StringBuilder string = new StringBuilder();
        Collections.sort (investigadores);
        Investigador aux = null;
        string.append("\n").append(nome).append(" - ").append(acronimo).append(":\n");
        for (Investigador i : investigadores) {
            if (aux == null || !(i.categoria ().equals(aux.categoria ())))
                string.append("\t").append(i.categoria()).append(":\n");// primeiro elemeto entra no if
            //e nas restantes iteraçoes  vai ser comparado a categoria  dos investigadores para assim se a categoria alterar
            //é dado print a categoria indicando assim que a categoria dos investigadores alterou
            aux = i;
            string.append("\t\t").append(i.toString()).append("\n");
        }
        return string.toString();

    }

    /**
     *Metodo que da return a um arraylist de publicaçoes (neste caso do grupo(de todos os investigadores que pertencem ao grupo))
     * @param filtro inteiro que seleciona as publicaçoes dos ultimos "filtro anos"
     * @return ArrayList com as publicações  perante o filtro escolhido
     */
    public ArrayList<Publicacao> getUltimasPublicacoes (int filtro) {
        return publicacoesDoGrupo (filtro);
    }

    /**
     *Metodo que da nº de investigadores de cada categoria
     * @return nº de investigadores de cada categoria
     */

    public int[] ndeInvestigadoresPorCategoria () {
        ///POSIÇAO 0->Estudante;
        // POSIÇAO 1->EFETIVO;
        int[] n = {0, 0};
        int count = 0;
        Investigador iaux = null;
        for (Investigador i : investigadores) {
            if (iaux != null && !i.categoria ().equals (iaux.categoria ())) {
                n[0] = count;
                n[1] = investigadores.size () - count;
            }
            iaux = i;
            count++;
        }
        return n;

    }



    /**
     * ToString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos dos objetos desta classe
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Investigador i : investigadores)
            s.append("\t-").append(i.getNome()).append("\n");
        return "GrupoDeInvestigaçao:\n" +
                "\"" + nome + '\"' +
                " - (" + acronimo + ')' +
                "\nMembro Responsavel: " + membroResponsavel
                + "\nMembros:\n" + s;
    }
//GET

    /**
     *
     *Metodo que devolve o atributo
     * @return valor do atributo
     */
    public String getNomeMembroResponsavel() {
        return nomeMembroResponsavel;
    }

    /**
     *Metodo que devolve o atributo
     * @return valor do atributo
     */
    public Efetivo getMembroResponsavel() {
        return membroResponsavel;
    }

    /**
     *Metodo que devolve o atributo Nome do grupo
     * @return valor do atributo
     */
    public String getNome() {
        return nome;
    }

    /**
     *Metodo que devolve o atributo Investigadores do Grupo
     * @return valor do atributo
     */
    public ArrayList<Investigador> getInvestigadores() {
        return investigadores;
    }

    /**
     *Metodo que devolve o atributo  Acronimo
     * @return valor do atributo
     */
    public String getAcronimo() {
        return acronimo;
    }

//SETT

    /**
     * Metodo que atribui um valor  ao atributo membro Responsavel
     * @param membroEfetivo valor atribuido
     */
    public void setMembroResponsavel(Efetivo membroEfetivo) {
    this.membroResponsavel = membroEfetivo;
}

    /**
     * Metodo que atribui um valor ao  atributo acronimo
     * @param acronimo valor atribuido
     */
    public void setAcronimo (String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     *
     *Metodo que atribui um valor ao  atributo nome
     * @param nome valor atribuido
     */
    public void setNome (String nome) {
        this.nome = nome;
    }

    /**
     *Metodo que atribui um valor ao  atributo membro responsavel
     * @param nomeMembroResponsavel valor atribuido
     */
    public void setNomeMembroResponsavel (String nomeMembroResponsavel) {
        this.nomeMembroResponsavel = nomeMembroResponsavel;
    }

    /**
     *Metodo que atribui um valor ao  atributo investigador
     * @param investigadores valor atribuido
     */
    public void setInvestigadores (ArrayList<Investigador> investigadores) {
        this.investigadores = investigadores;
    }
}
