package net.redlinesoft.a08_cardview;

/**
 * Created by xavier on 5/1/2017 AD.
 */

public class DataObject {

    String name;
    String age;
    int photoId;

    DataObject(String name, String age, int photoId) {
        this.name=name;
        this.age=age;
        this.photoId=photoId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getPhotoId() {
        return photoId;
    }


}
