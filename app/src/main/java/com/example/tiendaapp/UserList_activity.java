package com.example.tiendaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class UserList_activity extends AppCompatActivity {

    private final static int CODE_ADD = 100;
    private final static int CODE_DELETE = 110;
    private ArrayList<User> ListadoUsuarios;
    private RecyclerView rv_Users;
    private Button bt_Form;
    private  UserAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ListadoUsuarios = (ArrayList<User>) getIntent().getSerializableExtra("Users");

        rv_Users = findViewById(R.id.rv_user_list);
        bt_Form = findViewById(R.id.bt_Form_add_user);

        bt_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intention = new Intent(UserList_activity.this, Register_activity.class);
                startActivityForResult(Intention, CODE_ADD);
            }
        });

        miAdaptador = new UserAdapter(ListadoUsuarios);
        rv_Users.setAdapter(miAdaptador);

        rv_Users.setLayoutManager(new LinearLayoutManager(UserList_activity.this));
        rv_Users.setHasFixedSize(true);
    }

    @Override
    public void onBackPressed() {
        Intent intecion = new Intent();
        intecion.putExtra("Usuarios", ListadoUsuarios);
        setResult(RESULT_OK, intecion);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODE_ADD && resultCode==RESULT_OK){
            if(data != null){
                User Usuario = (User) data.getSerializableExtra("User");
                User LastUser = ListadoUsuarios.get(ListadoUsuarios.size()-1);
                ListadoUsuarios.add(Usuario);

                miAdaptador.setList(ListadoUsuarios);

                Toast.makeText(UserList_activity.this, Usuario.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}