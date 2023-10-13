package com.example.realfirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.realfirestore.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
ArrayList<ModelClass> datalist;
  FirebaseFirestore mydatabae;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        mydatabae=FirebaseFirestore.getInstance();
     datalist= new ArrayList<>();
     binding.button.setOnClickListener(view -> {


         String name=binding.editTextText.getText().toString();
         String email=binding.editTextText2.getText().toString();
         String id = mydatabae.collection("user").document().getId();

         ModelClass data= new ModelClass(name,email,id);
         mydatabae.collection("user").document(id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful()){
                     Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                 }
             }
         });

     });
        getAllData();
    }
    public void getAllData(){
        datalist=new ArrayList<>();
        mydatabae.collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                datalist.clear();
                List<ModelClass> data=value.toObjects(ModelClass.class);
                datalist.addAll(data);
                LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this);
                  AdaptorClass adapter= new AdaptorClass(MainActivity.this,datalist);
                  binding.Recyclerview.setAdapter(adapter);
                  binding.Recyclerview.setLayoutManager(layoutManager);
            }
        });

   }
}