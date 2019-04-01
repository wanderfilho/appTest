package teste.wf.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsultarOficina extends AppCompatActivity {

    private TextView nome1, telefone1;
    private TextView nome2, telefone2;
    private TableRow tr1, tr2;
    private RequestQueue queue;

    List<String> oficinasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        queue = Volley.newRequestQueue(this);

        tr1 = findViewById(R.id.a);
        tr2 = findViewById(R.id.b);

        nome1 = findViewById(R.id.c1r1);
        telefone1 = findViewById(R.id.c2r1);
        nome2 = findViewById(R.id.c1r2);
        telefone2 = findViewById(R.id.c2r2);

        jsonParse();

        tr1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                Intent intent = new Intent(ConsultarOficina.this, DetalhesOficina.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("json", (ArrayList<String>) oficinasList);
                intent.putExtra("json", bundle);
                startActivity(intent);
            }
        });

        tr2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                Intent intent = new Intent(ConsultarOficina.this, DetalhesOficina.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("json", (ArrayList<String>) oficinasList);
                intent.putExtra("json", bundle);
                startActivity(intent);
            }
        });

    }


    private void jsonParse() {
        String url = "http://app.hinovamobile.com.br/ProvaConhecimentoWebApi/Api/Oficina?codigoAssociacao=601";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("ListaOficinas");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject oficina = jsonArray.getJSONObject(i);

                                int Id = oficina.getInt("Id");
                                String Nome = oficina.getString("Nome");
                                String Descricao = oficina.getString("Descricao");
                                String DescricaoCurta = oficina.getString("DescricaoCurta");
                                String Endereco = oficina.getString("Endereco");
                                String Latitude = oficina.getString("Latitude");
                                String Longitude = oficina.getString("Longitude");
                                String Foto = oficina.getString("Foto");
                                int AvaliacaoUsuario = oficina.getInt("AvaliacaoUsuario");
                                int CodigoAssociacao = oficina.getInt("CodigoAssociacao");
                                String Email = oficina.getString("Email");
                                String Telefone1 = oficina.getString("Telefone1");
                                String Telefone2 = oficina.getString("Telefone2");
                                boolean Ativo = oficina.getBoolean("Ativo");

                                oficinasList.add(String.valueOf(Id));
                                oficinasList.add(Nome);
                                oficinasList.add(Descricao);
                                oficinasList.add(DescricaoCurta);
                                oficinasList.add(Endereco);
                                oficinasList.add(Latitude);
                                oficinasList.add(Longitude);
                                oficinasList.add(Foto);
                                oficinasList.add(String.valueOf(AvaliacaoUsuario));
                                oficinasList.add(String.valueOf(CodigoAssociacao));
                                oficinasList.add(Email);
                                oficinasList.add(Telefone1);
                                oficinasList.add(Telefone2);
                            }
                            nome1.setText(oficinasList.get(1));
                            telefone1.setText(oficinasList.get(11));
                            nome2.setText(oficinasList.get(14));
                            telefone2.setText(oficinasList.get(24));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public void showDetails(View view) {
        Intent intent = new Intent(ConsultarOficina.this, DetalhesOficina.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("json", (ArrayList<String>) oficinasList);
        intent.putExtra("json", bundle);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra("json", oficinasList.toString());
        startActivity(intent);
    }
}