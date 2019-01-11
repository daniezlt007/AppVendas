package vendas.app.com.br.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class Util extends Activity {

    public static boolean checarConexaoInternet(Context context){
        boolean conectado = false;

        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED){
                conectado = true;
            }

            if(connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED){
                conectado = true;
            }

        }catch (Exception e){
            conectado = false;
            Toast.makeText(context, "Não possui nenhuma conexão de internet.", Toast.LENGTH_LONG).show();
        }
        return conectado;
    }

    public static void msgError(String tag, String msg){
        Log.e(tag,msg);
    }

    public static void msgInfo(String tag, String msg){
        Log.i(tag,msg);
    }

    public static void msgDebug(String tag, String msg){
        Log.d(tag,msg);
    }

}
