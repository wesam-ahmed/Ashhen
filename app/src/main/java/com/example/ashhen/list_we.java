package com.example.ashhen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class list_we extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_we);
        ArrayList<list_item> items=new ArrayList<list_item>();

        items.add(new list_item("معرفه الرقم","*688#"));
        items.add(new list_item("استعلام رصيد","*414#"));
        items.add(new list_item("خدمه عملاء","111"));
        items.add(new list_item("باقات النت","*999#"));
        items.add(new list_item("الغاء باقه النت","*999#"));
        items.add(new list_item(" تغير النظام","111"));
        items.add(new list_item("تحويل رصيد","*323*NUMPER*AMOUNT#"));
       // items.add(new list_item("كول تون","*15*5#"));
        items.add(new list_item("رسائل ضبط","111"));
        items.add(new list_item(" باقات المكالمات","*600*20/40#"));
       // items.add(new list_item(" معرفه الاستهلاك","*838#"));

        list_we.Customadpter adpter =new list_we.Customadpter(items);
        ListView listView= findViewById(R.id.list_we);
        listView.setAdapter(adpter);
    }
    class Customadpter extends BaseAdapter {
        ArrayList<list_item> items=new ArrayList<list_item>();

        Customadpter( ArrayList<list_item> items){
            this.items=items;
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i).name;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater=getLayoutInflater();
            View view1=inflater.inflate(R.layout.customlayout,null);
            TextView name=view1.findViewById(R.id.textView_name);
            TextView numper=view1.findViewById(R.id.textView_num);
            name.setText(items.get(i).name);
            numper.setText(""+items.get(i).num);
            return view1;
        }
    }
}
