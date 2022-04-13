package JavaFiles;

/**
 *
 * Classe Capitulo é uma classe filha de Livro
 * esta apresenta como é alguns novos atributos especificos
 *  @author josepedroaguiar
 */
public class Capitulo extends Livro {

    private String nomeDecapitulo;
    private int paginaInicio;
    private  int paginaFim;

    /**
     /**
     * RECEBE como argumetos os atributos das classe mãe bem como novos valres para 2 novos atributos (editora e isbn)
     * @param titulo String com o titulo da obra
     * @param palavrasChave Array de strings com as palavras chave
     * @param tipo String com o tipo de publicaçao
     * @param audiencia Inteiro com o numero de audencia da publicação
     * @param ano Inteiro com o ano de publicação
     * @param editora String com a editora
     * @param isbn Identificaça do livro (long)
     * @param resumo String com o resume
     * @param capitulo String com o capitulo
     * @param paginaInicio int da pagina em que começa o capitulo
     * @param paginaFim int da pagina em que termina o capitulo
     */
    public Capitulo(String titulo,String palavrasChave,String tipo,int audiencia,int ano,String editora,long isbn,String resumo,String capitulo,int paginaInicio, int paginaFim){
        super(titulo,palavrasChave,tipo,audiencia,ano,editora,isbn,resumo);
        this.paginaFim=paginaFim;
        this.paginaInicio=paginaInicio;
        this.nomeDecapitulo=capitulo;

    }

    /**
     * Metodo que devolve o fator de impacto do livro
     * @return Fator de impacto
     */
    public char fatorDeImpacto(){
        if (audiencia>=10000)
            return 'A';
        else if (audiencia>5000)
            return 'B';
        else return 'C';
    }

}
