package com.example.demo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.example.demo.bean.DaoMaster;
import com.example.demo.bean.DaoSession;
import com.example.demo.bean.DayList;
import com.example.demo.bean.DayListDao;
import com.example.demo.bean.LabelList;
import com.example.demo.bean.LabelListDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by crxc on 2016/11/29.
 */

public class DBManager {
    private final static String dbName = "label_list_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }


    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param labelList
     */
    public void insertLabelList(LabelList labelList) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LabelListDao labelListDao = daoSession.getLabelListDao();
        labelListDao.insert(labelList);
    }
    /**
     * 插入一条记录
     *
     * @param dayList
     */
    public void insertDayList(DayList dayList) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DayListDao dayListDao = daoSession.getDayListDao();
        dayListDao.insert(dayList);
    }

    /**
     * 插入用户集合
     *
     * @param labelLists
     */
    public void insertLabelListList(List<LabelList> labelLists) {
        if (labelLists == null || labelLists.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LabelListDao labelListDao = daoSession.getLabelListDao();
        labelListDao.insertInTx(labelLists);
    }

    /**
     * 删除一条记录
     *
     * @param labelList
     */
    public void deleteLabelList(LabelList labelList) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LabelListDao labelListDao = daoSession.getLabelListDao();
        labelListDao.delete(labelList);
    }

    /**
     * 更新一条记录
     *
     * @param labelList
     */
    public void updateLabelList(LabelList labelList) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LabelListDao labelListDao = daoSession.getLabelListDao();
        labelListDao.update(labelList);
    }

    /**
     * 查询用户列表
     */
    public List<LabelList> queryLabelListList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LabelListDao labelListDao = daoSession.getLabelListDao();
        QueryBuilder<LabelList> qb = labelListDao.queryBuilder();
        List<LabelList> list = qb.list();
        return list;
    } 
    /**
     * 查询用户列表
     */
    public List<DayList> queryDayListList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DayListDao dayListDao = daoSession.getDayListDao();
        QueryBuilder<DayList> qb = dayListDao.queryBuilder();
        List<DayList> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<LabelList> queryLabelListList(String label) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        LabelListDao labelListDao = daoSession.getLabelListDao();
        QueryBuilder<LabelList> qb = labelListDao.queryBuilder();
        qb.where(LabelListDao.Properties.Mlabel.gt(label)).orderAsc(LabelListDao.Properties.Mlabel);
        List<LabelList> list = qb.list();
        return list;
    }
}
