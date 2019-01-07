package vendas.app.com.br.ConfigDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vendas.app.com.br.Util.Constantes;

public class Db extends SQLiteOpenHelper {

    public Db(Context context) {
        super(context, Constantes.DB_NAME, null, Constantes.DB_VERSION);
    }

    private static String TABELA_CLIENTE = "CREATE TABLE cliente (cli_id INTEGER, " +
            "cli_nome TEXT(50), cli_fantasia TEXT(50)," +
            "cli_endereco TEXT(50), cli_bairro TEXT(50)," +
            "cli_cep TEXT(12), cli_contato TEXT(20), " +
            "cli_nascimento TEXT(12),cli_cpfcnpj TEXT(25)," +
            "cli_rginscriest TEXT(30), cli_email TEXT(70)," +
            "cli_enviado CHAR(1), cli_chave TEXT(100)," +
            "usu_codigo INTEGER,cid_codigo INTEGER)";

    private static String TABELA_PRODUTO = "CREATE TABLE produto(" +
            "prd_codigo INTEGER, prd_ean TEXT(13)," +
            "prd_descricao TEXT(50), prd_unidmed TEXT(10)," +
            "prd_custo DECIMAL(10,2), prd_preco DECIMAL(10,2)," +
            "prd_quantidade INTEGER, prd_categoria TEXT)";

    private static String TABELA_VENDAC = "CREATE TABLE vendac(" +
            "ven_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ven_chave TEXT, ven_datahora DATETIME," +
            "ven_precisaoentrega DATE, " +
            "ven_usu_codigo INTEGER,ven_usu_nome TEXT," +
            "ven_cli_codigo INTEGER, ven_cli_nome TEXT," +
            "ven_formapgto TEXT,ven_valor DECIMAL(10,2)," +
            "ven_valor_desconto DECIMAL(10,2),ven_pesototal DECIMAL(10,2)," +
            "ven_enviada CHAR(1), ven_latitude DOUBLE," +
            "ven_longitude DOUBLE)";

    private static String TABELA_VENDAD = "CREATE TABLE vendad(" +
            "vendac_chave TEXT," +
            "vendad_nro_item INTEGER," +
            "vendad_ean TEXT," +
            "vendad_prd_codigo INTEGER," +
            "vendad_prd_descricao TEXT," +
            "vendad_quantidade DECIMAL(10,2)," +
            "vendad_preco_venda DECIMAL(10,2)," +
            "vendad_total DECIMAL(10,2))";

    private static String TABELA_VENDAD_TEMP = "CREATE TABLE venda_temp(" +
            "vendad_ean TEXT," +
            "vendad_prd_codigo INTEGER," +
            "vendad_prd_descricao TEXT," +
            "vendad_quantidade DECIMAL(10,2)," +
            "vendad_preco_venda DECIMAL(10,2)," +
            "vendad_total DECIMAL(10,2))";

    private static String TABELA_CHEQUE = "CREATE TABLE cheque(" +
            "ch_codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ch_cli_codigo INTEGER," +
            "ch_numerocheque TEXT," +
            "ch_contato TEXT," +
            "ch_cpf_dono TEXT," +
            "ch_nomebanco TEXT, ch_vencimento DATE," +
            "ch_valorcheque DECIMAL(10,2), ch_terceiro CHAR(1)," +
            "vendac_chave TEXT, ch_enviado TEXT, ch_datacadastro DATE)";

    private static String TABELA_CONTAS_RECEBER = "CREATE TABLE contas_receber(" +
            "rec_codigo INTEGER," +
            "rec_numparcela INTEGER," +
            "rec_cli_codigo INTEGER," +
            "rec_cli_nome TEXT," +
            "vendac_chave TEXT," +
            "rec_datamovto DATE," +
            "rec_valor_receber DECIMAL(10,2)," +
            "rec_datavencimento DATE," +
            "rec_data_pagamento DATE," +
            "rec_recebido_com TEXT," +
            "rec_enviado CHAR(1)" +
            ")";

    private static String TABELA_CONFIG_PAGTO = "CREATE TABLE config_pagto(" +
            "config_codigo INTEGER, config_com_sem_entrada CHAR(1)," +
            "config_tipo_pagto TEXT, config_recebido_com TEXT," +
            "config_valor_recebido DECIMAL(10,2)," +
            "config_parcela INTEGER, vendac_chave TEXT)";

    private static String TABELA_HIST_PAGTO = "CREATE TABLE hist_pagto(" +
            "hist_codigo INTEGER, hist_numparcela INTEGER," +
            "hist_valor_real_parcela DECIMAL(10,2)," +
            "hist_valor_pago_no_dia DECIMAL(10,2)," +
            "hist_restante_a_pagar DECIMAL(10,2)," +
            "hist_data_pagto DATE, hits_nome_cliente TEXT," +
            "hist_como_pagou TEXT, vendac_chave TEXT," +
            "hist_enviado CHAR(1)" +
            ")";

    private static String TABELA_PARAMETROS = "CREATE TABLE parametros(" +
            "params_usu_codigo INTEGER," +
            "params_importar_cliente TEXT," + // 1-todos ou 2-apenas clientes do vendedor
            "params_end_ip_local TEXT," +
            "params_end_ip_server TEXT," +
            "params_estoque_negativo CHAR(1)," +
            "params_desconto_vendedor INTEGER," +
            "params_login TEXT," +
            "params_senha TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABELA_CHEQUE);
        db.execSQL(TABELA_CLIENTE);
        db.execSQL(TABELA_CONFIG_PAGTO);
        db.execSQL(TABELA_CONTAS_RECEBER);
        db.execSQL(TABELA_HIST_PAGTO);
        db.execSQL(TABELA_PARAMETROS);
        db.execSQL(TABELA_PRODUTO);
        db.execSQL(TABELA_VENDAC);
        db.execSQL(TABELA_VENDAD);
        db.execSQL(TABELA_VENDAD_TEMP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
