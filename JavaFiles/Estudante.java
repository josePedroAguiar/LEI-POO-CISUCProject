package JavaFiles;

/**
 * Classe Estudante (classe filha de Investigadores)
 *@author josepedroaguiar
 *
 */
public class Estudante extends Investigador {
    private String tituloDaTese;
    private Data data;
    private Efetivo orientador;

    /**
     *Construtor de Estudante
     * @param nome nome do estudante  (String)
     * @param email email (String)
     * @param g objeto Grupo onde pertence o Invetigador
     * @param tituloDaTese titulo da tese do estudante (String)
     * @param data objeto data que corresponde a data de entrega da tese
     * @param orientador objeto Efetivo que tem de ser do mesmo grupo de investigaçao do estudante
     */
    public Estudante (String nome, String email, Grupo g, String tituloDaTese, String data, String orientador) {
        super(nome, email, g);
        this.tituloDaTese = tituloDaTese;
        this.orientador = new Efetivo(orientador, " ", g, 1, 1.0);
        try {
            String[] arrOfStr = data.split("/");
            if (arrOfStr.length == 3) {
                int dia = Integer.parseInt(arrOfStr[0]);
                int mes = Integer.parseInt(arrOfStr[1]);
                int ano = Integer.parseInt(arrOfStr[2]);
                this.data = new Data(dia, mes, ano);
            }


        } catch (NumberFormatException e) {
            System.out.println("ERRO");
            this.data = new Data(0, 0, 0);
        }

    }

    /**
      * O metodo define o orientador
     */
    public String defineOrientador () {
        Efetivo aux = null;

        for (Investigador o : grupoInvestigacao.getInvestigadores()) {
            if (orientador.getNome().equals(o.getNome())
                    && o.getGrupoInvestigacao().getNome().equals(grupoInvestigacao.getNome())
                    && o.categoria().equals("Efetivo")) {
                aux = ( Efetivo ) o;
                break;
            }

        }
        if (aux != null) {
            orientador = aux;
        } else { orientador = null;
           return ("Orientador nao encontrado para o estudate '"+nome+"'\n");

        }
        return "";
    }

    /**
     * O metodo indica se o Estudante tem orientador
     * @return false se nao tiver orientador ou true se tiver orientador
     */
    public boolean temOrientador (){
        return orientador != null;
    }
    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos do objetos
     */
    @Override
    public String toString () {
        return super.toString() +
                " Titulo Da Tese='" + tituloDaTese + '\'' +
                " Data:" + data.toString() + "\n";

    }

    /**
     *O metodo indica a categoria do Estudante  que é sempre "Estudante"
     * @return "Estudante"
     */
    public String categoria () {
        return "Estudante";
    }

///GET

    /**
     * Metodo que devolve um valor da data de entrega da tese
     * @return Data da tese
     */
    public Data getData () {
        return data;
    }

    /**
     *Metodo que devolve um valor  do titulo da tese
     * @return Titulo da tese
     */
    public String getTituloDaTese () {
        return tituloDaTese;
    }

    /**
     *Metodo que devolve um valor  do orientador
     * @return Orientador
     */
    public Efetivo getOrientador () {
        return orientador;
    }
///SETT

    /**
     *Metodo que atribui um valor  a data
     * @param data valor que vai ser atribuido
     */
    public void setData (Data data) {
        this.data = data;
    }

    /**
     *Metodo que atribui um valor  ao orientador
     * @param orientador valor que vai ser atribuido
     */
    public void setOrientador (Efetivo orientador) {
        this.orientador = orientador;
    }

    /**
     *Metodo que atribui um valor  ao Titulo da tese
     * @param tituloDaTese valor que vai ser atribuido
     */
    public void setTituloDaTese (String tituloDaTese) {
        this.tituloDaTese = tituloDaTese;
    }
}
