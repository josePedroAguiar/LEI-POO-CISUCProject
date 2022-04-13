package JavaFiles;

/**
 *
 * Classe ArtigosDeConferencia  é uma classe filha de Livro
 * esta apresenta como é mais alguns atributos especificos
 * @author josepedroaguiar
 */
public class ArtigosDeConferencia extends Livro {
    private String nomedaconferencia;
    private int ndeartigos;

    /**
     *
     * @param titulo String com o titulo da obra
     * @param palavrasChave Array de strings com as palavras chave
     * @param tipo String com o tipo de publicaçao
     * @param audiencia Inteiro com o numero de audencia da publicação
     * @param ano Inteiro com o ano de publicação
     * @param editora String com a editora
     * @param isbn Identificaça do livro (long)
     * @param resumo String com o resume
     * @param ndeartigos inteiro com o nº do artigo
     * @param nomedaconferencia nome da conferencia
     */
    public ArtigosDeConferencia(String titulo, String palavrasChave, String tipo, int audiencia, int ano, String editora, long isbn, String resumo, int ndeartigos, String nomedaconferencia){
        super(titulo,palavrasChave,tipo,audiencia,ano,editora,isbn,resumo);
        this.nomedaconferencia=nomedaconferencia;
        this.ndeartigos = ndeartigos;

    }
    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos dos objetos desta classe
     */
    @Override
    public String toString() {
        return
                super.toString ()+
                "Nomedaconferencia='" + nomedaconferencia + "'" +
                "Nº de artigos=" + ndeartigos +"\n";
    }
    /**
     * Metodo que devolve o fator de impacto do livro
     * @return Fator de impacto
     */
    public char fatorDeImpacto(){
        if (audiencia>=7500)
            return 'A';
        else if (audiencia>2500)
            return 'B';
        else return 'C';
    }

    //SET

    /**
     * Define a nome da conferencia
     * @param nomedaconferencia valor que se quer atribuir
     */
    public void setNomedaconferencia (String nomedaconferencia) { this.nomedaconferencia = nomedaconferencia; }

    /**
     * Define a numero do artigo
     * @param ndeartigos valor que se quer atribuir
     */
    public void setNdeartigos (int ndeartigos) {
        this.ndeartigos = ndeartigos;
    }

    //GETT

    /**
     * Devolve a nome do artigo
     * @return valor atribuido
     */
    public String getNomedaconferencia () {
        return nomedaconferencia;
    }

    /**
     * Devolve a nº do artigo
     * @return valor atribuido
     */
    public int getNdeartigos () {
        return ndeartigos;
    }
}
