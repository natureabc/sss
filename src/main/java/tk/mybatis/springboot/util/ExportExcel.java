package tk.mybatis.springboot.util;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {

	public void createExcel(String[] title, List<?> list, String[] keys,File file /*HttpServletResponse response*/) {
		try {
			//OutputStream os = response.getOutputStream();// 取得输出流
			//response.reset();// 清空输出流

			// List<AcctAudit> list=new ArrayList<AcctAudit>();
			JSONArray arr = new JSONArray();
			arr = JSONArray.parseArray(JSONObject.toJSONString(list));
			// //需要导出列的对象名(个数可自由控制)

			// 下面是对中文文件名的处理
			//response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
			//fname = java.net.URLEncoder.encode(fname, "UTF-8");
			//response.setHeader("Content-Disposition",
			//		"attachment;filename=" + new String(fname.getBytes("UTF-8"), "GBK") + ".xls");
			//response.setContentType("application/msexcel");// 定义输出类型
			
			// 创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(file);

			// 创建新sheet
			WritableSheet sheet = workbook.createSheet("sheet1", 0);

			// 创建标题栏
			for (int i = 0; i < title.length; i++) {
				String titleStr = title[i];
				Label name = new Label(i, 0, titleStr);
				sheet.addCell(name);
			}

			// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
			for (int i = 0; i < arr.size(); i++) {
				Label name = null;
				// Number xuhao1=new Number(0,i,i);//创建序号
				// sheet.addCell(xuhao1);
				for (int j = 0; j < keys.length; j++) {
					String key = keys[j];
					String value = arr.getJSONObject(i).getString(key);
					if (StringUtils.isEmpty(value)) {
						value = "";
					}
					name = new Label(j, i + 1, value.toString());
					sheet.addCell(name);
				}

			}

			// 把创建的内容写入到输出流中，并关闭输出流
			workbook.write();
			workbook.close();
			//os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
