package assignment.android.acadgild.reteiveimage;

import android.graphics.Bitmap;

/**
 * Created by DivyaVipin on 1/4/2017.
 */
public class Employee {
    private String name;
    private  int age;
    private Bitmap photo;
    public Employee(Bitmap photo,String name,int age) {
        this.name = name;
        this.age = age;
        this.photo = photo;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }




}
