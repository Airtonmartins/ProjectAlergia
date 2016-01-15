package mpoo.ufrpe.projetoalergia.dominio.dominoRemedio;

import android.net.Uri;

/**
 * Created by Airton on 08/11/2015.
 */
public class RemedioDTO {

    private int id;
    private String nome;
    private Uri uriIcone;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Uri getUriIcone() {
        return uriIcone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUriIcone(Uri uriIcone) {
        this.uriIcone = uriIcone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return nome;
    }

}
