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

        textoChatsecond = (TextView)  findViewById(R.id.textoChatSecond);
        mensajeAEnviarPorSecond = (EditText) findViewById(R.id.mensajeAEnviarPorSecond);
        enviarSecond = (Button) findViewById(R.id.enviarSecond);
    }

    public void enviarMensaje(View view){
        String mensaje = String.valueOf(mensajeAEnviarPorSecond.getText());
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(ENVIADO_POR_SECOND, mensaje);
        startActivity(intent);
    }
}