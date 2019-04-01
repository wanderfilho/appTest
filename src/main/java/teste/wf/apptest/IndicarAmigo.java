package teste.wf.apptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class IndicarAmigo extends AppCompatActivity {

    private RequestQueue requestQueue;

    private EditText nomeAmigo, emailAmigo, telefoneAmigo, nomeAssociado, emailAssociado,
            telefoneAssociado, cpfAssociado, placaVeiculo, codigoAssociacao, dataCriacao;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicar);

        nomeAmigo = findViewById(R.id.nomeAmigo);
        emailAmigo = findViewById(R.id.emailAmigo);
        telefoneAmigo = findViewById(R.id.telefoneAmigo);
        nomeAssociado = findViewById(R.id.nomeAssociado);
        emailAssociado = findViewById(R.id.emailAssociado);
        telefoneAssociado = findViewById(R.id.telefoneAssociado);
        cpfAssociado = findViewById(R.id.cpfAssociado);
        placaVeiculo = findViewById(R.id.placaVeiculo);
        codigoAssociacao = findViewById(R.id.codigoAssociacao);
        dataCriacao = findViewById(R.id.dataCriacao);


        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "{" +
                        "\"CodigoAssociacao\"" + "\"" + codigoAssociacao.getText().toString() + "\"," +
                        "\"DataCriacao\"" + "\"" + dataCriacao.getText().toString() + "\"," +
                        "\"CpfAssociado\"" + "\"" + cpfAssociado.getText().toString() + "\"," +
                        "\"EmailAssociado\"" + "\"" + emailAssociado.getText().toString() + "\"," +
                        "\"NomeAssociado\"" + "\"" + nomeAssociado.getText().toString() + "\"," +
                        "\"TelefoneAssociado\"" + "\"" + telefoneAssociado.getText().toString() + "\"," +
                        "\"PlacaVeiculoAssociado\"" + "\"" + placaVeiculo.getText().toString() + "\"," +
                        "\"NomeAmigo\"" + "\"" + nomeAmigo.getText().toString() + "\"," +
                        "\"TelefoneAmigo\"" + "\"" + telefoneAmigo.getText().toString() + "\"," +
                        "\"EmailAmigo\"" + "\"" + emailAmigo.getText().toString() + "\"" +
                        "}";
                Submit(data);
            }
        });
    }

    private void Submit(String data) {
        final String saveData = data;
        String URL = "http://app.hinovamobile.com.br/ProvaConhecimentoWebApi/Api/Indicacao";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), object.toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(IndicarAmigo.this, "Server ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hideProgressDialog();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return saveData == null ? null : saveData.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
}