package mpoo.ufrpe.projetoalergia.negocio;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mpoo.ufrpe.projetoalergia.dao.RemedioDAO;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Componente;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Remedio;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.RemedioDTO;
import mpoo.ufrpe.projetoalergia.negocio.infra.ElementoAfinidade;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;

import static mpoo.ufrpe.projetoalergia.negocio.infra.Util.afinidadeEuclidiana;



public class RemedioNegocio {

    private static UsuarioNegocio usuarioNegocio;
    private static RemedioDAO dao;

    /* singleton */
    private static RemedioNegocio instancia = new RemedioNegocio();
    private RemedioNegocio(){}
    public static RemedioNegocio getInstancia(Context contexto)
    {
        usuarioNegocio = UsuarioNegocio.getInstancia(contexto);
        dao = RemedioDAO.getInstancia(contexto);
        return instancia;
    }

    private Sessao sessao = Sessao.getInstancia();

    public List<RemedioDTO> consultarRemedioPorNomeParcial(String nome){
       return dao.buscarRemediosPorNomeParcial(nome);
    }

    /**
     *
     * @param codigo    codigo de barra parcial que se deseja pesquisar
     * @return          Lista de remedios que possuem o codigo desejado
     */
    public List<RemedioDTO> consultarRemediosCodigoDeBarraParcial(String codigo){
        return dao.buscarRemediosCodigoBarraParcial(codigo);
    }

    /**
     *
     * @param id        id do remedio desejado
     * @return          objeto remedio preenchido.
     */
    public Remedio retornarRemedioPorId(int id){
        Remedio remedio = null;
        remedio = dao.pesquisarRemedioPorId(id);
        return remedio;
    }

    /**
     *
     * @param id        id do remedio desejado
     * @return          objeto RemedioDTO preenchido
     */
    public RemedioDTO retornarRemedioDTOPorId(int id){
        RemedioDTO remedio = null;
        remedio = dao.pesquisarRemedioDTOPorId(id);
        return remedio;
    }

    /**
     *
     * @param codigoDeBarra         codigo de barra a ser pesquisado
     * @return                      objeto Remedio preenchido.
     */
    public Remedio consultarRemedioPorCodigoDeBarra(String codigoDeBarra){
        Remedio remedio = null;
        return dao.pesquisarRemedioPorCodigoDeBarra(codigoDeBarra);
    }

    /**
     *
     * @param nome                  nome do remedio a ser pesquisado
     * @return                      objeto remedio preenchido
     * @throws ProjetoAlergiaException      Caso checagem de negocio falhem.
     */
    public Remedio retornarRemedioPorNome(String nome) throws ProjetoAlergiaException {
        Remedio remedio = dao.pesquisarRemedioPorNome(nome);
        StringBuilder builder = new StringBuilder();

        if (remedio == null) {
            builder.append("Não existe remedios no banco");
        }

        if (builder.length() > 0) {
            throw new ProjetoAlergiaException(builder.toString());
        }
        return remedio;
    }

    /**
     *
     * @param id            id do remedio ser checado se existe na lista negra do usuario logado.
     * @return              objeto do remedio, se existir.
     */
    public RemedioDTO checarRemedioNaListaNegra(int id){
        return dao.retornaRemedioListaNegraUsuarioLogado(id);
    }

    /**
     *
     * @param id    id do usuario que desejamos receber a lista negra.
     * @return      lista de remedios no tipo RemedioDTO que estao na lista negra do usuario desejado
     */
    public List<RemedioDTO> retornarListaNegraPorId(int id) {
        return dao.retornarListaNegraPorUid(id);
    }

    /**
     *
     * @return      total de remedios cadastrados no sistema.
     */
    public int totalRemedios () {
        return dao.contarTotalRemedios();
    }

    /**
     *
     * @return      total de componentes cadastrados no sistema.
     */
    public int totalComponentes () {
        return dao.contarTotalComponentes();
    }

