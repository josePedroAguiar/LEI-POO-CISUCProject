package JavaFiles;

/**
 *
 * Classe  extende a classe Artigo que por sua vez extende a classe Publicação
 *  @author josepedroaguiar
 */
public class Revista extends Artigo {
    private String nome;
    private int nRevista;

    /**
     * Construtor da classe revista
     * @param titulo string com o titulo da publicação
     * @param palavrasChave  array de strings com as palavras chaves da publicação
     * @param tipo string com o tipo de publicação
     * @param audiencia inteiro com o numero
     * @param ano inteiro com o ano de publicaçao
     * @param resumo string com o resumo da publicação
     * @param localizacao string com a localizacao da conferencia da qual foi baseada a revista
     * @param conferencia string com a nome da conferencia da qual foi baseada a revista
     * @param nomeRevista string com a nome da Revista
     * @param nRevista int numero da revista
     * @param data data da conferencia  da qual foi baseada a revista
     */
    public Revista(String titulo, String palavrasChave, String tipo, int audiencia, int ano, String resumo, String localizacao, String conferencia, String nomeRevista, int nRevista, String data){
        super(titulo,palavrasChave,tipo, audiencia,ano,resumo,localizacao,conferencia,data);
        this.nRevista = nRevista;
        this.nome=nomeRevista;

    }

    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos dos objetos desta classe
     */
    @Override
    public String toString() {
        return super.toString ()+
                ",Nome de Revista " + nome  + ",Nº Revista " + nRevista;
    }

    /**
     * Metodo que devolve o fator de impacto do livro
     * @return fator de impacto
     */
    public char fatorDeImpacto(){
        if (audiencia>=1000)
            return 'A';
        else if (audiencia>500)
            return 'B';
        else return 'C';
    }

//GETT

    /**
     * Metodo que devolve o nome da revista
     * @return valor do atributo  nome
     */
    public String getNome () {
        return nome;
    }

    /**
     * Metodo que devolve o nº da  revista
     * @return valor do atributo  nº Revista
     */
    public int getnRevista () {
        return nRevista;
    }

//SET

    /**
     * Metodo que atribui um valor  ao atributo nome (nome da revista)
     * @param nome valor que vai ser atribuido
     */
    public void setNome (String nome) {
        this.nome = nome;
    }

    /**
     * Metodo que atribui um valor  ao atributo nRevista (nº revista)
     * @param nRevista valor que vai ser atribuido
     */
    public void setnRevista (int nRevista) {
        this.nRevista = nRevista;
    }
}
