package tk.mybatis.springboot.model.vo;


import java.util.Date;

public class BugVo {

    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //bug归属人
    private Integer bugBelongerId;
    //bug等级
    private Integer level;
    //bug状态(0:初始化 1:正常 2:激活 3：已解决 4：已关闭)
    private Integer status;
    //所属项目id
    private Integer projectId;
    //创建时间
    private Date createTime;
    //最后修改时间
    private Date lastUpdateTime;
    //创建人
    private String createBy;
    //最后修改人
    private String lastUpdateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBugBelongerId() {
        return bugBelongerId;
    }

    public void setBugBelongerId(Integer bugBelongerId) {
        this.bugBelongerId = bugBelongerId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

}
