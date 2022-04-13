package JavaFiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe Investigador(objetos desta classe encontram se nos grupo de Investigação)(abstrata)
 * @author josepedroaguiar
 */
public abstract class Investigador implements Comparable<Investigador>, Serializable {
    protected String nome;
    protected String email;
    protected Grupo grupoInvestigacao;
    protected ArrayList<Publicacao> publicacoes;

    /**
     * Construtor de
     * @param nome String com o nome do Investigador
     * @param email String com o mail do Investigador
     * @param g Grupo (objeto)
     */
    public Investigador(String nome, String email, Grupo g) {
        publicacoes = new ArrayList<> ();
        this.nome = nome;
        this.email = email;
        grupoInvestigacao = g;

    }

    /**
     * Metodo  abstrato
     * @return vai dar categoria consoante a classe filhas
     */
    public abstract String categoria();

    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos dos objetos desta classe
     */




    public String toString() {
        return getNomePeU() +
                ", Email-" + email +
                ", Grupo De Investigaçao-" + grupoInvestigacao.getAcronimo()+", "
                ;
    }

    @Override
    public int compareTo(Investigador o) {

        return categoria ().compareTo ((o.categoria ()));
    }

    /**
     * Metodo da lista de publicações por ano
     * @return string da lista de publicações
     */
    public String listaDePublicacoesPorAnoTipoFI () {
        StringBuilder string = new StringBuilder();
        Collections.sort (publicacoes);
        Publicacao aux = null;
        for (Publicacao i : publicacoes) {
            if (aux == null || i.getAno () != aux.getAno ()){
                string.append("\n").append(i.getAno()).append(":\n").append("\n\t").append(i.getTipo()).append(":\n").append("\n\t\t").append(i.fatorDeImpacto()).append(":\n");
            }
            else if(!i.getTipo().equals(aux.getTipo()))
                string.append("\n\t").append(i.getTipo()).append(":\n").append("\n\t\t").append(i.fatorDeImpacto()).append(":\n");
            else if(i.fatorDeImpacto() != aux.fatorDeImpacto())
                string.append("\n\t\t").append(i.fatorDeImpacto()).append(":\n");
            aux = i;
            string.append("\t\t\t\t").append(i.toString()).append("\n");
        }
        if(aux==null)
            return "Sem Publicações";
        return string.toString();
    }

    /**
     * Metodo lista de publicações por tipo
     * @return  de uma string com a lista de publicaçoes organizadas por tipo
     */
    public String listaDePublicacoesPorTipo () {
        StringBuilder string = new StringBuilder();
        ComparacaoPorTipo comparacaoPorTipo= new ComparacaoPorTipo();
        Collections.sort (publicacoes,comparacaoPorTipo);
        Publicacao aux = null;
        for (Publicacao i : publicacoes) {
            if (aux == null||!(i.getTipo().equals(aux.getTipo())))
                string.append("\n").append(i.getTipo()).append(":\n\n");
            aux = i;
            string.append(i.toString()).append("\n");
        }
        if(aux==null)
            return "Sem Publicações";
        return string.toString();
    }

    /**
     *Metodo lista de publicações por Fator de Impacto
     *@return  de uma string com a lista de publicaçoes organizadas por fator de impacto
     *
     */
    public String listaDePublicacoesPorFatorDeImpacto () {
        StringBuilder string = new StringBuilder();
        ComparacaoPorFatorDeImpacto comparacaoPorFatorDeImpacto =new ComparacaoPorFatorDeImpacto();
        Collections.sort (publicacoes, comparacaoPorFatorDeImpacto);
        Publicacao aux = null;
        for (Publicacao i : publicacoes) {
            if (aux == null || !(i.fatorDeImpacto () == aux.fatorDeImpacto ()))
                string.append("\n").append(i.fatorDeImpacto()).append(":\n\n");
            aux = i;
            string.append(i.toString()).append("\n");
        }
        if(aux==null)
            return "Sem Publicações";
        return string.toString();
    }
    /**
     *Metodo lista de publicações por Ano apenas
     *@return  de uma string com a lista de publicaçoes organizadas por ano(somente)
     *
     */
    public String listaDePublicacoesPorAno () {
        StringBuilder string = new StringBuilder();
        ComparacaoPorAno comparacaoPorAno= new ComparacaoPorAno();
        Collections.sort (publicacoes,comparacaoPorAno);
        Publicacao aux = null;
        for (Publicacao i : publicacoes) {
            if (aux == null||!(i.getAno()==(aux.getAno())))
                string.append("\n").append(i.getAno()).append(":\n\n");
            aux = i;
            string.append(i.toString()).append("\n");
        }
        if(aux==null)
            return "Sem Publicações";
        return string.toString();
    }


//GET

    /**
     * Metodo que devolve o atributo Nome do Investigadores
     * @return primeiro e ultimo nome
     */
    public String getNomePeU() {
    String[]arrOfStrig=nome.split ( " ");
    return arrOfStrig[0].substring(0,1).toUpperCase ()+arrOfStrig[0].substring(1).toLowerCase ()+" " +arrOfStrig[arrOfStrig.length-1].substring(0,1).toUpperCase ()+arrOfStrig[arrOfStrig.length-1].substring(1).toLowerCase ();
}

    /**
     * Metodo que devolve o atributo Nome do Investigadores
     * @return atributo Nome De Investigadores
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo que devolve o atributo Data do Investigadores
     * @return atributo  Data
     */
    public  Data getData(){
        return null;
    }

    /**
     * Metodo que devolve o atributo Publicações do Investigadores
     * @return atributo publicações
     */
    public ArrayList<Publicacao> getPublicacoes () {
        return publicacoes;
    }

    /**
     * Metodo que devolve o atributo email
     * @return atributo email
     */
    public String getEmail () {
        return email;
    }

    /**
     * Metodo que devolve o atributo Grupo de Investigação
     * @return atributo Grupo de Investigação
     */
    public Grupo getGrupoInvestigacao () {
        return grupoInvestigacao;
    }

//SETT

    /**
     * Metodo que atribui um valor  ao atributo nome
     * @param nome valor atrbuido ao atributo
     */
    public void setNome (String nome) {
        this.nome = nome;
    }

    /**
     *Metodo que atribui um valor  ao atributo email
     * @param email valor  atrbuido ao atributo
     */
    public void setEmail (String email) {
        this.email = email;
    }

    /**
     * Metodo que atribui um valor  ao atributo  que corresponde ao grupo de investigaçao
     * @param grupoInvestigacao valor  atrbuido a variável
     */
    public void setGrupoInvestigacao (Grupo grupoInvestigacao) {
        this.grupoInvestigacao = grupoInvestigacao;
    }

    /**
     *Metodo que atribui um valor  ao atributo  publicaçoes
     * @param publicacoes valor  atrbuido ao atributo
     */
    public void setPublicacoes (ArrayList<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }
}
