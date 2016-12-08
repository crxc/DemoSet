package com.example.demo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by crxc on 2016/11/29.
 */
@Entity
public class DayList {
    @Id(autoincrement = true)
    private Long _id;
    private String day;
    @Keep
    public DayList( String day) {
        this.day = day;
    }
    @Generated(hash = 964809264)
    public DayList(Long _id, String day) {
        this._id = _id;
        this.day = day;
    }
    @Generated(hash = 117584427)
    public DayList() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getDay() {
        return this.day;
    }
    public void setDay(String day) {
        this.day = day;
    }
   
   
}
