package tk.mybatis.springboot.util;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;


public class ImportExcel {
			
	 private  static  Object  dataObj(Object obj, HSSFRow row,Class clazz) throws Exception { 
			Class<?> rowClazz= obj.getClass();      
		    Field[] fields = FieldUtils.getAllFields(rowClazz);  
		    if (fields == null || fields.length < 1) {  
		        return null;  
		    }  
		       	
		    //容器  
		    Map<String,Object> map=new HashMap<String,Object>();
		    
		    
		    //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）  
		    for (int j = 0; j < fields.length; j++) {  
		        map.put(fields[j].getName(), getVal(row.getCell(j)));  
		    } 
		    
		    return converMap(clazz,map); 
		}  
		  
		public  static   List<?> importExcel(InputStream inputStream, Object obj,Class clazz) throws Exception {
		      
		    //装载流
			//InputStream input=new FileInputStream(file);
		    POIFSFileSystem fs = new POIFSFileSystem(inputStream);
		    HSSFWorkbook hw= new HSSFWorkbook(fs);  
		      
		    //获取第一个sheet页  
		    HSSFSheet sheet = hw.getSheetAt(0);  
		      
		    //容器  
		    List<Object> ret = new ArrayList<Object>();  
		      
		    //遍历行 从下标第一行开始（去除标题）  
		    //System.out.println("行数="+sheet.getLastRowNum());
		    for (int i = 1; i < sheet.getLastRowNum()+1; i++) {  
		        HSSFRow row= sheet.getRow(i);  
		        if(row!=null){  
		            //装载obj  
		             ret.add(dataObj(obj,row,clazz));  
		        }
		    }  
		    return ret;  
		}  
		  
		/** 
		 * 处理val（暂时只处理string和number，可以自己添加自己需要的val类型） 
		 * @param hssfCell 
		 * @return 
		 */  
		public static Object getVal(HSSFCell hssfCell) {  
			if(hssfCell==null) {
		    	return null;
		    }
		    if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  
		    	
		        return hssfCell.getStringCellValue();  
		    }else if(hssfCell.getCellType() ==HSSFCell.CELL_TYPE_BOOLEAN) {
		    	return hssfCell.getBooleanCellValue()+"";
		    }else {  
		        return (int)hssfCell.getNumericCellValue();  
		    }  
		}  

		
		
		public static Object converMap(Class clazz,Map<String, Object> map )throws Exception{
			
			//Map<String, Object> map = new HashMap<String, Object>();  
		    BeanInfo beanInfo=Introspector.getBeanInfo(clazz);
		    Object obj=clazz.newInstance();
		    PropertyDescriptor[] propertyDescriptors=beanInfo.getPropertyDescriptors();
		    for(PropertyDescriptor descriptor : propertyDescriptors) {
		    	String propertyName=descriptor.getName();
		    	System.out.println(propertyName);
		    	if(map.containsKey(propertyName)) {
		    		Object value=map.get(propertyName);
		    		if(propertyName.equals("orderPayItemIds")) {
		    			value=value.toString();
		    		}
		    		System.out.println(value);
		    		descriptor.getWriteMethod().invoke(obj, value);
		    	}
		    }
		    return obj;
		    
		}
		

}
