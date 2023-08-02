package com.example.database_demo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.database_demo.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Integer> idList=new ArrayList();
    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> emailList=new ArrayList<>();


    Show_Recycler_Adapter adapter;


    //    int id;
    //    String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        MydataBase mydataBase=new MydataBase(MainActivity.this);


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.editName.getText().toString();
                String email=binding.editemail.getText().toString();
                mydataBase.addData(name,email);
                binding.editName.setText("");
                binding.editemail.setText("");
            }
        });


        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Show_Activity.class);
                startActivity(intent);
//                intent.putExtra("id",idList);
//                intent.putExtra("name",nameList);
//                intent.putExtra("email",emailList);
            }
        });
    }
}