package mpoo.ufrpe.projetoalergia.negocio.infra;

import java.util.Comparator;

import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Componente;

/**
 * Created by Airton on 10/11/2015.
 */
public class ComparatorComponentePeso implements Comparator<Componente> {

    public int compare(Componente primeiroComponente, Componente segundaComponente) {
        if(primeiroComponente.getPeso() == segundaComponente.getPeso())
            return 0;

        if(primeiroComponente.getPeso() < segundaComponente.getPeso())
            return 1;
        else
            return -1;
    }

}
