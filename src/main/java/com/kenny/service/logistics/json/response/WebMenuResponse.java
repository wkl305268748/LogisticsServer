package com.kenny.service.logistics.json.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WKL on 2016-12-20.
 */
public class WebMenuResponse {
    String url;
    String title;
    int alert;
    String icon;
    boolean is_hander;
    List<WebMenuResponse> treeview;

    public WebMenuResponse(String url, String title, WebMenuResponse ...webMenuResponses){
        this.url = url;
        this.title = title;
        this.alert = 0;
        this.icon = "fa fa-laptop";
        this.is_hander = false;
        this.treeview = new ArrayList<>();
        for(WebMenuResponse menuResponse : webMenuResponses){
            treeview.add(menuResponse);
        }
    }

    public WebMenuResponse(String url, String title, int alert,String icon,boolean is_hander,WebMenuResponse ...webMenuResponses){
        this.url = url;
        this.title = title;
        this.alert = alert;
        this.icon = icon;
        this.is_hander = is_hander;
        this.treeview = new ArrayList<>();
        for(WebMenuResponse menuResponse : webMenuResponses){
            treeview.add(menuResponse);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<WebMenuResponse> getTreeview() {
        return treeview;
    }

    public void setTreeview(List<WebMenuResponse> treeview) {
        this.treeview = treeview;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isIs_hander() {
        return is_hander;
    }

    public void setIs_hander(boolean is_hander) {
        this.is_hander = is_hander;
    }
}
