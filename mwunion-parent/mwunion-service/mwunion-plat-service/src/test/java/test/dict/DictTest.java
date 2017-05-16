package test.dict;

import java.util.List;

import org.junit.Test;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.commons.DictUtil;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.dao.model.adenum.EnumRecWorkStatus;

/**
 * 说明:
 */
public class DictTest extends BaseTest{
	
	@Test
	public void testDict() throws ClassNotFoundException{
//		EnumRecWorkStatus working = EnumRecWorkStatus.WORKING;
		Class.forName("com.sungan.ad.dao.model.adenum.EnumRecWorkStatus");
		List<DictItem> dict = DictUtil.getDict("RECCEPTER_STATUS");
		System.out.println(dict);
	}
}
