package com.zyl.recycle.demo.model;

import java.io.Serializable;

/**
 * Created by zhangyonglu on 2017/2/22.
 */

public class AgTechnologInfor implements Serializable {
//    "source": "西峰区瓜菜办",
//            "category": "实用技术",
//            "type": "无公害生产",
//            "intime": "2017/2/21 0:00:00",
//            "contentId": 222,
//            "releaseTime": "2011/12/30 0:00:00"
    //    "source": "西峰区瓜菜办",

    private String source;
    private String category;
    private String type;
    private String intime;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private int  contentId;
//    private int  id;
    private String id;
    private String releaseTime;
    private String title;
    private String content;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
