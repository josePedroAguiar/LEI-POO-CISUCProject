package JavaFiles;

/**
 *
 *  Classe que é filha da classe Artigo(abstrata) tendo esta como  classe mae,por sua vez,Publicações
 *  @author josepedroaguiar
 */
public class Conferencia extends Artigo {
    /**
     * Construtor do Artigo Conferencia
     * @param titulo é uma string
     * @param palavrasChave é uma string
     * @param tipo é uma string
     * @param audiencia é um inteiro
     * @param ano é um inteiro
     * @param resumo é uma string
     * @param localizacao é uma string
     * @param conferencia é uma string
     * @param data é um Objeto da class data
     */
    public Conferencia(String titulo,String palavrasChave,String tipo,int audiencia,int ano,String resumo,String localizacao,String conferencia,String data){
        super(titulo,palavrasChave,tipo, audiencia,ano,resumo,localizacao,conferencia,data);
    }

    /**
     * Metodo que devolve o fator de impacto do artigo de conferencia
     * @return Fator de impacto
     */
    public char fatorDeImpacto(){
        if (audiencia>=500)
            return 'A';
        else if (audiencia>=200)
            return 'B';
        else return 'C';
    }
}
