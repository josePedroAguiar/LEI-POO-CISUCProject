package JavaFiles;

/**
 *Classe livro uma classe filha de publicações(apreseta algums novos atributos )
 *@author josepedroaguiar
 */
public class Livro extends Publicacao {
    private String editora;
    private long isbn;

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
     */
    public Livro(String titulo,String palavrasChave,String tipo,int audiencia,int ano,String editora,long isbn,String resumo){
        super(titulo,palavrasChave,tipo,audiencia,ano,resumo);
        this.editora=editora;
        this.isbn=isbn;
    }
    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos dos objetos desta classe
     */
    @Override
    public String toString() {
        return super.toString ()+
                "Editora-" + editora +" "+resumo+
                ",ISBN-" + isbn ;

    }

    /**
     * Metodo que devolve o fator de impacto do livro
     * @return Fator de impacto
     */
    public char fatorDeImpacto(){
        if (audiencia>=10000)
        return 'A';
        else if (audiencia>=5000)
            return 'B';
        else return 'C';
    }

//SET

    /**
     *Metodo que atribui um valor  ao atributo isbn
     * @param isbn Valor que vai ser dado ao atributo
     */
    public void setIsbn (long isbn) {
        this.isbn = isbn;
    }

    /**
     *Metodo que atribui um valor  ao atributo editora
     * @param editora Valor que vai ser dado ao atributo
     */
    public void setEditora (String editora) {
        this.editora = editora;
    }

//GETT

    /**
     *Metodo que devolve o valor do atributo-editora
     * @return Valor do atributo -editora do objeto
     */
    public String getEditora () {
        return editora;
    }

    /**
     **Metodo que devolve o valor do atributo-Isbn
     * @return Valor do atributo -isbn do objeto
     */
    public long getIsbn () {
        return isbn;
    }
}
