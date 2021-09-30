package com.example.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class Register_activity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etImage;
    private EditText etPassword;
    private Button btAdd;
    User Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_correo);
        etImage = findViewById(R.id.et_url_image);
        etPassword = findViewById(R.id.et_password_file);
        btAdd = findViewById(R.id.bt_registro);

        Usuario = (User) getIntent().getSerializableExtra("User");

        if (Usuario != null){
            etName.setText(Usuario.getuName());
            etEmail.setText(Usuario.getuEmail());
            etImage.setText(Usuario.getuImage());
            etPassword.setText(Usuario.getuPassword());
            btAdd.setText("Registrarse");

            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String image = etImage.getText().toString();
                    String password = etPassword.getText().toString();

                    Usuario.setuName(name);
                    Usuario.setuEmail(email);
                    Usuario.setuImage(image);
                    Usuario.setuPassword(password);

                    Intent Data = new Intent();
                    Data.putExtra("User", Usuario);

                    setResult(RESULT_OK, Data);
                    finish();
                }
            });
        } else {
            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String image = etImage.getText().toString();
                    String password = etPassword.getText().toString();

                    Usuario = new User(name, email, image, password);

                    Intent Data = new Intent();
                    Data.putExtra("User", Usuario);
                    setResult(RESULT_OK, Data);
                    finish();
                }
            });
        }
    }


}
