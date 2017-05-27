package net.redlinesoft.a12_sugarorm;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

/**
 * Created by xavier on 5/22/2017 AD.
 */

public class Note extends SugarRecord {
    @Unique
    Long time;
     String title;
     String note;


    public Note() {
    }

    public Note(Long time,String title, String note) {
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}