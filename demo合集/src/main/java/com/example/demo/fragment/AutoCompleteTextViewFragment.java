package com.example.demo.fragment;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.example.demo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/11/28.
 */
@EFragment(R.layout.fragment_auto_complete)
public class AutoCompleteTextViewFragment extends Fragment {

    private  String[] stringArray;


    @ViewById(R.id.tv_auto_complete)
    AutoCompleteTextView autoCompleteTextView;
    @AfterViews
    void initAutoCompleteTextView(){
        stringArray = getResources().getStringArray(R.array.sms_temp);
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item,R.id.text,stringArray));
    }
}