    /**
     * Metodo dedicado a comparar se a lista1 esta propriamente contida na lista2 versao componentes
     *
     * @param lista1        lista1 de remedios a serem checados.
     * @param lista2        lista2 de remedios a serem checados.
     * @return              Se true, a lista1 esta completamente contida na segunda, se false nao esta.
     */
    public boolean listaContidaComponente(List<Componente> lista1, List<Componente> lista2) {

        for (Componente componente1: lista1) {
            boolean retval = false;
            for (Componente componente2: lista2) {
                if (componente1.getId() == componente2.getId()) {
                    retval = true;
                }
            }
            if (retval == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo dedicado a chegar se lista1 contem ao menos algum elemento de lista2 versao componentes
     * @param lista1                lista1 de remedios a serem checados.
     * @param lista2                lista2 de remedios a serem checados.
     * @return             Se true, a lista1 tem ao menos um item da lista2, se falso as listas nao tem nada em comum.
     */
    public boolean componenteEmcomum(List<Componente> lista1, List<Componente> lista2) {

        for (Componente componente1: lista1) {
            for (Componente componente2: lista2) {
                if (componente1.getId() == componente2.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo dedicado a comparar se a lista1 esta propriamente contida na lista2 versao remedio
     * @param lista1        lista1 de remedios a serem checados.
     * @param lista2        lista2 de remedios a serem checados.
     * @return              Se true, a lista1 esta completamente contida na segunda, se false nao esta.
     */
    public boolean listaContidaRemedio(List<RemedioDTO> lista1, List<RemedioDTO> lista2) {

        for (RemedioDTO remedio1: lista1) {
            boolean retval = false;
            for (RemedioDTO remedio2: lista2) {
                if (remedio1.getId() == remedio2.getId()) {
                    retval = true;
                }
            }
            if (retval == false) {
                return false;
            }
        }
        return true;

    }

    /**
     * Metodo dedicado a chegar se lista1 contem ao menos algum elemento de lista2 versao remedio
     *
     * @param lista1            lista1 de remedios a serem checados.
     * @param lista2            lista2 de remedios a serem checados.
     * @return                  Se true, a lista1 tem ao menos um item da lista2, se falso as listas nao tem nada em comum.
     */
    public boolean remedioEmcomum(List<RemedioDTO> lista1, List<RemedioDTO> lista2) {

        if ( lista1 == null || lista1.size() == 0) {
            return false;
        }
        for (RemedioDTO remedio1: lista1) {
            for (RemedioDTO remedio2: lista2) {
                if (remedio1.getId() == remedio2.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo dedicado a remover objetos de RemedioDTO duplicados em uma mesma lista
     * @param lista         lista a ser filtrada
     * @return              lista contida por objetos sem repeticoes
     */
    public  List<RemedioDTO> removerDuplicatasEmListas(List<RemedioDTO> lista) {

        List<RemedioDTO> result = new ArrayList<RemedioDTO>();

        Set<String> titles = new HashSet<String>();

        for( RemedioDTO remedioDTO : lista ) {
            if(titles.add(remedioDTO.getNome() )) {
                result.add( remedioDTO );
            }
        }

        return result;
    }

    /**
     * Metodo dedicado a gerar uma lista de remedios recomendados com base nas preferencias de outros usuarios
     * @return          Lista de remedios no tipo RemedioDTO, que constitues de remedios de outras listas negras de outros usuarios de perfil similar.
     * @throws ProjetoAlergiaException      Caso verificações de negocio falhem.
     */
    public List<RemedioDTO> sugestoesFiltroColaborativo() throws ProjetoAlergiaException {
        List<RemedioDTO> listaNegra;

        int user = sessao.getUsuarioLogado().getId();

        int tr = totalRemedios();
        int tu = usuarioNegocio.totalUsuarios();

        if ( retornarListaNegraPorId(user) == null ) {
            throw new ProjetoAlergiaException("Não é possivel sugerir! adicione ao menos um item a listanegra!");
        }
        if (tr <= 3) {
            throw new ProjetoAlergiaException("Não é possível sugerir! cadastre mais remedios...");
        }
        if (tu <= 2) {
            throw new ProjetoAlergiaException("Não é possível sugerir! cadastre mais usuários...");
        }

        //iniciado matriz no tamanho apropiado
        float[][] matriz = new float[tu][tr];

        //carregando listanegra de remedios de cada usuario na matriz
        //cada remedio da lista negra ganha 1 e os outros 0 (padrao do int)
        for (int uid = 1; uid <= tu; uid++) {
            listaNegra = retornarListaNegraPorId(uid);
            if (listaNegra != null) {
                for (RemedioDTO remedio : listaNegra) {
                    matriz[uid - 1][remedio.getId() - 1] = 1;
                }
            }
        }

        //gera vetor de usuarios com afinidade
        ElementoAfinidade[] vetor = new ElementoAfinidade[tu-1];

        int index = 0;

        //primeiro inicializa vetor com os usuarios a processar
        for (int uid = 1; uid <= tu; uid++) {
            if (user != uid) {
                vetor[index] = new ElementoAfinidade();
                //usuario a processar
                vetor[index].setId(uid);
                //aqui calcula coeficiente de afinidade
                if (remedioEmcomum(retornarListaNegraPorId(uid), retornarListaNegraPorId(user))) {
                    if (! listaContidaRemedio(retornarListaNegraPorId(uid), retornarListaNegraPorId(user))) {
                        vetor[index].setAfinidade((float) afinidadeEuclidiana(user, uid, matriz));
                    } else {
                        vetor[index].setAfinidade(0);
                    }
                } else {
                    vetor[index].setAfinidade(0);
                }
                index++;
            }
        }


        List<RemedioDTO> sugestoes = topN(5, vetor, true);

        if (sugestoes == null || sugestoes.size() == 0) {
            throw new ProjetoAlergiaException("No momento a lista de sugestões para você é vazia!");
        }

        return sugestoes;
    }

    /**
     * Metodo dedicado a gerar uma lista de remedios recomendados com base nas similaidades outros remedios
     * @return          Lista de remedios no tipo RemedioDTO, que constitue outros remedios de perfil similar.
     * @throws ProjetoAlergiaException      Caso verificações de negocio falhem.
     */
    public List<RemedioDTO> sugerirRemediosSimilares() throws ProjetoAlergiaException {
        List<Componente> componentes;
        List<RemedioDTO> listaNegra;
        List<RemedioDTO> sugestoes = new ArrayList<RemedioDTO>();

        int tamanhoAnterior = 0;
        int user = sessao.getUsuarioLogado().getId();

        int tr = totalRemedios();
        int te = totalComponentes();

        if ( retornarListaNegraPorId(user) == null ) {
            throw new ProjetoAlergiaException("Não é possivel sugerir! adicione ao menos um item a listanegra!");
        }

        //sugerir remedios com base na afinidade de elementos nos remedios da sua lista negra;
        listaNegra = retornarListaNegraPorId(user);

        for (RemedioDTO remedio: listaNegra) {

            //criar matriz que relaciona remedios e elementos
            //iniciado matriz no tramanho apropiado

            float[][] matriz = new float[tr][te];

            //preenchendo matriz cada linha = remedios e cada coluna = peso do elemento

            for (int rid = 1; rid <= tr; rid++) {
                componentes = retornarRemedioPorId(rid).getComponentes();
                if (componentes != null) {
                    for (Componente componente : componentes) {
                        matriz[rid - 1][componente.getId() - 1] = componente.getPeso();
                    }
                }
            }

            //gera vetor de remedios com afinidade
            ElementoAfinidade[] vetor = new ElementoAfinidade[tr-1];

            int index = 0;

            //primeiro inicializa vetor com os remedios a processar
            for (int rid = 1; rid <= tr; rid++) {
                if (remedio.getId() != rid) {
                    vetor[index] = new ElementoAfinidade();
                    //remedio a processar
                    vetor[index].setId(rid);
                    //aqui calcula coeficiente de afinidade
                    if (componenteEmcomum(retornarRemedioPorId(rid).getComponentes(), retornarRemedioPorId(remedio.getId()).getComponentes())) {
                        if (! listaContidaComponente(retornarRemedioPorId(rid).getComponentes(), retornarRemedioPorId(remedio.getId()).getComponentes())) {
                            vetor[index].setAfinidade((float) afinidadeEuclidiana(remedio.getId(), rid, matriz));
                        } else {
                            //se o remedio tem todos os componentes contidos
                            boolean test = false;
                            //verifica se ele ja esta na lista negra...
                            for (RemedioDTO remedioDTO : listaNegra) {
                                if (remedioDTO.getNome() == retornarRemedioPorId(rid).getNome()) {
                                    test = true;
                                    break;
                                }
                            }

                            if (test = true) {
                                //se tiver nao interessa adicionar
                                vetor[index].setAfinidade(0);
                            } else {
                                //se nao tiver ele tem otima afinidade
                                vetor[index].setAfinidade(1);
                            }
                        }

                    } else {
                        //se o remedio tem nada em comum nao interessa adicionar
                        vetor[index].setAfinidade(0);
                    }
                    index++;
                }
            }

            //nullpoint fix
            List<RemedioDTO> temp = null;
            temp = topN(5, vetor, false);
            tamanhoAnterior = sugestoes.size();
            if (sugestoes != null) {
                if (temp != null && temp.size() > 0) {
                    sugestoes.addAll(temp);
                }
            }
            Log.i("__>", "loop! >" + sugestoes.size() + " " + tamanhoAnterior);

        }

        sugestoes = removerDuplicatasEmListas(sugestoes);

        if (sugestoes == null || sugestoes.size() == 0) {
            throw new ProjetoAlergiaException("No momento a lista de sugestões para você é vazia!");
        }

        return sugestoes;

    }

    public List<RemedioDTO> topN (int n, ElementoAfinidade[] vetor, boolean tipo) throws ProjetoAlergiaException {

        List<RemedioDTO> sugestoes = new ArrayList<RemedioDTO>();


        int user = sessao.getUsuarioLogado().getId();

        Log.i("---&>","entrando em topN("+Integer.toString(n)+" "+tipo);

        //sort no vetor pela afinidade
        Arrays.sort(vetor, new Comparator<ElementoAfinidade>() {
            @Override
            public int compare(final ElementoAfinidade entry1, final ElementoAfinidade entry2) {
                final Float afinidade1 = entry1.getAfinidade();
                final Float afinidade2 = entry2.getAfinidade();
                return Float.compare(afinidade2, afinidade1);
            }
        });

        Log.i("---&>", "Sort concluido!");

        StringBuilder mensagemdebug = new StringBuilder();
        int index = 0;

        for (ElementoAfinidade v: vetor) {
            mensagemdebug.append(" "+index+":").append(v.getId()+"=").append(v.getAfinidade());
            index++;
        }
        Log.i("-----!>", mensagemdebug.toString());

        if ( vetor[0].getAfinidade() == 0) {
            Log.i("-----!>","todos as afinidades sao nulas! :(");
            throw new ProjetoAlergiaException("No momento a lista de sugestões para você é vazia!");
        }

        //gera lista topN de recomendacoes contendo remedios do usuario com mais afinidade que o usuario logado ainda nao possue

        //o primeiro é o  usuario com mais afinidade
        //cada sugestao vai ser adicionado a esta lista

        Log.i("---&>","entrando no for princpal!");

        //iterando os itens do vetor ate o resultado ter n itens ou o fim do vetor...
        for (ElementoAfinidade item: vetor) {

            Log.i("---&>","item = "+item.getId()+" "+item.getAfinidade());

            if ( item.getAfinidade() == 0) {
                Log.i("---&>","afinidade eh zero saindo do for...");
                break;
            }

            if (tipo) {
                //item esta guardando info de usuarios
                int uid = item.getId();

                Log.i("-----!>", "Esse eh o uid: "+Integer.toString(uid));

                //removendo da lista de sugestoes remedios q o usuario ja possue.
                for (RemedioDTO remedio1: retornarListaNegraPorId(uid)) {
                    boolean retval = false;
                    for (RemedioDTO remedio2 : retornarListaNegraPorId(user)) {
                        if (remedio1.getId() == remedio2.getId()) {
                            retval = true;
                            break;
                        }
                    }
                    if (sugestoes.size() > 0) {
                        for (RemedioDTO remedio2 : sugestoes) {
                            if (remedio1.getId() == remedio2.getId()) {
                                retval = true;
                                break;
                            }
                        }
                    }
                    if (retval == false) {
                        sugestoes.add(remedio1);
                    }
                }
            } else {
                //item esta guardando info de um remedio
                Log.i("---&>", "entrando no filtro sugestoes de remedio!!");
                //basta verificar se o remedio ja existe na listanegra do usuario.
                RemedioDTO remedioteste = checarRemedioNaListaNegra(item.getId());
                if ( remedioteste == null) {
                    //se o remedio nao existe na lista negra
                    sugestoes.add(retornarRemedioDTOPorId(item.getId()));
                }
            }

            if (sugestoes.size() >= n) {
                Log.i("---&>","Ja encontramos "+n+" remedios!");
                break;
            }
        }

        return sugestoes;
    }

}
