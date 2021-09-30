package com.example.tiendaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

import java.util.List;
import java.util.jar.Attributes;

public class Formulario_activity extends AppCompatActivity {
    private EditText et_Name;
    private EditText et_Price;
    private EditText et_Image;
    private EditText et_Description;
    private Button bt_Add;
    private TextView tv_Title;
    Producto Product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        et_Name = findViewById(R.id.et_Name_Form);
        et_Price = findViewById(R.id.et_Price_Form);
        et_Image = findViewById(R.id.et_Image_Form);
        et_Description = findViewById(R.id.et_Description_Form);
        bt_Add = findViewById(R.id.bt_Add_Form);
        tv_Title = findViewById(R.id.tv_Title_Form);

        Product = (Producto) getIntent().getSerializableExtra("Product");

        if(Product != null){
            tv_Title.setText(R.string.txt_Edit_Product);
            et_Name.setText(Product.getName());
            et_Price.setText(Product.getPrice()+"");
            et_Image.setText(Product.getUrlImage());
            et_Description.setText(Product.getDescription());
            bt_Add.setText(R.string.txt_Edit_Product);

            bt_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Name = et_Name.getText().toString();
                    double Price = Double.parseDouble(et_Price.getText().toString());
                    String Image = et_Image.getText().toString();
                    String Description = et_Description.getText().toString();

                    Product.setName(Name);
                    Product.setPrice(Price);
                    Product.setUrlImage(Image);
                    Product.setDescription(Description);

                    Intent Data = new Intent();
                    Data.putExtra("Product", Product);

                    setResult(RESULT_OK, Data);
                    finish();
                }
            });
        }else{
            bt_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Name = et_Name.getText().toString();
                    double Price = Double.parseDouble(et_Price.getText().toString());
                    String Image = et_Image.getText().toString();
                    String Description = et_Description.getText().toString();

                    Product = new Producto(Name, Price, Image);
                    if(!Description.equals("")){
                        Product.setDescription(Description);
                    }

                    Intent Data = new Intent();
                    Data.putExtra("Product", Product);

                    setResult(RESULT_OK, Data);
                    finish();
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_cerrar_sesión) {
            SharedPreferences preferences = getSharedPreferences(getString(R.string.txt_nombre_preferencia), MODE_PRIVATE);
            SharedPreferences.Editor editable = preferences.edit();
            editable.clear();
            editable.apply();

            Intent i = new Intent(Formulario_activity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}