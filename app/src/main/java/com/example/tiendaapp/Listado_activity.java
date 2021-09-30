package com.example.tiendaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado_activity extends AppCompatActivity {

    private final static int CODE_ADD = 100;
    private final static int CODE_DELETE = 110;
    private ArrayList<Producto> ListadoProductos;
    private RecyclerView rv_Products;
    private Button bt_Form;
    private  ProductAdapter miAdaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        String Email = getIntent().getStringExtra("Email");

        setTitle(Email);

        rv_Products = findViewById(R.id.rv__List);
        bt_Form = findViewById(R.id.bt_Form_add);

        bt_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intention = new Intent(Listado_activity.this, Formulario_activity.class);
                startActivityForResult(Intention, CODE_ADD);
            }
        });

        ListadoProductos = new ArrayList<>();
        Cargar_Productos_Fake();

        miAdaptador = new ProductAdapter(ListadoProductos);
        rv_Products.setAdapter(miAdaptador);

        rv_Products.setLayoutManager(new LinearLayoutManager(Listado_activity.this));
        rv_Products.setHasFixedSize(true);

        miAdaptador.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Producto Product) {
                Intent Intention = new Intent(Listado_activity.this, Detail_activity.class);
                Intention.putExtra("Product", Product);
                startActivityForResult(Intention, CODE_DELETE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODE_ADD && resultCode==RESULT_OK){
            if(data != null){
                Producto Product = (Producto) data.getSerializableExtra("Product");
                Producto LastProduct = ListadoProductos.get(ListadoProductos.size()-1);
                int ID = LastProduct.getId()+1;
                Product.setId(ID);
                ListadoProductos.add(Product);

                miAdaptador.setList(ListadoProductos);

                Toast.makeText(Listado_activity.this, Product.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==CODE_DELETE && resultCode==RESULT_OK){
            if(data != null){
                Producto Product = (Producto) data.getSerializableExtra("Product");
                Boolean EDIT = data.getBooleanExtra("EDIT", false);

                for(Producto Element: ListadoProductos){
                    Log.d("ELIMINAR", "Lista: "+Element.getId()+" Eliminar: " +Product.getId());
                    if(Element.getId()==Product.getId()){
                        int Position = ListadoProductos.indexOf(Element);
                        if(EDIT){
                            ListadoProductos.set(Position, Product);
                        }else{
                            ListadoProductos.remove(Element);
                        }
                        break;
                    }
                }
                miAdaptador.setList(ListadoProductos);
            }
        }
    }

    private void Cargar_Productos_Fake(){
        Producto Pro1 = new Producto("Memoria USB", 70000, "https://compucentro.co/wp-content/uploads/USB-32GB.jpeg");
        Pro1.setId(1);
        ListadoProductos.add(Pro1);

        Producto Pro2 = new Producto("Disco Duro", 120000, "https://shop.westerndigital.com/content/dam/store/en-us/assets/products/internal-storage/wd-blue-3d-nand-sata-ssd/gallery/m2/wd-blue-3d-nand-sata-ssd-m2-2280-500GB.png");
        Pro2.setDescription("Disco Duro SSD 500GB");
        Pro2.setId(2);
        ListadoProductos.add(Pro2);

        Producto Pro3 = new Producto("Mouse", 50000, "https://www.sincable.mx/wp-content/uploads/2020/04/0-Raton-gamer-Dario-Lo-Presti-61562952_m.jpg");
        Pro3.setId(3);
        ListadoProductos.add(Pro3);

        Producto Pro4 = new Producto("Teclado", 80000, "https://compucentro.co/wp-content/uploads/TECLADO-GAMER-TRUST-AZOR.jpeg");
        Pro4.setId(4);
        ListadoProductos.add(Pro4);

        Producto Pro5 = new Producto("Pantalla", 400000, "https://icdn.dtcn.com/image/digitaltrends_es/asus-rog-swift-pg35vq-1-500x500.jpg");
        Pro5.setId(5);
        ListadoProductos.add(Pro5);
    }
}
