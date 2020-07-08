package tk.mybatis.springboot.model;

public class ConsuUser {

	
	private String id;
	private String pat_id;
	private String doc_id;
	private String illness_detail;
	private String create_time;
	private Integer con_status;
	private String first_img_url;
	private Integer con_type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPat_id() {
		return pat_id;
	}
	public void setPat_id(String pat_id) {
		this.pat_id = pat_id;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getIllness_detail() {
		return illness_detail;
	}
	public void setIllness_detail(String illness_detail) {
		this.illness_detail = illness_detail;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getCon_status() {
		return con_status;
	}
	public void setCon_status(Integer con_status) {
		this.con_status = con_status;
	}
	public String getFirst_img_url() {
		return first_img_url;
	}
	public void setFirst_img_url(String first_img_url) {
		this.first_img_url = first_img_url;
	}
	public Integer getCon_type() {
		return con_type;
	}
	public void setCon_type(Integer con_type) {
		this.con_type = con_type;
	}
	
	
	
}
