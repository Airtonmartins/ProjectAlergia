package mpoo.ufrpe.projetoalergia.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.RemedioDTO;


public class AdapterListRemedio extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<RemedioDTO> itens;

    /**
     *
     * @param context   contexto do adapter
     * @param itens     lista de remedios no tipo RemedioDTO a ser preenchida no adapter
     */
    public AdapterListRemedio(Context context, List<RemedioDTO> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     *
     * @return      Retorna a quantidade de itens
     */
    public int getCount()
    {
        if (itens != null) {
            return itens.size();
        } else {
            return 0;
        }
    }

    /**
     *
     * @param posicao      posição desejada
     * @return      Retorna o item de acordo com a posicao dele na tela.
     */
    public RemedioDTO getItem(int posicao)
    {
        return itens.get(posicao);
    }

    /**
     *
     * @param posicao       posicao desejada
     * @return      retorna posicao no objeto que ocupa posicao
     */
    public long getItemId(int posicao)
    {
        return posicao;
    }

    /**
     *
     * @param posicao       posicao do item desejado
     * @param view          objeto da view
     * @param parent        objeto ViewGroup
     * @return              Retorna o item de acordo com a posção, inflamos o layout para podermos preencher os dados.
     */
    public View getView(int posicao, View view, ViewGroup parent)
    {

        RemedioDTO item = itens.get(posicao);

        view = mInflater.inflate(R.layout.item_list_remedio, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.text)).setText(item.getNome());
        ((ImageView) view.findViewById(R.id.imagemview)).setImageURI(item.getUriIcone());


        if (posicao % 2 == 0){
            view.setBackgroundResource(R.drawable.backgroundlistviewbranco);
        } else {
            view.setBackgroundResource(R.drawable.backgroundlistview);
        }

        return view;
    }
}
