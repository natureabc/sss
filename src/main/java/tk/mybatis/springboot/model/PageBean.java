package tk.mybatis.springboot.model;

import java.util.List;

public class PageBean {

	private Integer page=1;
	private Integer rows=10;
	private Integer resultNum;
	
	private Integer count;
	private int pageCount;
	private List list;
	public Integer getPage() {
		
			return page;
		
		
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		
			return rows;
		
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getResultNum() {
		if(page != null && page>1){
			resultNum=(page-1)*rows;
		}else {
			resultNum=0;
		}
		return resultNum;
	}
	public void setResultNum(Integer resultNum) {
		this.resultNum = resultNum;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public PageBean(Integer page, Integer rows, Integer count, List list) {
		super();
		this.page = page;
		this.rows = rows;
		this.count = count;
		this.list = list;
		if(null != count && rows != null){
			pageCount =(count+rows-1)/rows;
		}
	}
	
	public PageBean() {
	}
	
}
