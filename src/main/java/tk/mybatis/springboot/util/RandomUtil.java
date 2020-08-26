package tk.mybatis.springboot.util;

import java.util.Random;

public class RandomUtil {


    public static int [] getRandomNum(){

        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while(i < 4){
            int t = ran.nextInt(7);
            if(sb.indexOf(String.valueOf(t)) == -1){
                sb.append(t);
                i++;
            }
        }
        String result=sb.toString();
        System.out.println("randomnum=========================="+result);

        int resultNum[]=new int[result.length()];
        resultNum[0]=Integer.parseInt(result.substring(0,1));
        resultNum[1]=Integer.parseInt(result.substring(1,2));
        resultNum[2]=Integer.parseInt(result.substring(2,3));
        resultNum[3]=Integer.parseInt(result.substring(3));
        return resultNum;
    }

}
