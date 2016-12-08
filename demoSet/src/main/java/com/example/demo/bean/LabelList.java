package com.example.demo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by crxc on 2016/11/29.
 */
@Entity
public class LabelList {
    @Id(autoincrement = true)
    private Long _id;
    private String mlabel;
    private int num;
    private String mItem;
    @Keep
    public LabelList( String mlabel, int num, String mItem) {
        this.mlabel = mlabel;
        this.num = num;
        this.mItem = mItem;
    }
    @Generated(hash = 2099013427)
    public LabelList(Long _id, String mlabel, int num, String mItem) {
        this._id = _id;
        this.mlabel = mlabel;
        this.num = num;
        this.mItem = mItem;
    }
    @Generated(hash = 254135054)
    public LabelList() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getMlabel() {
        return this.mlabel;
    }
    public void setMlabel(String mlabel) {
        this.mlabel = mlabel;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getMItem() {
        return this.mItem;
    }
    public void setMItem(String mItem) {
        this.mItem = mItem;
    }



}
