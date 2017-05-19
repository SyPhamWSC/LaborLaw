package com.dtsgroup.labourlaw.parser;

import com.dtsgroup.labourlaw.model.JSonItemBookmark;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JBookmark {

    public static List<JSonItemBookmark> getBookmark(JSONArray jsonArray) throws JSONException {
        List<JSonItemBookmark> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInt("id");
            String chapter = jsonObject.getString("chapter");
            String name_vi = jsonObject.getString("sname_vi");
            String name_en = jsonObject.getString("sname_en");
            String description_vi = jsonObject.getString("description_vi");
            String description_en = jsonObject.getString("description_en");
            String title_vi = jsonObject.getString("title_vi");
            String title_en = jsonObject.getString("title_en");

            JSonItemBookmark bookmark = new JSonItemBookmark(id, chapter, name_vi, name_en, description_vi, description_en, title_en, title_vi);
            list.add(bookmark);
        }

        return list;
    }
}
