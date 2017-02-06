package project.sample.com.luke.homeworkimage.data;

/**
 * Created by itsm02 on 2017. 2. 6..
 */

public class ImgItem {

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
}
