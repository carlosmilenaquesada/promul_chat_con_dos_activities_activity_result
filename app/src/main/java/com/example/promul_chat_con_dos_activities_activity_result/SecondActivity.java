package com.example.promul_chat_con_dos_activities_activity_result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textoChatsecond;
    EditText mensajeAEnviarPorSecond;
    Button enviarSecond;
    public static String ENVIADO_POR_SECOND = "promul_chat_con_dos_activities_activity_result.SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textoChatsecond = (TextView) findViewById(R.id.textoChatSecond);
        mensajeAEnviarPorSecond = (EditText) findViewById(R.id.mensajeAEnviarPorSecond);
        enviarSecond = (Button) findViewById(R.id.enviarSecond);
        enviarSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensajePorSecond();
            }
        });

        /*
         * 3º Recibimos el intent como haríamos normalmente.
         */
        Intent intent = getIntent();
        textoChatsecond.setText(intent.getStringExtra(MainActivity.ENVIADO_POR_MAIN));
    }

    public void enviarMensajePorSecond() {
        String mensaje = String.valueOf(mensajeAEnviarPorSecond.getText());
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(ENVIADO_POR_SECOND, mensaje);

        /*
         * 4º En el proceso de responder por parte de second, en lugar de contestar con un starActivity,
         *  lo hacemos utilizando la vía del Result, enviando a través de éste un intent de respuesta.
         *  Hay que finalizar (finish) la ventana actual second para que se muestre la ventana padre main.
         */
        setResult(RESULT_OK, intent);
        finish();
    }
}