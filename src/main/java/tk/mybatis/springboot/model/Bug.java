package tk.mybatis.springboot.model;

import org.apache.ibatis.annotations.Options;

import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bug extends BaseEntity{
    @Transient
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   // private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //bug归属人
    private Integer bugBelongerId;
    //归属人名字
    private String belongerName;
    //归属人头像
    private String belongerImg;
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
    @Transient
    private String createTimeStr;
    @Transient
    private String userName;

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

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

    public String getCreateTimeStr() {
        if(getCreateTime()!=null){
            return sdf.format(getCreateTime());
        }
        return null;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBelongerName() {
        return belongerName;
    }

    public void setBelongerName(String belongerName) {
        this.belongerName = belongerName;
    }

    public String getBelongerImg() {
        return belongerImg;
    }

    public void setBelongerImg(String belongerImg) {
        this.belongerImg = belongerImg;
    }
}
