package test.all;

import com.sungan.ad.CacheTran;
import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.commons.ClientUtil;
import com.sungan.ad.commons.DateUtil;
import com.sungan.ad.commons.EnumClientType;
import com.sungan.ad.dao.log.DayuvLog;
import com.sungan.ad.dao.model.AdHostAccountAdOrder;
import com.sungan.ad.dao.model.PlatAccount;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderStatus;
import com.sungan.ad.dao.model.adenum.EnumStmasterSiteStatus;
import com.sungan.ad.dao.sys.PlatAccountWrapper;
import com.sungan.ad.dao.sys.SystemParamUtil;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderService;
import com.sungan.ad.service.adhost.AdHostService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderVo;
import com.sungan.ad.service.plat.settle.DayuvLogService;
import com.sungan.ad.service.plat.settle.LogWorker;
import com.sungan.ad.service.st.StmasterService;
import com.sungan.ad.service.st.StmasterSiteService;
import com.sungan.ad.vo.st.StmasterSiteVo;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangyf18255 on 2017/5/14.
 */
public class MesgGen extends BaseTest {
    /**
     *
     */
    @Autowired
   private DayuvLogService service;
    @Autowired
    private AdHostAccountAdOrderService orderService;
    @Autowired
    private AdHostService adHostService;
    @Autowired
    private StmasterService stmasterService;
    @Autowired
    private StmasterSiteService siteService;
    @Autowired
    private LogWorker logWorker;

    @Value("${log.file.path}")
    private String parentFile;
    @Autowired
    private CacheTran cacheTran;

    @Test
    public void testAcount(){
        cacheTran.testTan();
    }
    @Test
    public void testWork(){
        this.testGd();
        logWorker.Work();
    }


    public static void main(String[] args){
        List<String> agents = getAgent();
        for(String agent:agents) {
            EnumClientType typeByAgent = ClientUtil.getTypeByAgent(agent);
            System.out.println(typeByAgent.getDesc()+"::"+agent);
        }
    }

    private static List<String> getAgent(){
        try {
//            File file = new File("D:/log/agents_微信.txt");
            File file = new File("D:/log/agents_Safari.txt");
            BufferedReader eader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            Properties clientAgent = new Properties();
            clientAgent.load(eader);
            Set<Map.Entry<Object, Object>> entries = clientAgent.entrySet();
            List<String> arr = new ArrayList<>();
            for(Map.Entry<Object, Object> entry:entries){
                Object key = entry.getKey();
                Object value = entry.getValue();
                arr.add(value+"");
//                System.out.println(key+":"+value);
            }
            eader.close();
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private  List<AdHostAccountAdOrderVo> adHostAccountAdOrderVos;
    private AdHostAccountAdOrderVo randomOrderId(){
        if(adHostAccountAdOrderVos==null) {
            AdHostAccountAdOrder condition = new AdHostAccountAdOrder();
            condition.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey());
            adHostAccountAdOrderVos = orderService.queryList(condition);
        }
        if(adHostAccountAdOrderVos==null||adHostAccountAdOrderVos.size()<1){
            System.out.println("订单信息为空!");
            throw new RuntimeException("订单信息为空");
        }
        int size = adHostAccountAdOrderVos.size();
        Random random = new Random();
        int i = random.nextInt(size);
        AdHostAccountAdOrderVo adHostAccountAdOrderVo = adHostAccountAdOrderVos.get(i);
        return adHostAccountAdOrderVo;
    }
    private List<StmasterSiteVo> stmasterSiteVos;
    private StmasterSiteVo randomSite(){
        if(stmasterSiteVos==null) {
            StmasterSite siteCondition = new StmasterSite();
            siteCondition.setSiteStatus(EnumStmasterSiteStatus.NORMAL.getKey());
            stmasterSiteVos = siteService.queryList(siteCondition);
        }
        if(stmasterSiteVos==null||stmasterSiteVos.size()<1){
            System.out.println("站点信息为空!");
            throw new RuntimeException("");
        }
        int size = stmasterSiteVos.size();
        Random random = new Random();
        int i = random.nextInt(size);
        StmasterSiteVo stmasterSiteVo = stmasterSiteVos.get(i);
        return stmasterSiteVo;
    }
    private String randomAgent(){
        List<String> agent = getAgent();
        int size = agent.size();
        Random random = new Random();
        int i = random.nextInt(size);
        String s = agent.get(i);
        return s;
    }
    @Test
    public void testGd(){
        List<DayuvLog> dataList = new ArrayList<>();
        for (int i=0;i<200;i++) {
            DayuvLog log = new DayuvLog();
            AdHostAccountAdOrderVo adHostAccountAdOrderVo = this.randomOrderId();
            log.setAdOrderId(adHostAccountAdOrderVo.getAdOrderId());
            log.setAdHostId(adHostAccountAdOrderVo.getAdHostId());
            log.setAccesTime(new Date());
            log.setAccesTimeInt(DateUtil.dateToIntDayNow());
            log.setAdOrderType(adHostAccountAdOrderVo.getAdOrderType());
            log.setClientAgent(this.randomAgent());
            StmasterSiteVo stmasterSiteVo = this.randomSite();
            log.setStId(stmasterSiteVo.getStId());
            log.setStSiteId(stmasterSiteVo.getSiteId());
            log.setStSiteName(stmasterSiteVo.getSiteName());
            log.setUvIp(getRandomIp());
            log.setUvKey(getUuid());
            dataList.add(log);
        }
        writeToDisk(dataList);
    }

    private void writeToDisk(List<DayuvLog> logs){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String format1 = format.format(new Date());
        String fileNme = format1+getUuid()+".data";
        File parent = new File(parentFile);
        if(!parent.exists()){
            parent.mkdirs();
        }
        try {
            File dataFile = new File(parent,fileNme);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile), "UTF-8"));
            for (DayuvLog log : logs) {
                JSONObject obj = JSONObject.fromObject(log);
                writer.write(obj.toString()+"\r\n");
            }
            writer.flush();
            writer.close();
            File okFile = new File(parent,fileNme+".ok");
            okFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getUuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }


    /*
     * 随机生成国内IP地址
     */
    public static String getRandomIp(){

        //ip范围
        int[][] range = {{607649792,608174079},//36.56.0.0-36.63.255.255
                {1038614528,1039007743},//61.232.0.0-61.237.255.255
                {1783627776,1784676351},//106.80.0.0-106.95.255.255
                {2035023872,2035154943},//121.76.0.0-121.77.255.255
                {2078801920,2079064063},//123.232.0.0-123.235.255.255
                {-1950089216,-1948778497},//139.196.0.0-139.215.255.255
                {-1425539072,-1425014785},//171.8.0.0-171.15.255.255
                {-1236271104,-1235419137},//182.80.0.0-182.92.255.255
                {-770113536,-768606209},//210.25.0.0-210.47.255.255
                {-569376768,-564133889}, //222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0]+new Random().nextInt(range[index][1]-range[index][0]));
        return ip;
    }

    /*
         * 将十进制转换成ip地址
         */
    public static String num2ip(int ip) {
        int [] b=new int[4] ;
        String x = "";

        b[0] = (int)((ip >> 24) & 0xff);
        b[1] = (int)((ip >> 16) & 0xff);
        b[2] = (int)((ip >> 8) & 0xff);
        b[3] = (int)(ip & 0xff);
        x=Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]);

        return x;
    }


}
