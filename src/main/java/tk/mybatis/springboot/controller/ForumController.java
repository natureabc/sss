package tk.mybatis.springboot.controller;


import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.Banner;
import tk.mybatis.springboot.model.Forum;
import tk.mybatis.springboot.model.PageBean;
import tk.mybatis.springboot.model.Satuation;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.model.vo.Question;
import tk.mybatis.springboot.service.ForumService;
import tk.mybatis.springboot.util.AuthUtils;
import tk.mybatis.springboot.util.RandomUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("forum")
public class ForumController {

    private static final Logger log = LoggerFactory.getLogger(ForumController.class);
    @Resource
    private ForumService forumService;

    @Value("${sys.img.readPath}")
    private String imgPath;

    @RequestMapping("index")
    public ModelAndView toIndex(HttpServletRequest request){
        request.getSession();
        request.getSession().setAttribute("img",imgPath);

        return new ModelAndView("forumIndex");
    }

    @RequestMapping("getAllList")
    public Object getAllList(Integer labelId,Integer keywordId,PageBean pageBean){
        PageBean page=forumService.getAllList(labelId,keywordId,pageBean);
        return page;
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail(Integer id,Integer nextId,Integer preId){
        ModelAndView mv=new ModelAndView("forumDetail");
        mv.addObject("forumId",id);
        mv.addObject("nextId",nextId);
        mv.addObject("preId",preId);
        return mv;
    }

    @RequestMapping("getForumDetailById")
    public Object getForumDetailById(Integer forumId){

        ForumVo f=forumService.getForumDetailById(forumId);
        return f;
    }

    @RequestMapping("getSatuationList")
    public Object getSatuationList(){
        List<Satuation> list=forumService.getSatuationList();
        return list;
    }


    @RequestMapping("getSatutionDetail")
    public ModelAndView getSatutionDetail(){
        return new ModelAndView("satuationlist");
    }

    @RequestMapping("getKeyWordList")
    public Object getKeyWordList(){

        return forumService.getKeyWordList();
    }

    @RequestMapping("getLabelList")
    public Object getLabelList(){
        return forumService.getLabelList();
    }

    @RequestMapping("getToolTitle")
    public Object getToolTitle(){

        return forumService.getToolTitle();

    }

    @RequestMapping("getBannerList")
    public Object getBannerList(){

        List<Banner> list=forumService.getBannerList();
        return list;
    }

   // String token="9a8ea002-c842-40d3-8f5d-5961143abc404";
    @RequestMapping("testRequest")
    public void testRequest(String token){
        int count=0;
        for(int i=0;i<120000;i++){
            try{
               // Thread.sleep(50);
                testQuestion(token);
                count++;
                log.info("调用数量=="+count);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }




    public void testQuestion(String token){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://gdll.winner123.cn/api/g/mgudon-exercise-game-service/v/searchexercisequestion")
                    .method("POST", body)
                    .addHeader("Cookie", "token="+token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            String bodyStr=response.body().string();
            Question q= JSON.parseObject(bodyStr,Question.class);
            if(!q.getError_no().equals("0")){
                getCheckCode(token);
            }
          //  log.info(bodyStr);
                testAnswer(q.getResult().getQuestion_no(),token);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

   // @RequestMapping("testAnswer")
    public void testAnswer(String no,String token){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String [] options={"A","B","C","D"};
            Random random = new Random();
            int pick = random.nextInt(4);
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "question_no="+no+"&right_option="+options[pick]);
            Request request = new Request.Builder()
                    .url("https://gdll.winner123.cn/api/g/mgudon-exercise-game-service/v/addexerciseanswer")
                    .method("POST", body)
                    .addHeader("Cookie", "token="+token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            String bodyStr=response.body().string();
            Question q= JSON.parseObject(bodyStr,Question.class);
            if(!q.getError_no().equals("0")){
                getCheckCode(token);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("testCheck")
    public void testCheck(String token){
        getCheckCode(token);
    }

    public void getCheckCode(String token){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://gdll.winner123.cn/api/g/mgudon-exercise-game-service/v/getcheckcanexercisecode")
                    .method("POST", body)
                    .addHeader("Cookie", "token="+token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            String bodyStr=response.body().string();
            Question q= JSON.parseObject(bodyStr,Question.class);
            log.info(bodyStr);
            String base64Str=q.getResult().getJepg_byte_base64();
            int result=checkCode(base64Str,token);
            if(result==1){
                //验证成功后需要初始化
                initCheckCode(token);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int checkCode(String base64Str,String token){

        try {

           // int resultNum[]= RandomUtil.getRandomNum();
          /*  String str0=codes[resultNum[0]];
            String str1=codes[resultNum[1]];
            String str2=codes[resultNum[2]];
            String str3=codes[resultNum[3]];
            String str=str0+str1+str2+str3;*/
            //调用百度识别接口
            String appKey="qRKbXq81f4QXOGt5TVGFf7QT";
            String secret="TNsba7xTgnfE6iC4y5fjbgeoLrujhd1C";
            String accessToken=AuthUtils.getAuth(appKey,secret);
            String result=AuthUtils.webImage(base64Str,accessToken);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "check_code="+result);
            Request request = new Request.Builder()
                    .url("https://gdll.winner123.cn/api/g/mgudon-exercise-game-service/v/checkcanexercise")
                    .method("POST", body)
                    .addHeader("Cookie", "token="+token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            String bodyStr=response.body().string();
            Question q= JSON.parseObject(bodyStr,Question.class);
            if(q.getError_no().equals("0")){
                log.info("验证成功xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                return 1;
            }else{
                log.info("验证失败===================================");
                return 0;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public void initCheckCode(String token){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://gdll.winner123.cn/api/g/mgudon-exercise-game-service/v/inituserexercisequestioninfo")
                    .method("POST", body)
                    .addHeader("Cookie", "token="+token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            String bodyStr = response.body().string();
            log.info(bodyStr);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
