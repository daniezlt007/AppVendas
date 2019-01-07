package vendas.app.com.br.Activitys;

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
    private static final String TAG_SUCESSO = "sucesso";
    private static final String TAG_MENSAGEM = "mensagem";
    private ParametrosBean bean;
    private ParametrosDao dao;
    private static final String TAG_REQUEST = "tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bean = new ParametrosBean();
        dao = new ParametrosDao(getBaseContext());

        txtLogin = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

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
                Constantes.URL_REGISTRO,
                map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

        request.setTag(TAG_REQUEST);
        requestQueue.add(request);

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
