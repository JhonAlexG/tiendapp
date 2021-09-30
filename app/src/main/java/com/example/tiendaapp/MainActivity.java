package com.example.tiendaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_ADD = 100;
    private TextView tv_Title;
    private EditText et_Mail, et_Password;
    private Button bt_LogIn;
    private Button bt_Register;
    private ArrayList<User> ListadoUsuarios;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email = getSharedPreferences(getString(R.string.txt_nombre_preferencia),)

        tv_Title = findViewById(R.id.tv_title_start);
        et_Mail = findViewById(R.id.et_mail);
        et_Password = findViewById(R.id.et_password);
        bt_LogIn = findViewById(R.id.bt_login);
        bt_Register = findViewById(R.id.bt_user_list);

        ListadoUsuarios = new ArrayList<>();
        Cargar_Usuarios_Fake();

        preferences = getSharedPreferences(getString(R.string.txt_nombre_preferencia), MODE_PRIVATE);

        boolean logeado = preferences.getBoolean(getString(R.string.txt_preferencia_login), false);

        if (logeado){
            Intent miIntencion = new Intent(MainActivity.this, Listado_activity.class);
            startActivity(miIntencion);
            finish();
        }

        bt_LogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String eMail = et_Mail.getText().toString();
                String password = et_Password.getText().toString();
                for (User usuario : ListadoUsuarios){
                    if (eMail.equals(usuario.getuEmail()) && password.equals(usuario.getuPassword())){
                        Intent miIntencion = new Intent(MainActivity.this, Listado_activity.class);
                        miIntencion.putExtra("email", eMail);
                        startActivity(miIntencion);

                        SharedPreferences.Editor editable = preferences.edit();
                        editable.putBoolean(getString(R.string.txt_preferencia_login), true);
                        miIntencion.putExtra("email", eMail;
                        editable.apply();

                        finish();
                    }
                }
            }
        });

        bt_Register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent miIntencion = new Intent(MainActivity.this, UserList_activity .class);
                miIntencion.putExtra("Users", ListadoUsuarios);
                startActivityForResult(miIntencion, CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ADD && resultCode == RESULT_OK){
            if (data != null){
                ListadoUsuarios = (ArrayList<User>)data.getSerializableExtra("Usuarios");
            }
        }

    }

    private void Cargar_Usuarios_Fake(){
        User User1 = new User("Carlos", "carlos1011@gmail.com", "https://www.carreraspormontana.com/images/sites/2/2016/05/aguacate-960x640.jpg", "carlos123");
        ListadoUsuarios.add(User1);
        User User2 = new User("Lorena", "l521@outlook.com", "https://static1.abc.es/media/bienestar/2020/11/06/naranja-kOMF--620x349@abc.jpg", "lore123");
        ListadoUsuarios.add(User2);
    }
}
