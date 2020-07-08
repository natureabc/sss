package tk.mybatis.springboot.common;

public class BugConstant {

    /**
     * bug操作记录的常量
     */
    //新增
    public static final Integer OPERATION_ADD=1;
    //指派
    public static final Integer OPERATION_ASSIGN=2;
    //确认
    public static final Integer OPERATION_CONFIRM=3;
    //解决
    public static final Integer OPERATION_SOLVE=4;
    //激活
    public static final Integer OPERATION_ACTIVATION=5;
    //关闭
    public static final Integer OPERATION_CLOSE=6;

    /**
     * bug实体类状态
     */
    //未解决
    public static final Integer BUG_UNSLOVED=1;
    //已确认
    public static final Integer BUG_CONFIRM=2;
    //已解决
    public static final Integer BUG_SLOVED=3;
    //已关闭
    public static final Integer BUG_CLOSE=4;
}
