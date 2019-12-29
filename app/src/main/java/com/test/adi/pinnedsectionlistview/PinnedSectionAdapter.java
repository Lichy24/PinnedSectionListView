package com.test.adi.pinnedsectionlistview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeMap;

public class PinnedSectionAdapter extends BaseExpandableListAdapter implements PinnedSectionListView.PinnedSection, AbsListView.OnScrollListener {
    private Context context;
    private ArrayList<String> titles,previous,next;
    private TreeMap<String,ArrayList<Product>> data;


    public PinnedSectionAdapter(Context context, ArrayList<String> titles, TreeMap<String, ArrayList<Product>> data) {
        this.context = context;
        this.titles = titles;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(titles.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return titles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(titles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater layoutInflater =(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group,null);
        }
        ((TextView)view.findViewById(R.id.title_list)).setText(titles.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view==null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_product, null);
        }
        Product curr = data.get(titles.get(i)).get(i1);
        ((TextView) view.findViewById(R.id.name)).setText(curr.getName());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    public void configurePinnedSection(View v, int position, int alpha) {
        TextView header = v.findViewById(R.id.section_title);
        v.setAlpha(alpha);
        final String title = titles.get(position);
        header.setText(title);
    }
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedSectionListView) {
            ((PinnedSectionListView) view).configureSectionTop(firstVisibleItem);
            ((PinnedSectionListView) view).configureSectionBottom(firstVisibleItem+visibleItemCount-1);
        }
    }
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }
}
