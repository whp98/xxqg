package xyz.intellij.xxqgDemo;



//数据类

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoItem {
    public void loadData(Context con){
        Resources resources = con.getResources();
        String[] names = resources.getStringArray(R.array.video_names);
        String[] contents = resources.getStringArray(R.array.video_contents);
        String[] len = resources.getStringArray(R.array.video_len);
        String[] url = resources.getStringArray(R.array.video_urls);
        for (int i=0;i<names.length;i++){
            addItem(new Star(i+1,names[i],contents[i],len[i],url[i]));
        }
    }
    //定义一个内部类
    public static class Star{
        public Integer id;
        public String name;
        public String contents;
        public String len;
        public String url;

        public Star(Integer id, String name, String contents,String len, String url) {
            this.id = id;
            this.name = name;
            this.contents = contents;
            this.len = len;
            this.url = url;
        }

        @Override
        public String toString() {
            return  name+"-"+len;
        }
    }

    public static List<Star> ITEMS = new ArrayList<Star>();

    public static Map<Integer, Star> ITEM_MAP = new HashMap<Integer, Star>();

    public static void addItem(Star star){
        ITEMS.add(star);
        ITEM_MAP.put(star.id, star);
    }
}
