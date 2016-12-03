package com.example.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by crxc on 2016/12/1.
 */
@EFragment(R.layout.fragment_contact)
public class ContactFragment extends Fragment {
    ReturnContact returnContact;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            returnContact= (ReturnContact) getActivity();
            initContactData();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+"must implement ReturnContact");
        }
    }

    private void initContactData() {

    }

    @ViewById(R.id.rv)
    RecyclerView rv;
    private static ArrayList<String> data=new ArrayList<>();
    static {
        ContentResolver resolver = App.getContext().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String contact = cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            while(phone.moveToNext())
            {
                String PhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //格式化手机号
                PhoneNumber = PhoneNumber.replace("-","");
                PhoneNumber = PhoneNumber.replace(" ","");
                data.add(PhoneNumber);
            }
        }
    }
    @AfterViews
    void initRv(){
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(new MyRecyclerAdapter(getActivity(), data.toArray(new String[data.size()])));
        rv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(final View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView text = (TextView) view.findViewById(R.id.text);
                        String phone = text.getText().toString();
                        returnContact.returnContactPhone(phone);
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });
    }

    public interface ReturnContact{
        void returnContactPhone(String phone);
    }
}
