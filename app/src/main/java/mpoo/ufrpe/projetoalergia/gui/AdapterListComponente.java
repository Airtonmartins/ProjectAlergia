package mpoo.ufrpe.projetoalergia.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Componente;


public class AdapterListComponente extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Componente> itens;

    public AdapterListComponente(Context context, List<Componente> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
    /**
     *
     * @return Retorna a quantidade de itens
     */
    public int getCount()
    {
        return itens.size();
    }

    /**
     *
     * @param posicao  posição desejada
     * @return      Retorna o item de acordo com a posicao dele na tela.
     */
    public Componente getItem(int posicao)
    {
        return itens.get(posicao);
    }

    /**
     *
     * @param posicao   posicao
     * @return          retorna posicao gravado no objeto
     */
    public long getItemId(int posicao)
    {
        return posicao;
    }

    /**
     *
     * @param posicao       posicao desejada
     * @param view          objeto view a ser processado
     * @param parent        objeto ViewGroup
     * @return              Pega o item de acordo com a posção, infla o layout para podermos preencher os dados e retorna o view
     */

    public View getView(int posicao, View view, ViewGroup parent)
    {
        Componente item = itens.get(posicao);

        view = mInflater.inflate(R.layout.item_list_componente, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.txtComponente)).setText(item.getNome());
        String numero = String.valueOf(item.getPeso());
        numero = numero.replace(".",",");
        ((TextView) view.findViewById(R.id.txtPeso)).setText(String.valueOf(numero));

        if (posicao % 2 == 0){

            view.setBackgroundResource(R.drawable.backgroundlistviewbranco);
        } else {
            view.setBackgroundResource(R.drawable.backgroundlistview);
        }

        return view;
    }
}
