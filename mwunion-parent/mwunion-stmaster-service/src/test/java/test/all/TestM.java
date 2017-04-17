package test.all;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.service.st.StmasterService;
import com.sungan.ad.vo.st.StmasterVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
public class TestM extends BaseTest {
    @Autowired
    private StmasterService stmasterService;

    @Test
    public void testQuery(){
        StmasterVo stmasterVo = stmasterService.find("ef20d5e33bd1430b88b3a322a39e751c");
        System.out.println(stmasterVo.getUserStatusCn());
    }
}
