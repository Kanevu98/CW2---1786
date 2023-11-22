package com.example.apcw2.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.apcw2.Model.APModel;
import com.example.apcw2.R;

import java.util.ArrayList;



public class APAdapter extends RecyclerView.Adapter<APAdapter.AccountVH> {
    ArrayList<APModel> accountModels;  // List to hold AccountModel objects
    Context context;  // Context of the activity

    // Constructor for the adapter
    public APAdapter(ArrayList<APModel> accountModels, Context context){
        this.accountModels = accountModels;
        this.context = context;
    }

    // Creating a new ViewHolder
    @NonNull
    @Override
    public APAdapter.AccountVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.accountrow, parent, false);  // Inflate the layout for each item
        return new AccountVH(view);
    }

    // Binding data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull APAdapter.AccountVH holder, int position) {
        APModel o = accountModels.get(position);
        holder.name_txt.setText(String.valueOf(o.getName()));
        holder.date_txt.setText(String.valueOf(o.getDate()));
        holder.email_txt.setText(String.valueOf(o.getEmail()));
        holder.account_image.setImageBitmap(o.getAccountImage());
    }

    @Override
    public int getItemCount() {
        return accountModels.size();
    }
    class AccountVH extends RecyclerView.ViewHolder {
        TextView name_txt, date_txt, email_txt;
        ImageView account_image;
        CardView account_cardview;

        public AccountVH(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.name_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            email_txt = itemView.findViewById(R.id.email_txt);
            account_image = itemView.findViewById(R.id.account_image);
            account_cardview = itemView.findViewById(R.id.account_card_view);
        }
    }
}
