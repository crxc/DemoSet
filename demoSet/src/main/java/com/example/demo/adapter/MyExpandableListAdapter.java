package com.example.demo.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.bean.DayList;
import com.example.demo.bean.LabelList;
import com.example.demo.utils.DBManager;
import com.example.demo.utils.SuperHashMap;

import java.util.HashMap;
import java.util.List;

/**
 * Created by crxc on 2016/11/29.
 */

public class MyExpandableListAdapter implements ExpandableListAdapter{
    private Context mContext;
    private List<DayList> dayLists;
    private DBManager dbManager;
    private HashMap<String,List<LabelList>> dataList;
    private SuperHashMap<String,LabelList> shm;
    public MyExpandableListAdapter(Context context) {
        mContext=context;
        dbManager = DBManager.getInstance(mContext);
        dayLists = dbManager.queryDayListList();
        initDataList();
        dataList=shm.getMap();
    }

    private void initDataList() {
        List<LabelList> labelLists = dbManager.queryLabelListList();
        shm=new SuperHashMap<>();
        for (LabelList label: labelLists) {
            String mlabel = label.getMlabel();
            shm.put(mlabel,label);
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {

        return shm.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<LabelList> labelLists = dataList.get(dayLists.get(groupPosition).getDay());
        return labelLists.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        List<LabelList> labelLists = dbManager.queryLabelListList(dayLists.get(groupPosition).getDay());
        return labelLists;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  dbManager.queryLabelListList(dayLists.get(groupPosition).getDay()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_group, parent, false);
            groupViewHolder = new MyViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (MyViewHolder) convertView.getTag();
        }
        TextView textView = (TextView) groupViewHolder.getViewByID(R.id.label_expand_group);
        textView.setText(dayLists.get(groupPosition).getDay());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_child, parent, false);
            groupViewHolder = new MyViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (MyViewHolder) convertView.getTag();
        }
        TextView textView = (TextView) groupViewHolder.getViewByID(R.id.label_expand_child);
        String day = dayLists.get(groupPosition).getDay();
        List<LabelList> labelLists = dataList.get(day);
        String mItem = labelLists.get(childPosition).getMItem();
        textView.setText(mItem);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    public String getTitlt(int groupPosition, int childPosition) {
        String day = dayLists.get(groupPosition).getDay();
        List<LabelList> labelLists = dataList.get(day);
        String mItem = labelLists.get(childPosition).getMItem();
        return mItem;
    }
}
