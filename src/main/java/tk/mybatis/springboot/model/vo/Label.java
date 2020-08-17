package tk.mybatis.springboot.model.vo;

import tk.mybatis.springboot.model.BaseEntity;

public class Label extends BaseEntity {

    private String labelName;
    private Integer isTitle;


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getIsTitle() {
        return isTitle;
    }

    public void setIsTitle(Integer isTitle) {
        this.isTitle = isTitle;
    }
}
