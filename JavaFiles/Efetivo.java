package JavaFiles;

/**
 *
 * Classe filha da classe investigador(são investigadores que tem certos atributos como gabinete...)
 *@author josepedroaguiar
 */
public class Efetivo extends Investigador {
    private long nTelefoneDEI;
    private double nDeGabinete;

    /**
     * Construtor de Objeto Efetivo extende Investigador
     * @param nome String dado para a class mae
     * @param email String dado para a class mae
     * @param g Grupo dado para a class mae
     * @param nTelefoneDEI long dado numero do telefone
     * @param nDeGabinete double do numero do gabinete
     */
    public Efetivo(String nome, String email, Grupo g, long nTelefoneDEI, double nDeGabinete){
        super(nome,email,g);
        this.nDeGabinete = nDeGabinete;
        this.nTelefoneDEI = nTelefoneDEI;

    }



    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os dados dos objetos efetivos
     */
    @Override
    public String toString() {
        return  super.toString()+
                ",nºTelemovelDEI= " + nTelefoneDEI +
                ",nºDeGabinete= " + nDeGabinete +"\n";
    }

    /**
     *Metodo que da infomrçao soubre a catecoria de cada Investigador
     * @return categoria ("Efetivo","Estudante")neste caso vai ser sempre efetivo
     */
    public String categoria(){
        return "Efetivo";
    }

    ///GET

    /**
     *Get
     * (Metodo que da) o nº Telefone DEI
     * @return NºTelefoneDEI valor do atributo do nº Telefone DEI do objeto
     */
    public long getNTelefoneDEI () {
        return nTelefoneDEI;
    }

    /**
     * Get
     * (Metodo que da) o nºDeGabinete
     * @return NºDeGabinete valor do atributo do nº De Gabinete do objeto
     */
    public double getNDeGabinete () {
        return nDeGabinete;
    }

    ///SETT

    /**
     *Sett
     * (Metodo que atribui um valor ) o nºDeGabinete
     * @param nDeGabinete valor que vai ser atribuido ao gabinete do  objeto Efetivo
     */
    public void setDeGabinete (double nDeGabinete) {
        this.nDeGabinete = nDeGabinete;
    }

    /**
     *Sett
     * (Metodo que atribui um valor ) o nº TELEFONE
     * @param nTelefoneDEI valor que vai ser atribuido ao nº telefone do objeto Efetivo
     */
    public void setnTelefoneDEI (long nTelefoneDEI) {
        this.nTelefoneDEI = nTelefoneDEI;
    }
}



