package com.example.myapplication3.common.Helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.myapplication3.common.app.MyApp;
import com.example.myapplication3.model.PhotoItem;

import java.util.ArrayList;
import java.util.List;

public class MediaHelper {
    public static List<PhotoItem> getMediaFiles(long dateAddedAfter) {
        List<PhotoItem> photoItems = new ArrayList<>();
        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.PARENT
        };
        ContentResolver contentResolver = MyApp.getmContext().getContentResolver();
        String selection = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO + ") AND "
                + MediaStore.Files.FileColumns.DATE_ADDED + "> ?";

        String[] selectionArgs = new String[]{String.valueOf(dateAddedAfter)};
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, null);
//
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID));
                String data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME));
                int mediaType = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE));
                String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE));
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE));
                long dateAdded = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED));
                long dateModified = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED));
                int parent = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.PARENT));
                PhotoItem photoItem = new PhotoItem(id, data, displayName, mediaType, mimeType, size, dateAdded, dateModified, parent);
                photoItems.add(photoItem);
            }
            cursor.close();
        }
        return photoItems;
    }
}
