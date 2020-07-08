package tk.mybatis.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.User;
import tk.mybatis.springboot.util.ImportExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

@RestController
@RequestMapping("bridgeapi")
public class ExcelController {


    @RequestMapping("toPage")
    public ModelAndView toPage(){
        return new ModelAndView("findPictrueByExcel");

    }

    @RequestMapping("doImportExcel")
    public void doImportExcel(@RequestParam(value="excelFile")MultipartFile file,String path) throws Exception {
        User user=new User();
        List<User> list= (List<User>)ImportExcel.importExcel(file.getInputStream(), user,User.class);
        File files=new File(path);
        File[] filelist=files.listFiles();
        for(int i=0;i<filelist.length;i++){
            String fileName=filelist[i].getName();
            for(int j=0;j<list.size();j++){
                if(fileName.contains(list.get(j).getUserName())){
                   // System.out.println(list.get(j).getUserName());
                    FileInputStream input=new FileInputStream(filelist[i]);
                    FileOutputStream output=new FileOutputStream("D:/pic/"+filelist[i].getName());
                    int in=input.read();
                    while(in!=-1){
                        output.write(in);
                        in=input.read();
                    }
                    input.close();
                    output.close();
                }
            }
        }



    }

}
