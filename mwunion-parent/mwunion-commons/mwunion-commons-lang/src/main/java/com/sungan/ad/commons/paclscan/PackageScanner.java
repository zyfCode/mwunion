package com.sungan.ad.commons.paclscan;

import java.io.IOException;
import java.util.List;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月31日
 */
public interface PackageScanner {
	
	List<String> getFullyQualifiedClassNameList() throws IOException;
}
