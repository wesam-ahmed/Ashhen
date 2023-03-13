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

public class list_etsalat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etsalat);
        ArrayList<list_item> items=new ArrayList<list_item>();

        items.add(new list_item("معرفه الرقم","*947#"));
        items.add(new list_item("استعلام رصيد","*888#"));
        items.add(new list_item("خدمه عملاء","333"));
        items.add(new list_item("باقات النت","*566#"));
        items.add(new list_item("الغاء باقه النت","*566*2357#"));
        items.add(new list_item(" تغير النظام","*525#"));
        items.add(new list_item("تحويل رصيد","*557*NUMPER*AMOUNT#"));
        items.add(new list_item("كول تون","*15*5#"));
        items.add(new list_item("رسائل ضبط","*100#"));
        items.add(new list_item(" باقات المكالمات","*688#"));
        items.add(new list_item(" معرفه الاستهلاك","*838#"));

        Customadpter adpter =new Customadpter(items);
        ListView listView= findViewById(R.id.list_etsalat);
        listView.setAdapter(adpter);
    }

    class Customadpter extends BaseAdapter{
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
