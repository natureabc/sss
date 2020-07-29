package tk.mybatis.springboot.model.vo;

import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.springboot.model.Forum;
import tk.mybatis.springboot.model.ForumLabel;

import java.util.List;

public class ForumVo extends Forum {

    private List<ForumLabel> labelList;
    private String [] labelArray;
    private String preTitle;
    private String nextTitle;
    private Integer preId;
    private Integer nextId;
    private MultipartFile imgFile;

    public List<ForumLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<ForumLabel> labelList) {
        this.labelList = labelList;
    }


    public String getNextTitle() {
        return nextTitle;
    }

    public void setNextTitle(String nextTitle) {
        this.nextTitle = nextTitle;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public String getPreTitle() {
        return preTitle;
    }

    public void setPreTitle(String preTitle) {
        this.preTitle = preTitle;
    }

    public String[] getLabelArray() {
        return labelArray;
    }

    public void setLabelArray(String[] labelArray) {
        this.labelArray = labelArray;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
