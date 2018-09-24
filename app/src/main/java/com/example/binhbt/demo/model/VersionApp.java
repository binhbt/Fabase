package com.example.binhbt.demo.model;

import android.graphics.drawable.Drawable;

import com.example.binhbt.demo.viewmodel.AppItemCircleView;
import com.example.binhbt.demo.viewmodel.OtherAppItemView;
import com.vn.fa.adapter.multipleviewtype.DataBinder;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;

/**
 * Created by binhbt on 3/7/2017.
 */
public class  VersionApp implements IViewBinder {
    public enum ViewType{
        CIRCLE,
        ROUND
    }
    private String id;
    private String type;
    private String title;
    private String pakage;
    private String url;
    private String versionName;
    private String versionCode;
    private String thumb;
    private ViewType viewType;
    private transient Drawable resId;

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public Drawable getResId() {
        return resId;
    }

    public void setResId(Drawable resId) {
        this.resId = resId;
    }

    @Override
    public DataBinder getViewBinder() {
        if (viewType == ViewType.CIRCLE){
            return  new AppItemCircleView(this);
        }
        return new OtherAppItemView(this);
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPakage() {
        return pakage;
    }

    public void setPakage(String pakage) {
        this.pakage = pakage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
