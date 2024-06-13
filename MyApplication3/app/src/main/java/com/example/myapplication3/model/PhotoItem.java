package com.example.myapplication3.model;

import android.provider.MediaStore;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = PhotoItem.PhotoTabName)
public class PhotoItem {
    public final static String PhotoTabName = "photo_items";
    @PrimaryKey
    private int _id;

    private String data;
    private String displayName;
    private int mediaType;
    private String mimeType;
    private long size;
    private long dateAdded;
    private long dateModified;
    private int parent;

    @Ignore
    public PhotoItem() {
    }

    public PhotoItem(int _id, String data, String displayName, int mediaType, String mimeType, long size, long dateAdded, long dateModified, int parent) {
        this._id = _id;
        this.data = data;
        this.displayName = displayName;
        this.mediaType = mediaType;
        this.mimeType = mimeType;
        this.size = size;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.parent = parent;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDateModified() {
        return dateModified;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

}
