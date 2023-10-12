package com.example.promul_chat_con_dos_activities_activity_result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textoChatMain;
    EditText mensajeAEnviarPorMain;
    Button enviarMain;

    public static String ENVIADO_POR_MAIN = "promul_chat_con_dos_activities_activity_result.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoChatMain = (TextView) findViewById(R.id.textoChatMain);
        mensajeAEnviarPorMain = (EditText) findViewById(R.id.mensajeAEnviarPorMain);
        enviarMain = (Button) findViewById(R.id.enviarMain);

    }

    public void enviarMensaje(View view){
        String mensaje = String.valueOf(mensajeAEnviarPorMain.getText());
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(ENVIADO_POR_MAIN, mensaje);
        startActivity(intent);

    }
}