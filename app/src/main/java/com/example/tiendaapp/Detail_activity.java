package com.example.tiendaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_activity extends AppCompatActivity {

    private static final int CODE_EDIT = 12;
    private TextView tv_Product;
    private ImageView iv_Image;
    private TextView tv_Price;
    private TextView tv_Description;
    private Button bt_Delete;
    private Button bt_Edit;
    Producto Product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_Product = findViewById(R.id.tv_Title_Detail);
        iv_Image = findViewById(R.id.iv_Image_Detail);
        tv_Price = findViewById(R.id.tv_Price_Detail);
        tv_Description = findViewById(R.id.tv_Description_Detail);
        bt_Delete = findViewById(R.id.bt_eliminar_producto);
        bt_Edit = findViewById(R.id.bt_editar_producto);

        Product = (Producto) getIntent().getSerializableExtra("Product");

        Load_Data();

        bt_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Data = new Intent();
                Data.putExtra("Product", Product);
                setResult(RESULT_OK, Data);
                finish();
            }
        });

        bt_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intention = new Intent(Detail_activity.this, Formulario_activity.class);
                Intention.putExtra("Product", Product);
                startActivityForResult(Intention, CODE_EDIT);
            }
        });
    }

    private void Load_Data() {
        tv_Product.setText(Product.getName());
        tv_Price.setText(Product.getPrice()+"");
        tv_Description.setText(Product.getDescription());

        Glide.with(Detail_activity.this).load(Product.getUrlImage()).into(iv_Image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODE_EDIT && resultCode==RESULT_OK){
            if(data != null){
                Product = (Producto) data.getSerializableExtra("Product");
                Load_Data();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent Data = new Intent();
        Data.putExtra("Product", Product);
        Data.putExtra("EDIT", true);
        setResult(RESULT_OK, Data);

        super.onBackPressed();
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

            Intent i = new Intent(Detail_activity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
