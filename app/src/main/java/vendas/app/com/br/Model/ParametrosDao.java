package vendas.app.com.br.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class ParametrosDao {

    private static String PARAMS_USU_CODIGO = "params_usu_codigo";
    private static String PARAMS_IMPORTAR_CLIENTE = "params_importar_cliente";
    private static String PARAMS_END_IP_LOCAL = "params_end_ip_local";
    private static String PARAMS_END_IP_SERVER = "params_end_ip_server";
    private static String PARAMS_ESTOQUE_NEGATIVO = "params_estoque_negativo";
    private static String PARAMS_DESCONTO_VENDEDOR = "params_desconto_vendedor";

    private final static String TAG_DEBUG = "ParametrosDao";
    private Context context;
    private String sql;
    private boolean gravacao;

    public ParametrosDao(Context context) {
        this.context = context;
    }

    public boolean gravarParametros(ParametrosBean paramsBean){
        SQLiteDatabase db = new Db(context).getWritableDatabase();
        gravacao = false;
        sql = "insert into parametros (" +
                "params_usu_codigo,params_usu_codigo," +
                "params_importar_cliente," +
                "params_end_ip_local," +
                "params_end_ip_server," +
                "params_estoque_negativo," +
                "params_desconto_vendedor) values (?,?,?,?,?,?)";

        SQLiteStatement stm = db.compileStatement(sql);
        try {
            stm.bindLong(1, paramsBean.getmParamsUsuCodigo());
            stm.bindString(2, paramsBean.getmParamsImportarCliente());
            stm.bindString(3, paramsBean.getmParamsEndIpLocal());
            stm.bindString(4, paramsBean.getmParamsEndIpServer());
            stm.bindString(5, paramsBean.getmParamsEstoqueNegativo());
            stm.bindLong(6, paramsBean.getmParamsDescontoVendedor());

            if(stm.executeInsert() > 0){
                gravacao = true;
            }

        }catch (SQLException ex){
            gravacao = false;
            Log.d(TAG_DEBUG, "gravarParametros: " + ex.getMessage());
        }finally {
            db.close();
            stm.close();
        }

        return gravacao;
    }

    public ParametrosBean buscarParametros(){
        SQLiteDatabase db = new Db(context).getReadableDatabase();
        ParametrosBean parms = null;
        sql = "select * from parametros";
        try{
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst()){
                parms = new ParametrosBean();
                parms.setmParamsUsuCodigo(cursor.getInt(cursor.getColumnIndex(PARAMS_USU_CODIGO)));
                parms.setmParamsImportarCliente(cursor.getString(cursor.getColumnIndex(PARAMS_IMPORTAR_CLIENTE)));
                parms.setmParamsImportarCliente(cursor.getString(cursor.getColumnIndex(PARAMS_END_IP_LOCAL)));
                parms.setmParamsImportarCliente(cursor.getString(cursor.getColumnIndex(PARAMS_END_IP_SERVER)));
                parms.setmParamsImportarCliente(cursor.getString(cursor.getColumnIndex(PARAMS_ESTOQUE_NEGATIVO)));
                parms.setmParamsImportarCliente(cursor.getString(cursor.getColumnIndex(PARAMS_DESCONTO_VENDEDOR)));
            }
        }catch (SQLException ex){
            Log.d(TAG_DEBUG, "buscaParametros: " + ex.getMessage());
        }finally {
            db.close();
        }
        return parms;
    }

    public void atualizarParametros(ParametrosBean params){
        SQLiteDatabase db = new Db(context).getWritableDatabase();
        sql = "update parametros set params_usu_codigo=?," +
                "params_usu_codigo=?,params_importar_cliente=?," +
                "params_end_ip_local=?,params_end_ip_server=?," +
                "params_estoque_negativo=?, params_desconto_vendedor=?";
        SQLiteStatement stm = db.compileStatement(sql);
        try {
            stm.bindLong(1, params.getmParamsUsuCodigo());
            stm.bindString(2, params.getmParamsImportarCliente());
            stm.bindString(3, params.getmParamsEndIpLocal());
            stm.bindString(4, params.getmParamsEndIpServer());
            stm.bindString(5, params.getmParamsEstoqueNegativo());
            stm.bindLong(6, params.getmParamsDescontoVendedor());

            stm.executeUpdateDelete();
            stm.clearBindings();

        }catch (SQLException ex){
            Log.d(TAG_DEBUG, "atualizarParametros: " + ex.getMessage());
        }finally {
            db.close();
            stm.close();
        }
    }

}
