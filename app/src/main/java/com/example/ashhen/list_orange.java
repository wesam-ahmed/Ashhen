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

public class list_orange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orange);
        ArrayList<list_item> items=new ArrayList<list_item>();

        items.add(new list_item("معرفه الرقم","*119#"));
        items.add(new list_item("استعلام رصيد","*100#3#"));
        items.add(new list_item("خدمه عملاء","110"));
        items.add(new list_item("باقات النت","*100#4#"));
        items.add(new list_item("الغاء باقه النت","*100#4#"));
        items.add(new list_item(" تغير النظام","400"));
        items.add(new list_item("تحويل رصيد","#100#2#"));
        items.add(new list_item("كول تون","999"));
        items.add(new list_item("رسائل ضبط","#100#4#"));
        items.add(new list_item(" باقات المكالمات","*400#"));
        items.add(new list_item(" معرفه الاستهلاك","#100#"));

        list_orange.Customadpter adpter =new list_orange.Customadpter(items);
        ListView listView= findViewById(R.id.list_orange);
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
