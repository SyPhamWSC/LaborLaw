package com.dtsgroup.labourlaw.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JAppendix {
    public static ItemAppendix getAppendix(final Appendix appendix){
        final ItemAppendix itemAppendix = new ItemAppendix();
        itemAppendix.setId(appendix.getId());
        itemAppendix.setTitleEn(appendix.getTitleEn());
        itemAppendix.setTitleVi(appendix.getTitleVi());
        itemAppendix.getImg();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(appendix.getUrl());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    itemAppendix.setImg(byteArray);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (itemAppendix.getImg()==null){

        }

        return itemAppendix;

    }
}
