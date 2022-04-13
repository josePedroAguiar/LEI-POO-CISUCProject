package JavaFiles;

/**
 * Artigo é uma class abtrata mãe para todas os Artigos(conferencia ou revista) excluindo os Livros de Artigos
 *  @author josepedroaguiar
 */
public abstract class Artigo extends Publicacao {
    private String localizacao;
    private String nomeDaConferencia;
    private Data data;

    /**
     * Construtor da class Artigo
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
    public Artigo(String titulo, String palavrasChave, String tipo, int audiencia, int ano, String resumo, String localizacao, String conferencia, Data data){
        super(titulo,palavrasChave,tipo,audiencia,ano,resumo);
        this.nomeDaConferencia=conferencia;
        this.localizacao = localizacao;
        this.data=data;
    }

    /**
     * Construtor da class Artigo
     * @param titulo é uma string
     * @param palavrasChave é uma string
     * @param tipo é uma string
     * @param audiencia inteiro
     * @param ano inteiro
     * @param resumo é uma string
     * @param localizacao é uma string
     * @param conferencia é uma string
     * @param data é uma string
     */

    public Artigo(String titulo, String palavrasChave, String tipo, int audiencia, int ano, String resumo, String localizacao, String conferencia, String data){
        super(titulo,palavrasChave,tipo,audiencia,ano,resumo);
        this.nomeDaConferencia=conferencia;
        this.localizacao = localizacao;
        try {
            String[] arrOfStr=data.split ("/");
            if(arrOfStr.length==3){
                int dia=Integer.parseInt (arrOfStr[0]);
                int mes =Integer.parseInt (arrOfStr[1]);
                int ano2=Integer.parseInt (arrOfStr[2]);
                if(ano2>=this.ano)
                    this.data=new Data(dia,mes,ano2,ano);
                else
                    this.data=new Data(0,0,0,ano);
            }


        }catch (NumberFormatException e){
            this.data=new Data(0,0,0);
        }
    }
///GET

    /**
     * get data da conferencia
     * @return data da conferencia
     */
    public Data getData(){
        return data;
    }

    /**
     * getlocalizaçao da conferencia
     * @return localizaçao da conferencia
     */
    public String getLocalizacao () {
        return localizacao;
    }

    /**
     * Get Nome Da Conferencia
     * @return Nome da conferencia
     */

    public String getNomeDaConferencia () {
        return nomeDaConferencia;
    }
///SETT

    /**
     * Define a data
     * @param data Valor que vai ser atrbuido
     */
    public void setData (Data data) {
        this.data = data;
    }
    /**
     * Define a Localizçao da conferencia
     * @param localizacao Valor que vai ser atrbuido
     */
    public void setLocalizacao (String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Define o Nome da Conferencia
     * @param nomeDaConferencia Valor que vai ser atrbuido
     */
    public void setNomeDaConferencia (String nomeDaConferencia) {
        this.nomeDaConferencia = nomeDaConferencia;
    }


    @Override
    public String toString() {
        return super.toString ()+ "Localizaçao=" + localizacao +
                ",Nome Da Conferencia='" + nomeDaConferencia + "'";
    }
}
