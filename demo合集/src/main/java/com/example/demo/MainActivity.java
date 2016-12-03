package com.example.demo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.demo.adapter.MyExpandableListAdapter;
import com.example.demo.fragment.AnimationFragment_;
import com.example.demo.fragment.AutoCompleteTextViewFragment_;
import com.example.demo.fragment.ContactFragment;
import com.example.demo.fragment.ContactFragment_;
import com.example.demo.fragment.DrawingFragment_;
import com.example.demo.fragment.ImageDemoFragment_;
import com.example.demo.fragment.MultiThreadDownloadFragment_;
import com.example.demo.fragment.MusicPlayerFragment_;
import com.example.demo.fragment.SmsDemoFragment;
import com.example.demo.fragment.SmsDemoFragment_;
import com.example.demo.fragment.SmsTemplateFragment;
import com.example.demo.fragment.SmsTemplateFragment_;
import com.example.demo.utils.CloseUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@EActivity
public class MainActivity extends AppCompatActivity implements SmsDemoFragment.SelectContactAble
        ,ContactFragment.ReturnContact,SmsDemoFragment.InsertSmsTemp,SmsTemplateFragment.SetSmsTemp{
    ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentManager manager;
    private String smsFragmentTag ="sms";
    private String smsTempTag="smsTemp";
    static {
        initData();
    }

    static void initData() {
        App app = App.getContext();
        SharedPreferences config = app.getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirstUse = config.getBoolean("isFirstUse", true);
        if(isFirstUse){
            InputStream inputStream = app.getResources().openRawResource(R.raw.data_db);
            BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
            File labelList_db = app.getDatabasePath("label_list_db");
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(labelList_db));
                for(int temp;(temp=bufferedInputStream.read())!=-1;){
                    bufferedOutputStream.write(temp);
                }
                config.edit().putBoolean("isFirstUse",false).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                CloseUtil.close(bufferedInputStream);
                CloseUtil.close(bufferedOutputStream);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //    @ViewById(R.id.left_drawer)
//    RecyclerView rv;
    @ViewById(R.id.expanded_menu)
    ExpandableListView mExpandView;
    @ViewById(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @AfterViews
    void init(){
        initExpandableListView();
        initDrawerView();
        initActionBar();
        manager=getFragmentManager();
    }


    private void initExpandableListView() {
        final MyExpandableListAdapter expandableListAdapter = new MyExpandableListAdapter(this);
        mExpandView.setAdapter(expandableListAdapter);
        mExpandView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                selectedItem(groupPosition,childPosition);
                setTitle(expandableListAdapter.getTitlt(groupPosition,childPosition));
                return true;
            }
        });
    }

    private void selectedItem(int groupPosition, int childPosition) {
        mDrawerLayout.closeDrawer(mExpandView);
        switch (groupPosition){
            case 0:
                getFragment(new MultiThreadDownloadFragment_());
                break;
            case 1:
                getFragment(new SmsDemoFragment_(), smsFragmentTag);
                break;
            case 2:
                selected13ChildItem(childPosition);
                break;
            case 3:
                selected14ChildItem(childPosition);
                break;
            case 4:
                selected15ChildItem(childPosition);
                break;
            case 5:
                selected17ChildItem(childPosition);
                break;
            case 6:
                selected18ChildItem(childPosition);
                break;
        }
    }

    private void selected18ChildItem(int childPosition) {
        switch (childPosition) {
            case 0:
                getFragment(new AnimationFragment_());
                break;
            case 1:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                getFragment(new AutoCompleteTextViewFragment_());
                break;
            case 3:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void selected17ChildItem(int childPosition) {
        switch (childPosition) {
            case 0:
                getFragment(new ImageDemoFragment_());
                break;
            case 1:

                break;
            case 2:
                getFragment(new DrawingFragment_());
                break;
            case 3:
                getFragment(new MusicPlayerFragment_());
                break;
            case 4:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void selected15ChildItem(int childPosition) {
        switch (childPosition) {
            case 0:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void selected14ChildItem(int childPosition) {
        switch (childPosition) {
            case 0:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void selected13ChildItem(int childPosition) {
        switch (childPosition){
            case 0:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "demo尚未添加", Toast.LENGTH_SHORT).show();
                break;

        }
    }



    private void initActionBar() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }



    private<T extends Fragment>  void getFragment(T fragment) {
        Fragment fragment1 = manager.findFragmentById(R.id.frame_main);
        FragmentTransaction transaction = manager.beginTransaction();
        if(fragment1==null){
            transaction.replace(R.id.frame_main,fragment)
                    .commit();
        }else {
            transaction.replace(R.id.frame_main,fragment)
                    .remove(fragment1).commit();
        }
    }
    private<T extends Fragment>  void getFragment(T fragment,String tag) {
        Fragment fragment1 = manager.findFragmentById(R.id.frame_main);
        FragmentTransaction transaction = manager.beginTransaction();
        if(fragment1==null){
            transaction.replace(R.id.frame_main,fragment,tag)
                    .commit();
        }else {
            transaction.replace(R.id.frame_main,fragment,tag)
                    .remove(fragment1).commit();
        }

    }

    private void initDrawerView() {
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout, R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                boolean open = mDrawerLayout.isDrawerOpen(GravityCompat.START);
                if(open) {
                    mDrawerLayout.closeDrawer(mExpandView);
                }else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            ContentResolver resolver = getContentResolver();
            Uri uri = data.getData();
            Cursor cursor = resolver.query(uri, null, null, null, null);
            if(cursor==null){
                Toast.makeText(this, "没有查询到联系人", Toast.LENGTH_SHORT).show();
                return;
            }
            cursor.moveToFirst();
//            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phoneCursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    ,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null);
            String userNumber = null;
            if(phoneCursor==null){
                Toast.makeText(this, "没有查询到电话", Toast.LENGTH_SHORT).show();
                return;
            }
            while (phoneCursor.moveToNext()){
                userNumber=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            cursor.close();
            phoneCursor.close();
            returnContactPhone(userNumber);
        }
    }

    @Override
    public void OpenSelectContactInterface() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,0);
    }

    @Override
    public void returnContactPhone(String phone) {
        SmsDemoFragment_ smsFragment = (SmsDemoFragment_) manager.findFragmentByTag(smsFragmentTag);
        smsFragment.setPhone(phone);
    }


    @Override
    public void openSmsTempInterface() {
        manager.beginTransaction().hide(manager.findFragmentByTag(smsFragmentTag))
                .add(R.id.frame_main,new SmsTemplateFragment_(),smsTempTag).commit();
    }

    @Override
    public void setSmsTemp(String s) {
        SmsDemoFragment_ smsFragment = (SmsDemoFragment_) manager.findFragmentByTag(smsFragmentTag);
        smsFragment.setSmsTemp(s);
        manager.beginTransaction().remove(manager.findFragmentByTag(smsTempTag))
                .show(smsFragment).commit();
    }
}
