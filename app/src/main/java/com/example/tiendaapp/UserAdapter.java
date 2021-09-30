package com.example.tiendaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter{

    private ArrayList<User> uList;

    public UserAdapter(ArrayList<User> List){
        this.uList = List;
    }

    public void setList(ArrayList<User> list) {
        this.uList = list;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_Image;
        TextView tv_Name;
        TextView tv_Email;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_Image = itemView.findViewById(R.id.iv_item_uProfile);
            tv_Name = itemView.findViewById(R.id.tv_item_uName);
            tv_Email = itemView.findViewById(R.id.tv_item_uEmail);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserAdapter.UserViewHolder(View);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final User Usuario = uList.get(position);

        final UserAdapter.UserViewHolder Holder = (UserAdapter.UserViewHolder) holder;

        Holder.tv_Name.setText(Usuario.getuName());
        Holder.tv_Email.setText(Usuario.getuEmail()+"");
        Glide.with(holder.itemView.getContext()).load(Usuario.getuImage()).into(((UserAdapter.UserViewHolder) holder).iv_Image);

    }

    @Override
    public int getItemCount() {
        return uList.size();
    }

}
