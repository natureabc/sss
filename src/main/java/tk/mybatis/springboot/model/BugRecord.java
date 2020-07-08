package tk.mybatis.springboot.model;

import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BugRecord {

    @Transient
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Integer id;
    //bugId
    private Integer bugId;
    //操作人id
    private Integer operationUserId;
    //操作人姓名
    private String operationUserName;
    //操作人头像
    private String operationUserHead;
    //操作类型（1：创建  2：指派 3 :确认4：解决 5：激活 6：关闭）
    private Integer operationType;
    //操作时间
    private Date operationTime;

    //指派对象的id
    private Integer bugBelongerId;

    //理由
    private String reason;

    @Transient
    private String operationTimeStr;
    @Transient
    private String bugBelongerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public Integer getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Integer operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getOperationUserHead() {
        return operationUserHead;
    }

    public void setOperationUserHead(String operationUserHead) {
        this.operationUserHead = operationUserHead;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getBugBelongerId() {
        return bugBelongerId;
    }

    public void setBugBelongerId(Integer bugBelongerId) {
        this.bugBelongerId = bugBelongerId;
    }

    public String getBugBelongerName() {
        return bugBelongerName;
    }

    public void setBugBelongerName(String bugBelongerName) {
        this.bugBelongerName = bugBelongerName;
    }

    public String getOperationTimeStr() {
        if(getOperationTime()!=null){
            return sdf.format(getOperationTime());
        }
        return null;
    }

    public void setOperationTimeStr(String operationTimeStr) {
        this.operationTimeStr = operationTimeStr;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
