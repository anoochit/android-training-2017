package net.redlinesoft.a12_sqlite_sugarorm;

import com.orm.SugarRecord;

/**
 * Created by xavier on 5/22/2017 AD.
 */

public class Note extends SugarRecord {

    String title,note;
    Long time;

    public Note() {
    }


    public Note(String title, String note, long time) {
        this.title = title;
        this.note = note;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
