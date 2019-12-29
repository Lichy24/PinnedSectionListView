package com.test.adi.pinnedsectionlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private LinearLayout pinnedLinearLayout;
    private PinnedSectionListView pinnedSectionListView;
    private PinnedSectionAdapter pinnedSectionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> titles= new ArrayList();
        ArrayList<Product> products;
        TreeMap<String,ArrayList<Product>> data = new TreeMap();
        for (int i = 0; i < 7; i++) {
            titles.add("category"+i);
            products = new ArrayList<>();
            for (int j = 0; j<20; j++) {
                products.add(new Product("item"+j));
            }
            data.put("category"+i,products);
        }
        pinnedLinearLayout = findViewById(R.id.root);
        pinnedSectionAdapter = new PinnedSectionAdapter(this,titles,data);
        pinnedSectionListView = findViewById(R.id.list);
        pinnedSectionListView.setAdapter(pinnedSectionAdapter);
        pinnedSectionListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Product product = (Product) expandableListView.getExpandableListAdapter().getChild(i,i1);
                Log.i("Product Clicked", "onChildClick: "+product.getName());
                return true;
            }
        });
        pinnedSectionListView.setGroupIndicator(null);
        pinnedSectionListView.setPinnedSections(R.layout.small_section2);
        pinnedSectionListView.setOnScrollListener((AbsListView.OnScrollListener) pinnedSectionAdapter);
        pinnedSectionListView.setDividerHeight(0);
        for (int i = 0; i < 7; i++) {
            pinnedSectionListView.expandGroup(i);
        }
    }
}
