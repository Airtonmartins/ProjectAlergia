package mpoo.ufrpe.projetoalergia.dominio.dominoRemedio;

import java.util.List;

/**
 * Created by Allan on 16/10/2015.
 */
public class Remedio {

    private int id;
    private String nome;
    private String fabricante;
    private List<Componente> componentes;
    private String uriIcone;
    private String codigoDeBarra;

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public String getUriIcone() {
        return uriIcone;
    }

    public void setUriIcone(String uriIcone) {
        this.uriIcone = uriIcone;
    }

    public String toString()
    {
        return nome +"      "+fabricante;


    }
}
