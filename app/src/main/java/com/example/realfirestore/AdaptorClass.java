package com.example.realfirestore;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realfirestore.databinding.ItemsBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;


public class AdaptorClass extends RecyclerView.Adapter<AdaptorClass.ViewHolder> {
    Context context;

    ArrayList<ModelClass> datalist;

FirebaseFirestore db= FirebaseFirestore.getInstance();
    public AdaptorClass(Context context, ArrayList<ModelClass> datalist) {
        this.datalist = datalist;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false));
    }
// there we set data on items from the model class
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass obj= datalist.get(position);
        holder.binding.textView.setText(obj.name);
        holder.binding.textView3.setText(obj.email);


//      Delete code
           holder.binding.deletebtn.setOnClickListener(v -> {
               db.collection("user").document(datalist.get(position).getUserid()).delete();

           });

//           Update code
      holder.binding.updatebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Intent intent= new Intent(context, updateactivity.class)
                     .putExtra("key",datalist.get(position).getUserid());
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemsBinding binding ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemsBinding.bind(itemView);
        }
    }
}
