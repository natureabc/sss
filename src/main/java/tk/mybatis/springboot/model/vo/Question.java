package tk.mybatis.springboot.model.vo;

public class Question {

    private String error_no;
    private String error_info;
    private QuestionResult result;

    public String getError_no() {
        return error_no;
    }

    public void setError_no(String error_no) {
        this.error_no = error_no;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public QuestionResult getResult() {
        return result;
    }

    public void setResult(QuestionResult result) {
        this.result = result;
    }
}
