package com.example.database_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import com.example.database_demo.databinding.ActivityShowBinding;
import java.util.ArrayList;

public class Show_Activity extends AppCompatActivity {
    ActivityShowBinding binding;
    ArrayList<Integer> idList=new ArrayList();
    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> emailList=new ArrayList<>();

    Show_Recycler_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        showdata();



//        idList = getIntent().getIntegerArrayListExtra("id");
//        nameList = getIntent().getStringArrayListExtra("name");
//        emailList = getIntent().getStringArrayListExtra("email");
//        adapter = new Show_Recycler_Adapter(Show_Activity.this,idList,nameList,emailList);
//        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.showrecyclerview.setLayoutManager(manager);
//
//        binding.showrecyclerview.setAdapter(adapter);

    }

    private void showdata() {
        MydataBase mydataBase=new MydataBase(Show_Activity.this);
        Cursor cursor=mydataBase.showData();
        while (cursor.moveToNext())
        {
            idList.add(cursor.getInt(0));
            nameList.add(cursor.getString(1));
            emailList.add(cursor.getString(2));
        }
        Show_Recycler_Adapter adapter=new Show_Recycler_Adapter(Show_Activity.this, idList,nameList,emailList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.showrecyclerview.setLayoutManager(manager);
        binding.showrecyclerview.setAdapter(adapter);
    }
}