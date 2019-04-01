package teste.wf.apptest;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DetalhesOficina extends AppCompatActivity {

    private TextView id, nome, endereco, descricao, descicaocurta,
            latitude, longitude, avaliacaousuario, codigoassociacao, email, telefone1, telefone2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("json");
        List<String> oficina = bundle.getStringArrayList("json");

        //id = findViewById(R.id.id);
        nome = findViewById(R.id.nome);
        endereco = findViewById(R.id.endereco);
        descricao = findViewById(R.id.descricao);
        descicaocurta = findViewById(R.id.descricaoCurta);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        avaliacaousuario = findViewById(R.id.avaliacaousuario);
        codigoassociacao = findViewById(R.id.codigoassociacao);
        email = findViewById(R.id.email);
        telefone1 = findViewById(R.id.telefone1);
        telefone2 = findViewById(R.id.telefone2);

        //id.setText(oficina.get(0));
        nome.setText(oficina.get(1));
        descricao.setText(oficina.get(2));
        descicaocurta.setText(oficina.get(3));
        endereco.setText(oficina.get(4));
        latitude.setText(oficina.get(5));
        longitude.setText(oficina.get(6));
        avaliacaousuario.setText(oficina.get(8));
        codigoassociacao.setText(oficina.get(9));
        email.setText(oficina.get(10));
        telefone1.setText(oficina.get(11));
    }
}