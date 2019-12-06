package com.example.wx.demo.utility;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wx.demo.dto.template.ResIndustry;
import com.example.wx.demo.dto.token.AccessToken;
import com.example.wx.demo.dto.message.BaseMessage;
import com.example.wx.demo.dto.message.ImageMessage;
import com.example.wx.demo.dto.message.MusicMessage;
import com.example.wx.demo.dto.message.NewsMessage;
import com.example.wx.demo.dto.message.TextMessage;
import com.example.wx.demo.dto.message.VideoMessage;
import com.example.wx.demo.dto.message.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeChatUtil {
    private static final String TOKEN = "LGYNB";
    private static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String APPID = "wx1b35981b4026fd95";
    private static final String APPSECRET = "5c4fc5f098106f1d86da6a8afff989bf";
    private static final String POST_WECHATMENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static final String POST_SETINDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    private static final String POST_SENDTEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    private static final String GET_GETINDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    private static AccessToken at;

    /**
     * post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private static String weChatPost(String url,String params) throws IOException {
        //创建HTTPClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //声明要请求的url，并构造HTTPPost请求
        HttpPost post = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(params,"utf-8");
        post.setEntity(requestEntity);
        //让HTTPClient去发送Post请求并得到响应
        HttpResponse response  = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        return result;
    }

    /**
     * get请求
     * @param url
     * @return
     * @throws IOException
     */
    private static String weChatGet(String url) throws IOException {
        //创建HTTPClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //声明要请求的url，并构造HTTPGet请求
        HttpGet get = new HttpGet(url);
        //让HTTPClient去发送get请求并得到响应
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    /**
     * 初始化菜单
     * @param param
     */
    public static void menuInit(String param){
        try {
            String url = POST_WECHATMENU_URL.replace("ACCESS_TOKEN",getAccessToken());
            String result = weChatPost(url,param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            if ("0".equals(errcode)){
                System.out.println("菜单初始化成功");
            }else {
                System.out.println("菜单初始化失败：错误码："+errcode+"\n错误信息"+errmsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("菜单初始化异常");
        }
    }
    /**
     * 验证签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean check(String signature, String timestamp, String nonce) {
        // 1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[] {TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
        System.out.println(mysig);
        System.out.println(signature);
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * sha1加密
     * @param str
     * @return
     */
    private static String sha1(String str) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for (byte b : digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析xml数据包
     * @param is
     * @return
     */
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(is);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            //获取根节点的所有的字节点
            List<Element> elements = root.elements();
            for (Element e : elements){
                map.put(e.getName(),e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 把消息对象处理为xml数据包
     * @return
     */
    public static String beanToXml(BaseMessage msg) {
        XStream xStream = new XStream();
        //设置需要处理的XStreamAlias("xml")注释类
        xStream.processAnnotations(BaseMessage.class);
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(ImageMessage.class);
        xStream.processAnnotations(MusicMessage.class);
        xStream.processAnnotations(VoiceMessage.class);
        xStream.processAnnotations(VideoMessage.class);
        xStream.processAnnotations(NewsMessage.class);
        String xml = xStream.toXML(msg);
        return xml;
    }


    /**
     * 获取token
     * @throws Exception
     */
    private static void getToken() throws Exception{
        String url = GET_ACCESSTOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        String result = weChatGet(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String accessToken = jsonObject.getString("access_token");
        String expireIn = jsonObject.getString("expires_in");
        at = new AccessToken(accessToken,expireIn);
    }

    /**
     * 对外暴露接口token
     * @return
     */
    public static String getAccessToken(){
        if (null == at || at.isExpired()){
            try {
                getToken();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return at.getAccessToken();
    }


    /**
     * 设置行业
     * @param param
     * @throws IOException
     */
    public static void setIndustry(String param) throws IOException{
        String url = POST_SETINDUSTRY_URL.replace("ACCESS_TOKEN",getAccessToken());
        String result = weChatPost(url,param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");
        if ("0".equals(errcode)){
            System.out.println("设置行业成功");
        }else {
            System.out.println("设置行业失败：错误码："+errcode+"\n错误信息"+errmsg);
        }
    }

    public static ResIndustry getIndustry() throws IOException {
        String url = GET_GETINDUSTRY_URL.replace("ACCESS_TOKEN",getAccessToken());
        String result = weChatGet(url);
        ResIndustry resIndustry = JSON.parseObject(result,ResIndustry.class);
        return resIndustry;
    }

    public static void sendTemplateMessage(String param)throws Exception{
        String url = POST_SENDTEMPLATE_URL.replace("ACCESS_TOKEN",getAccessToken());
        String result = weChatPost(url,param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");
        String msgid = jsonObject.getString("msgid");
        if ("0".equals(errcode)){
            System.out.println("模板消息推送成功，msgid:"+msgid);
        }else {
            System.out.println("模板消息推送失败：错误码："+errcode+"\n错误信息"+errmsg);
        }
    }
}
