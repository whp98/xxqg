package xyz.intellij.xxqgDemo;

public class Video {
    public Integer id;
    public String name;
    public String contents;
    public String len;
    public String url;

    public Video(Integer id, String name, String contents, String len, String url) {
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
