package test;

import java.io.*;

/**
 * Created by hukai on 2016/9/18.
 */
public class IoTest {
    public static void main(String [] args){
        try{
            /**
             * 1
             */
//            String fileName="F:"+ File.separator+"123.txt";
//            File f=new File(fileName);
//            InputStream in=new FileInputStream(f);
//            byte[] b=new byte[1024];
//            in.read(b);
//            in.close();
//            System.out.println(new String(b));

            /**
             * 2
             */
//            String fileName = "F:\\123.txt";
//            File f = new File(fileName);
//            InputStream in = new FileInputStream(f);
//            byte[] b = new byte[1024];
//            int len = in.read(b);
//            in.close();
//            System.out.println("读入长度为："+len);
//            System.out.println(new String(b));
            /**
             * 3
             */
//            String fileName="F:"+ File.separator+"123.txt";
//            File f=new File(fileName);
//            InputStream in=new FileInputStream(f);
//            byte[] b=new byte[(int)f.length()];
//            in.read(b);
//            System.out.println("文件长度为："+f.length());
//            in.close();
//            System.out.println(new String(b));
//            System.out.println(Arrays.toString(b));
            /**
             * 3
             */
//            String fileName="F:"+File.separator+"123.txt";
//            File f=new File(fileName);
//            InputStream in=new FileInputStream(f);
//            byte[] b=new byte[(int)f.length()];
//            for (int i = 0; i < b.length; i++) {
//                b[i]=(byte)in.read();
//            }
//            in.close();
//            System.out.println(new String(b));
            /**
             * 4
             */
//            String fileName = "F:\\123.txt";
//            File f = new File(fileName);
//            InputStream in = new FileInputStream(f);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String isLast;
//            while ((isLast = br.readLine())!=null){
//                System.out.println(isLast);
//            }
            /**
             * 5 从键盘输入，直到取到“kk”停止
             */
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            while(!br.readLine().equals("kk"));
//            br.close();
//            System.out.println("get a kk ^-^ ");
            /**
             * 6
             */
            //写入文件
//            String fileName="F:"+ File.separator+"123.txt";
//            File f=new File(fileName);
//            OutputStream os=new FileOutputStream(f);//字节流
//            OutputStreamWriter osw = new OutputStreamWriter(os);//字符流
//            osw.append("哈哈");
//            osw.append("\r\n");
//            osw.append("cccc");
//            osw.close();
//            os.close();
//
//            //读取文件
//            InputStream in = new FileInputStream(f);
//            InputStreamReader inr = new InputStreamReader(in);
//            StringBuffer sb = new StringBuffer();
//            while(inr.ready()){
//                sb.append((char)inr.read());
//            }
//            System.out.println(sb.toString());
//            inr.close();
//            in.close();

            /**
             * 7
             */
            InputStream in = new FileInputStream("F:\\123.txt");
            Reader r = new InputStreamReader(in);
            int b;
            int i=0;
//            while ((b=in.read())!=-1){
//                System.out.println((char)b);
//                i++;
//            }
//            System.out.println(i);
            while (r.ready()){
                System.out.println((char) r.read());
                i++;
            }
            System.out.println(i);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
