package com.example.database_demo;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.database_demo.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class Show_Recycler_Adapter extends RecyclerView.Adapter<Show_Recycler_Adapter.ShowrecycleHolder> {
    Show_Activity show_activity;
    ArrayList<Integer> idList;
    ArrayList<String> nameList;
    ArrayList<String> emailList;
    public Show_Recycler_Adapter(Show_Activity show_activity, ArrayList<Integer> idList, ArrayList<String> nameList, ArrayList<String> emailList) {
        this.show_activity = show_activity;
        this.idList = idList;
        this.nameList = nameList;
        this.emailList = emailList;

    }


    @NonNull
    @Override
    public Show_Recycler_Adapter.ShowrecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(show_activity).inflate(R.layout.activity_show_item,parent,false);
        ShowrecycleHolder holder = new ShowrecycleHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Show_Recycler_Adapter.ShowrecycleHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView1.setText(""+idList.get(position));
        holder.textView2.setText(""+nameList.get(position));
        holder.textView3.setText(""+emailList.get(position));


        //update mate

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MydataBase mydataBase=new MydataBase(show_activity);
//                mydataBase.updateData(idList.get(holder.getAdapterPosition()),nameList.get(holder.getAdapterPosition()),emailList.get(holder.getAdapterPosition()));


                PopupMenu popupMenu = new PopupMenu(show_activity,holder.button);
                show_activity.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.update){
                            Toast.makeText(show_activity, "Update", Toast.LENGTH_SHORT).show();


                            Dialog dialog = new Dialog(show_activity);
                            dialog.setContentView(R.layout.dialog_layout);
                            EditText txt1,txt2;
                            Button update;
                            txt1 = dialog.findViewById(R.id.dialog_name);
                            txt2 = dialog.findViewById(R.id.dialog_email);
                            update = dialog.findViewById(R.id.dialog_btn);

                            txt1.setText(""+nameList.get(position));
                            txt2.setText(""+emailList.get(position));

                            update.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MydataBase mydataBase = new MydataBase(show_activity.getApplicationContext());
                                    mydataBase.updateData(idList.get(position),txt1.getText().toString(),txt2.getText().toString());

                                    Intent intent = new Intent(show_activity,Show_Activity.class);
                                    show_activity.startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                        if (menuItem.getItemId()==R.id.delete){
                            Toast.makeText(show_activity, "Delete", Toast.LENGTH_SHORT).show();
                            MydataBase mydataBase = new MydataBase(show_activity);
                            mydataBase.deleteData(idList.get(holder.getAdapterPosition()));
                            idList.remove(position);
                            nameList.remove(position);
                            emailList.remove(position);
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return idList.size();//idList.toArray().length;
    }

    public class ShowrecycleHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;
        ImageButton button;
        public ShowrecycleHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.show_id_item);
            textView2 = itemView.findViewById(R.id.show_name_item);
            textView3 = itemView.findViewById(R.id.show_email_item);
            button = itemView.findViewById(R.id.more);
//                              detele mate
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    MydataBase mydataBase=new MydataBase(show_activity);
//                    mydataBase.deleteData(idList.get(getAdapterPosition()));
//                    idList.remove(itemView);
//                    nameList.remove(itemView);
//                    emailList.remove(itemView);
//                    notifyDataSetChanged();
//                    return true;
//                }
//            });
        }
    }
}