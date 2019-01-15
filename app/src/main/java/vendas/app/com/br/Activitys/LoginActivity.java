package vendas.app.com.br.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vendas.app.com.br.Model.ParametrosBean;
import vendas.app.com.br.R;
import vendas.app.com.br.Dao.ParametrosDao;
import vendas.app.com.br.Util.Constantes;
import vendas.app.com.br.Util.Util;
import vendas.app.com.br.Volley.CustomJsonObjectRequest;

public class LoginActivity extends AppCompatActivity {

    private Map<String, String> map;
    private EditText txtLogin, txtSenha;
    private Button btnLogin;
    private RequestQueue requestQueue;
    private ParametrosBean bean;
    private ParametrosDao dao;
    private JSONArray jsonArray = null;

    private static final String TAG_REQUEST = "tag";
    private static final String TAG_LOGIN_ACTIVITY = "LoginActivity";

    private static final String TAG_SUCESSO = "sucesso";
    private static final String TAG_MENSAGEM = "mensagem";
    private static final String TAG_LOGIN_USUARIO_JSON_ARRAY = "LoginUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bean = new ParametrosBean();
        dao = new ParametrosDao(getBaseContext());
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        txtLogin = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = txtLogin.getText().toString();
                String senha = txtSenha.getText().toString();
                if (validarCampos(login, senha)) {
                    bean = dao.buscarParametros();
                    if (bean != null) {
                        if (login.trim().equals(bean.getmParamsLogin()) && senha.trim().equals(bean.getmParamsSenha())) {
                            //registrarNaWeb(login, senha);
                            validarUsuarioOffLine(login, senha);
                        }
                    } else {
                        if (Util.checarConexaoInternet(LoginActivity.this)) {
                            registrarNaWeb(login, senha);
                        } else {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Sem conexão com internet.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }

    private void registrarNaWeb(String login, String senha) {
        map = new HashMap<>();
        map.put("login", login);
        map.put("senha", senha);

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_BASE,
                map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int sucesso = (Integer)response.get(TAG_SUCESSO);
                            String mensagem = (String) response.get(TAG_MENSAGEM);
                            switch (sucesso){
                                case 1:
                                    jsonArray = response.getJSONArray(TAG_LOGIN_USUARIO_JSON_ARRAY);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        bean = new ParametrosBean();
                                        dao = new ParametrosDao(LoginActivity.this);
                                        bean.setmParamsUsuCodigo(jsonObject.getInt("id"));
                                        bean.setmParamsDescontoVendedor(jsonObject.getInt("desconto"));
                                        bean.setmParamsLogin(jsonObject.getString("login"));
                                        bean.setmParamsSenha(jsonObject.getString("senha"));
                                        bean.setmParamsEndIpServer(Constantes.URL_BASE);
                                        bean.setmParamsEndIpLocal("localhost");
                                        bean.setmParamsImportarCliente("S");
                                        bean.setmParamsEstoqueNegativo("N");
                                        dao.gravarParametros(bean);
                                    }
                                    Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
                                    telaPrincipal();
                                    break;
                                case 2:
                                    Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
                                    break;
                            }

                        } catch (JSONException e) {
                            Util.msgError(TAG_LOGIN_ACTIVITY, e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Util.msgError(TAG_LOGIN_ACTIVITY, error.getMessage());
                    }
                }

        );

        request.setTag(TAG_REQUEST);
        requestQueue.add(request);

    }

    private void validarUsuarioOffLine(String login, String senha){
        bean = dao.buscarParametros();
        if (login.trim().equals(bean.getmParamsLogin()) && senha.trim().equals(bean.getmParamsSenha())) {
            telaPrincipal();
            Toast.makeText(this, "Seja bem vindo.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Usuário e/ou senha incorretos", Toast.LENGTH_LONG).show();
        }
    }

    private void telaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean validarCampos(String login, String senha) {
        boolean status = true;
        if (login.equals("") || login == null) {
            txtLogin.setError("Campo login deve ser preenchido");
            status = false;
        } else if (senha.equals("") || senha == null) {
            txtSenha.setError("Campo senha deve ser preenchido");
            status = false;
        }
        return status;
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestQueue.cancelAll(TAG_REQUEST);
    }
}
