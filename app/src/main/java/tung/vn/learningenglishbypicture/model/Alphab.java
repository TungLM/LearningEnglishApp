package tung.vn.learningenglishbypicture.model;

/**
 * Created by seven64 on 6/8/2016.
 */
public class Alphab {

    private String name;
    private String nameVn;

    public String getmAudio() {
        return mAudio;
    }

    private String mAudio;

    public int getColor() {
        return color;
    }

    private int color;

    public String getNameEL() {
        return nameEL;
    }

    private String nameEL;

    public String getNameEl() {
        return nameEl;
    }

    public void setNameEl(String nameEl) {
        this.nameEl = nameEl;
    }

    private String nameEl;
    private int thumbnail;

    public Alphab(String name, String nameEl, int thumbnail, int color, String nameVn, String url) {
        this.name = name;
        this.nameVn = nameVn;
        this.thumbnail = thumbnail;
        this.color = color;
        this.nameEL = nameEl;
        this.mAudio = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
