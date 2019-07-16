package wangjie.com.newproject.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/5/17.
 */

public class HttpsData {
    @SerializedName("name")
    public String name;

    @SerializedName("sex")
    public String sex;

    @SerializedName("age")
    public String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "HttpsData{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
