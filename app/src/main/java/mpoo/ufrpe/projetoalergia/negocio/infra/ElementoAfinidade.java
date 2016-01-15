package mpoo.ufrpe.projetoalergia.negocio.infra;

/**
 * Created by zuao on 23/11/15.
 */
public class ElementoAfinidade {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public float getAfinidade() {
        return afinidade;
    }

    public void setAfinidade(float afinidade) {
        this.afinidade = afinidade;
    }

    private float afinidade;
}
