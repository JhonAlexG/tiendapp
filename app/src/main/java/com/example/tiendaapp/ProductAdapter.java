package com.example.tiendaapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {
    private ArrayList<Producto> List;
    private OnItemClickListener OnItemClickListener;

    public ProductAdapter(ArrayList<Producto> List){
        this.List = List;
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener onItemClickListener) {
        OnItemClickListener = onItemClickListener;
    }

    public void setList(ArrayList<Producto> list) {
        this.List = list;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_Image;
        TextView tv_Name;
        TextView tv_Price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_Image = itemView.findViewById(R.id.iv_Image_Item);
            tv_Name = itemView.findViewById(R.id.tv_Name_Item);
            tv_Price = itemView.findViewById(R.id.tv_Price_Item);


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(View);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Producto Product = List.get(position);

        final ProductViewHolder Holder = (ProductViewHolder) holder;

        Holder.tv_Name.setText(Product.getName());
        Holder.tv_Price.setText(Product.getPrice()+"");
        Glide.with(holder.itemView.getContext()).load(Product.getUrlImage()).into(((ProductViewHolder) holder).iv_Image);

        Holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OnItemClickListener != null){
                    OnItemClickListener.OnItemClick(Product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public interface OnItemClickListener{
        void OnItemClick(Producto Product);
    }
}