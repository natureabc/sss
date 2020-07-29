package tk.mybatis.springboot.model;

import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forum extends BaseEntity{

    private String title;
    private String mainPic;
    private Integer isHot;
    private String keyWord;
    private Integer keywordId;
    private Integer browseNum;
    private String content;
    private Date createTime;
    private Integer isDel;

    @Transient
    private String createTimeStr;
    private Date updateTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }


    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTimeStr() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(getCreateTime()!=null){
            return sdf.format(getCreateTime());
        }
        return null;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
