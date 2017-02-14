package project.sample.com.luke.homeworkimage.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by itsm02 on 2017. 2. 6..
 */

public class ImgItem implements Parcelable {

    private String imgPath;
    private String imgTitleText;
    private String imgWebId;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgTitleText() {
        return imgTitleText;
    }

    public void setImgTitleText(String imgTitleText) {
        this.imgTitleText = imgTitleText;
    }

    public String getImgWebId() {
        return imgWebId;
    }

    public void setImgWebId(String imgWebId) {
        this.imgWebId = imgWebId;
    }

    @Override
    public String toString() {
        return "ImgItem{" +
                "imgPath='" + imgPath + '\'' +
                ", imgTitleText='" + imgTitleText + '\'' +
                ", imgWebId='" + imgWebId + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {

        out.writeString(imgPath);
        out.writeString(imgTitleText);
        out.writeString(imgWebId);

    }

    public static final Parcelable.Creator<ImgItem> CREATOR
            = new Parcelable.Creator<ImgItem>() {
        public ImgItem createFromParcel(Parcel in) {
            return new ImgItem(in);
        }

        public ImgItem[] newArray(int size) {
            return new ImgItem[size];
        }
    };

    public ImgItem() {
    }

    private ImgItem(Parcel in) {
        imgPath = in.readString();
        imgTitleText = in.readString();
        imgWebId = in.readString();
    }

}

