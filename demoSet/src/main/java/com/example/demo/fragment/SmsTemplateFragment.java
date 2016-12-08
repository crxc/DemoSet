package com.example.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.demo.App;
import com.example.demo.R;
import com.example.demo.adapter.MyRecyclerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/2.
 */
@EFragment(R.layout.fragment_sms_template)
public class SmsTemplateFragment extends Fragment {
    @ViewById(R.id.rv_sms_temp)
    RecyclerView recyclerView;
    private static String[] data;
    private SetSmsTemp setSmsTemp;

    public interface SetSmsTemp{
        void setSmsTemp(String s);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            setSmsTemp= (SetSmsTemp)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+"must Implement SetSmsTemp");
        }
    }

    @AfterViews
    void initRv(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyRecyclerAdapter(getActivity(),data));
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = ((TextView) v.findViewById(R.id.text)).getText().toString();
                        setSmsTemp.setSmsTemp(s);
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });
    }
    static {
        data= App.getContext().getResources().getStringArray(R.array.sms_temp);
    }
}
