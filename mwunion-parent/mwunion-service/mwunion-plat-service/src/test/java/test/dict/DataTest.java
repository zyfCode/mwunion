package test.dict;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.dao.log.DayuvLog;
import com.sungan.ad.service.plat.settle.LogWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by zhangyf18255 on 2017/5/7.
 */
public class DataTest  extends BaseTest {

    @Autowired
    private LogWorker logWorker;
    @Autowired
    private String localLogPath;

    public void genData(){
        File file = new File(localLogPath);
        String adHostId= "140fe8cb8604498395c0619ac98f9388";
        DayuvLog log = new DayuvLog();
//        log.setAdOrderId();

    }

    public void testData(){

    }

}
