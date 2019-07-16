package wangjie.com.newproject.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/23.
 */
public class NewsTopFocus implements Serializable{

    @SerializedName("cc-uri")
    String ccuri;

    @SerializedName("image")
    String image;

    @SerializedName("seq")
    String seq;

    @SerializedName("to-uri")
    String touri;

    @SerializedName("updateAt")
    String updateAtc;

    @SerializedName("vc-uri")
    String vcuri;

    public String getCcuri() {
        return ccuri;
    }

    public void setCcuri(String ccuri) {
        this.ccuri = ccuri;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTouri() {
        return touri;
    }

    public void setTouri(String touri) {
        this.touri = touri;
    }

    public String getUpdateAtc() {
        return updateAtc;
    }

    public void setUpdateAtc(String updateAtc) {
        this.updateAtc = updateAtc;
    }

    public String getVcuri() {
        return vcuri;
    }

    public void setVcuri(String vcuri) {
        this.vcuri = vcuri;
    }

    @Override
    public String toString() {
        return "NewsTopFocus{" +
                "ccuri='" + ccuri + '\'' +
                ", image='" + image + '\'' +
                ", seq='" + seq + '\'' +
                ", touri='" + touri + '\'' +
                ", updateAtc='" + updateAtc + '\'' +
                ", vcuri='" + vcuri + '\'' +
                '}';
    }
}
