package JavaFiles;

import java.util.Comparator;

/**
 *
 * Class que serve para comparar as publicações por Ano
 *  @author josepedroaguiar
 */
class ComparacaoPorAno implements Comparator<Publicacao> {
    /**
     *Metodo que serve de comparador para o sort (organizaçao)do ArrayList
     * @param p1 publicação que vai ser comparado (perante o ano)
     * @param p2 publicação que serve de  comparaçao
     * @return valor da comparaçao 1 se for maior e -1 se for menor
     */
    public int compare (Publicacao p1, Publicacao p2) {
        return (p1.getAno() > (p2.getAno())) ? -1 : 1;

    }
}
