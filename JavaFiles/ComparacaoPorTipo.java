package JavaFiles;

import java.util.Comparator;

/**
 *
 * Class que serve para comparar as publicações pelo Tipo
 *  @author josepedroaguiar
 */
class ComparacaoPorTipo implements Comparator<Publicacao> {
    /**
     *  Metodo que serve de comparador para o sort (organizaçao)do ArrayList
     * @param p1 publicação que vai ser comparado (perante o Tipo)
     * @param p2 publicação que serve de  comparaçao
     * @return valor da comparaçao 1 se for maior e -1 se for menor
     */
    public int compare (Publicacao p1, Publicacao p2) {
        return (p1.getTipo().compareTo(p2.getTipo()) > 0) ? 1 : -1;

    }
}
