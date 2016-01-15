package mpoo.ufrpe.projetoalergia.negocio.infra;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.ufrpe.projetoalergia.negocio.Sessao;

import static java.lang.Math.sqrt;

/**
 * Created by Allan on 01/11/2015.
 */
public class Util  {

    public static Sessao sessao = Sessao.getInstancia();

    public static void validarStringEspaco(String componente, String item)throws ProjetoAlergiaException{
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(componente);
        if(matcher.find()){
            String messagem = "O "+item+" não deve conter espaço";
            throw new ProjetoAlergiaException(messagem);
        }
    }

    public static void isCPF(String CPF) throws ProjetoAlergiaException{

        char dig10, dig11;
        int sm, i, r, num, peso;
        sm = 0;
        peso = 10;

        for (i=0; i<9; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1; }
        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else
            dig10 = (char)(r + 48);
        sm = 0;
        peso = 11;
        for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1; }
        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig11 = '0';
        else
            dig11 = (char)(r + 48);
        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
            return ;
        else {
            String menssagem = "CPF inválido";
            throw new ProjetoAlergiaException(menssagem);
        }
    }

    public static double afinidadeEuclidiana(int uid1, int uid2, float[][] matriz) {
        float retval = 0;

        int qtdCol = matriz[0].length;

        for (int r = 1; r <= qtdCol;r++) {
            retval = (float)Math.pow((matriz[uid1-1][r-1] - matriz[uid2-1][r-1]),2) + retval;
        }

        return (1 / (1 +sqrt(retval)));
    }

}

