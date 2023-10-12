package com.example.promul_chat_con_dos_activities_activity_result;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
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

    private static int ID_PETICION = 1;

    /*
     * 1º Hay que configurar en el manifest que MainActivity es la ventana padre y SecondActivity es
     * la ventana hijo, para poder establecer una conversación a través del result.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoChatMain = (TextView) findViewById(R.id.textoChatMain);
        mensajeAEnviarPorMain = (EditText) findViewById(R.id.mensajeAEnviarPorMain);
        enviarMain = (Button) findViewById(R.id.enviarMain);

        enviarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensajePorMain();
            }
        });
    }

    /*
     * 2º Creamos el método de enviar de main a second, donde iniciamos un ActivityForResult que
     * contiene el intent que se enviará de main a second y una id que servirá para identificar
     * el intent (ya que se pueden manejar varios intent a la vez).
     * Este proceso (el startActivityForResult()) abrirá una vía de comunicación a través del Result
     * en lugar de intercambiar directamente intent (como ocurría con startActivity()) entre main y second.
     */
    public void enviarMensajePorMain() {
        String mensaje = String.valueOf(mensajeAEnviarPorMain.getText());
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(ENVIADO_POR_MAIN, mensaje);
        startActivityForResult(intent, ID_PETICION);
    }


    /*
     * 5º Por último, para que main pueda recibir la información del intent del hijo second a través
     * del result, hay que sobrecargar el método onActivityResult, que será el encargado de recibir el
     * intent, además de una ID (requestCode) que nos ayuda a identificar el intent que vamos a recibir
     * (ya que se pueden recibir varios) y el RESULT_OK, que es un código emitido por la respuesta del
     * second que indica que ha ido bien.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ID_PETICION) {
                textoChatMain.setText(data.getStringExtra(SecondActivity.ENVIADO_POR_SECOND));
                textoChatMain.setVisibility(View.VISIBLE);
            }
        }
    }

    /*
     *   ARREGLAR DEPRECATED DE startActivityForResult()
     *   -  Aunque no se arregle, el código funciona, pero da el aviso de deprecated y en el futuro
     *      podría dejar de funcionar.
     *   -  Para arreglarlo:
     *
     *      1º Sustituimos el antiguo método public void enviarMensajePorMain() por el siguiente código:
     *
        *ActivityResultLauncher<Intent> enviarMensajePorMainLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == RESULT_OK) {
                Intent data = o.getData();
                textoChatMain.setText((String) data.getStringExtra(SecondActivity.ENVIADO_POR_SECOND));
            }
        }
        });
        *
        * 2º En lugar de llamar a startActivityForResult(intent, ID_PETICION) que está deprecated,
        * llamamos a enviarMensajePorMainLauncher.launch(intent)
        *
        * 3º Listo. Como podemos ver, ya no hay que usar el ID_PETICION ni el setVisibility.
     *
     *  */


}






































