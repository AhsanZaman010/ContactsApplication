package com.ahsanzaman.contactsapp.model;

import android.view.View;

/**
 * Created by Ahsan Zaman on 24-07-2017.
 */

public class ContactDetail {

    String title;

    String description;

    int startImageResource;

    int endImageResource;

    View.OnClickListener itemOnClickListener;

    View.OnClickListener endIconOnClickListener;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartImageResource() {
        return startImageResource;
    }

    public void setStartImageResource(int startImageResource) {
        this.startImageResource = startImageResource;
    }

    public int getEndImageResource() {
        return endImageResource;
    }

    public void setEndImageResource(int endImageResource) {
        this.endImageResource = endImageResource;
    }

    public View.OnClickListener getItemOnClickListener() {
        return itemOnClickListener;
    }

    public void setItemOnClickListener(View.OnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public View.OnClickListener getEndIconOnClickListener() {
        return endIconOnClickListener;
    }

    public void setEndIconOnClickListener(View.OnClickListener endIconOnClickListener) {
        this.endIconOnClickListener = endIconOnClickListener;
    }
}
