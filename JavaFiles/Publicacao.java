package JavaFiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 *Classe que é mãe das seguintes classes:
 *                                               -Artigo(abstrata):
 *                                                       ->Artigo
 *                                                       ->Revista
 *                                               -Livro:
 *                                                     ->Capitulo
 *                                                      ->Artigos de Conferencia
 *
 *Class Publicação é abstrata (cada investigador pode ter um ou mais objetos das classes filhas da publicaçao)
 *  @author josepedroaguiar
 */

public abstract  class Publicacao implements Comparable<Publicacao>, Serializable {
    protected ArrayList<Investigador>autores;
    protected String titulo;
    protected String [ ]palavrasChave;
    protected String resumo;
    protected String tipo;
    protected int ano;
    protected int audiencia;

    /**
     * RECEBE como argumetos os atributos das classe
     * @param titulo String com o titulo da obra
     * @param palavrasChave Array de strings com as palavras chave
     * @param tipo String com o tipo de publicaçao
     * @param audiencia Inteiro com o numero de audencia da publicação
     * @param ano Inteiro com o ano de publicação
     * @param resumo String com o resume
     * @param autores arraylist com os autores
     */
    public Publicacao (ArrayList<Investigador>autores, String titulo, String[] palavrasChave, String tipo, int audiencia, int ano, String resumo){
        this.audiencia=audiencia;
        this.titulo=titulo;
        this.resumo=resumo;
        this.ano=ano;
        this.palavrasChave=palavrasChave;
        this.tipo=tipo;
        this.autores=autores;
    }

    /**
     *Construtor  da classe publicaçes
     * @param titulo string com o titulo da publicação
     * @param palavrasChave  array de strings com as palavras chaves da publicação
     * @param tipo string com o tipo de publicação
     * @param audiencia inteiro com o numero
     * @param ano inteiro com o ano de publicaçao
     * @param resumo string com o resumo da publicação
     */
    public Publicacao (String titulo, String palavrasChave, String tipo, int audiencia, int ano, String resumo){
        this.audiencia=audiencia;
        this.titulo=titulo;
        this.resumo=resumo;
        this.ano=ano;
        this.autores=new ArrayList<>();
        this.palavrasChave=palavrasChave.split(",");
        this.tipo=tipo;
    }

//Auxiliares para os Prints
    private String autoresCisuc(){
        StringBuilder nome= new StringBuilder();
        for (Investigador a:autores) {
            if (a.categoria().equals("Estudante")) {
                String[] arrOfStrig = a.nome.split(" ");
                nome.append(a.getNomePeU().charAt(0)).append(". ").append(arrOfStrig[arrOfStrig.length - 1]).append(",");
            } else
                nome.append("Professor ").append(a.getNomePeU()).append(",");
        }
        return nome.toString();
    }
    /**
     * toString
     * Metodo que cria e fornece uma string com os atributos do objeto desta classe
     * @return String com os atributos dos objetos desta classe
     */
    @Override
    public String toString() {
        return    titulo +
                ",Autores-" + autoresCisuc() ;
    }

//Comparação
    /**
     * Metodo que serve de comparador de dois objetos desta classe
     * @param o objeto que vai ser compardo
     * @return 1 se objeto for "maior" e - 1 se for menor
     */
    @Override
    public int compareTo(Publicacao o) {
        if (o.ano>ano)
            return 1;
        else if(o.ano==ano){
            if(o.tipo.compareTo (tipo)<0) {
                return 1;}
            if (o.tipo.equals (tipo)){
                if (o.fatorDeImpacto() > fatorDeImpacto())
                    return 1;}

        }
        return -1;

    }

//Setters
    /**
     *Metodo que define os autores do CISUC de cada publicaçao
     * @param autores String com todos os investigadores
     * @param i INVESTIGADOR CISUC
     * @return "true" se i estiver como autor e "false" caso isso nao  se verifique
     */
    public boolean setAutores(String autores,Investigador i){
        String[] arrayAutores =autores.split(",");
        for (String a : arrayAutores) {
            if (i.getNome ().toUpperCase ().equals (a.toUpperCase ())) {
                this.autores.add(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que atribui um valor  ao atributo titulo
     * @param titulo valor que vai ser dado oa atributo
     */
    public void setTitulo (String titulo) {
        this.titulo = titulo;
    }

    /**
     * Metodo que atribui um valor  ao atributo tipo
     * @param tipo valor que vai ser dado oa atributo
     */
    public void setTipo (String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que atribui um valor  ao atributo palavras chave
     * @param palavrasChave valor que vai ser dado oa atributo
     */
    public void setPalavrasChave (String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    /**
     * Metodo que atribui um valor  ao atributo AUDIENCIA
     * @param audiencia Valor que vai ser dado oa atributo
     */
    public void setAudiencia (int audiencia) {
        this.audiencia = audiencia;
    }

    /**
     * Metodo que atribui um valor  ao atributo autores
     * @param autores valor que vai ser dado oa atributo
     */
    public void setAutores (ArrayList<Investigador> autores) {
        this.autores = autores;
    }
    /**
     * Metodo que atribui um valor  ao atributo ano
     * @param ano valor que vai ser dado oa atributo com esse nome
     */
    public void setAno (int ano) {
        this.ano = ano;
    }
    /**
     * Metodo que atribui um valor  ao atributo resumo
     * @param resumo valor que vai ser dado oa atributo
     */
    public void setResumo (String resumo) {
        this.resumo = resumo;
    }


    ///GETTERS
    /**
     *Metodo que devolve o valor do atributo
     * @return o valor do atributo titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Metodo que devolve do tipo
     * @return valor do atributo-tipo
     */
    public String getTipo () {
        return tipo;
    }
    /**
     * Metodo que devolve do tipo
     * @return  o valor do resumo
     */
    public String getResumo () {
        return resumo;
    }

    /**
     * Metodo que devolve o valor da audiencia
     * @return valor do atributo-audiencia
     */
    public int getAudiencia() {
        return audiencia;
    }

    /**
     * Metodo que devolve o Arraylist de Investigadores que sao autores do livro
     * @return Arraylist dos autores
     */
    public ArrayList<Investigador> getAutores() {
        return autores;
    }


    /**
     * Metodo que devolve o valor do atributo ano
     * @return valor do atributo ano
     */
    public int getAno() {
        return ano;
    }
    /**
     * Metodo fornece que consoante a sua classes filhas da Publicaçao
     * calcula com os valores da audiencia o fator de impacto da publicaçao
     * @return do fator de impacto char(A,B,C)
     */
    public abstract char fatorDeImpacto();


}

