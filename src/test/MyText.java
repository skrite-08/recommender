import com.sun.utils.MD5Util;

public class MyText{
    public static void main(String[] args){

        String a="123456";
        MD5Util md=new MD5Util();
        System.out.println(md.string2MD5(a));
    }




}