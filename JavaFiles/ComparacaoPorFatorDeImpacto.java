package JavaFiles;

import java.util.Comparator;
/**
 *
 * Class que serve para comparar as publicações por fator de impacto
 * @author josepedroaguiar
 */
class ComparacaoPorFatorDeImpacto implements Comparator<Publicacao> {
    /**
     *Metodo que serve de comparador para o sort (organizaçao)do ArrayList
     * @param p1 publicação que vai ser comparado (perante o fator de Impacto)
     * @param p2 publicação que serve de  comparaçao
     * @return valor da comparaçao 1 se for maior e -1 se for menor
     */
    public int compare (Publicacao p1, Publicacao p2) {
        return (p1.fatorDeImpacto() > p2.fatorDeImpacto()) ? 1 : -1;

    }
}
