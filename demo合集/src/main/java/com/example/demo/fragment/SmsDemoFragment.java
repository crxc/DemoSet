package com.example.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;

import com.example.demo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/12/1.
 */
@EFragment(R.layout.fragment_sms_demo)
public class SmsDemoFragment extends Fragment {
    private static final String TAG = "SmsDemoFragment";
    SelectContactAble select;
    InsertSmsTemp insertSmsTemp;
    @ViewById(R.id.edt_contact)
    EditText edt_phone;
    @ViewById(R.id.edt_sms_content)
    EditText edt_sms;


    @Override
    public void onAttach(Activity activity) {
        try {
            Activity activity1 = getActivity();
            select= (SelectContactAble) activity1;
            insertSmsTemp=(InsertSmsTemp) activity1;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+"must Implement SelectContactAble and InsertSmsTemp");
        }
        super.onAttach(activity);
    }

    public void setPhone(String phone) {
        edt_phone.setText(phone);
    }
    public void setSmsTemp(String smsTemp) {
        edt_sms.setText(smsTemp);
    }


    public interface SelectContactAble {
        void OpenSelectContactInterface();
    }
    public interface InsertSmsTemp{
        void openSmsTempInterface();
    }
    @Click(R.id.btn_select_contact)
    void openSelectContactInterface(){
        select.OpenSelectContactInterface();
    }
    @Click(R.id.btn_insert_template)
    void openInsertTemplateInterface(){
        insertSmsTemp.openSmsTempInterface();
    }
    @Click(R.id.btn_send)
    void sendSms(){
        String phone = edt_phone.getText().toString();
        String sms = edt_sms.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> strings = smsManager.divideMessage(sms);
        smsManager.sendMultipartTextMessage(phone,null,strings,null,null);
    }
}
