package com.qc.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class HttpRequestUtils {
	
	/**
	 * 获取项目路径
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回项目路径，例如：http://www.ip.com/projectName 后面没有反斜杠，后面接地址或参数必须加/
	 */
	public static String getRequestFullPath(HttpServletRequest request) {
		
		int port = request.getServerPort();
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme()).append("://").append(request.getServerName());
		if (port != 80) {
			
			sb.append(":").append(port);
		}
		
		return sb.append(request.getContextPath()).toString();
	}
	
	/**
	 * 获取请求域名，域名不包括http请求协议头
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 返回域名地址
	 */
	public static String getRequestFullPathWithoutScheme(HttpServletRequest request) {
		
		String path = request.getContextPath();
		String domain = request.getServerName();
		int port = request.getServerPort();
		
		return port == 80 ? domain + path : domain + ":" + request.getServerPort() + path;
	}
	
	/**
	 * 根据当前请求的特征，判断该请求是否来自手机终端，主要检测特殊的头信息，以及user-Agent这个header
	 * 
	 * @param request
	 *            HttpServletRequest对象,http请求
	 * @return 如果命中手机特征规则，则返回对应的特征字符串
	 */
	public static boolean isMobileDevice(HttpServletRequest request) {
		
		String via = request.getHeader("Via");
		String userAgent = request.getHeader("controller-agent");
		
		boolean mobileFlag = containsItem(via, mobileGateWayHeaders);
		
		boolean pcFlag = containsItem(userAgent, pcHeaders);
		
		if(!mobileFlag) {
			
			mobileFlag = containsItem(userAgent, mobileUserAgents);
		}
		
		return mobileFlag && !pcFlag;
	}
	
	private static boolean containsItem(String str, String[] items) {
		
		if (StringUtils.isNotBlank(str)) {

			for(String item : items) {
				
				if (str.contains(item)) {
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 获取当期项目物理路径
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param filePath
	 *            相对路径文件夹
	 * @return 返回当期项目物理路径
	 */
	public static String getRealPath(HttpServletRequest request, String filePath) {
		
		return request.getServletContext().getRealPath("") + File.separator + filePath;
	}
	
	/**
	 * 获取请求客户端ip
	 * 
	 * @param request
	 * @return ip地址
	 */
	public static String getIp(HttpServletRequest request) {
		
		String ipAddress = request.getHeader("x-forwarded-for");
		
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			
			ipAddress = request.getRemoteAddr();
			
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				try {
					
					ipAddress = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {//
				}
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(',') > -1) {
				
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
		}
		
		return ipAddress;
	}

	/**
	 * 获取对应ip地址的mac地址
	 * 
	 * @param ip
	 * @return mac地址
	 */
	public static String getMACAddress(String ip) {
		
		try(InputStreamReader ir = 
				new InputStreamReader(Runtime.getRuntime().exec("nbtstat -A " + ip).getInputStream());
				LineNumberReader input = new LineNumberReader(ir);) {
			
			String str;
			
			for (int i = 1; i < 100; i++) {
				
				str = input.readLine();
				
				if (StringUtils.isNotEmpty(str)) {
					
					int index = str.indexOf("MAC Address");
					
					return index < 0 ? StringUtils.EMPTY : str.substring(index + 14, str.length());
				}
			}
		} catch (IOException e) {//
		}
		
		return StringUtils.EMPTY;
	}
	
	/** Wap网关Via头信息中特有的描述信息 */
	private static String[] mobileGateWayHeaders = new String[] { "ZXWAP", // 中兴提供的wap网关的via信息，例如：Via=ZXWAP
																			// GateWayZTE
																			// Technologies，
			"chinamobile.com", // 中国移动的诺基亚wap网关，例如：Via=WTP/1.1
								// GDSZ-PB-GW003-WAP07.gd.chinamobile.com (Nokia
								// WAP Gateway 4.1 CD1/ECD13_D/4.1.04)
			"monternet.com", // 移动梦网的网关，例如：Via=WTP/1.1
								// BJBJ-PS-WAP1-GW08.bj1.monternet.com. (Nokia
								// WAP
								// Gateway 4.1 CD1/ECD13_E/4.1.05)
			"infoX", // 华为提供的wap网关，例如：Via=HTTP/1.1 GDGZ-PS-GW011-WAP2
						// (infoX-WISG
						// Huawei Technologies)，或Via=infoX WAP Gateway V300R001
						// Huawei Technologies
			"XMS 724Solutions HTG", // 国外电信运营商的wap网关，不知道是哪一家
			"wap.lizongbo.com", // 自己测试时模拟的头信息
			"Bytemobile"// 貌似是一个给移动互联网提供解决方案提高网络运行效率的，例如：Via=1.1 Bytemobile OSN
							// WebProxy/5.1
	};
	
	/** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
	private static String[] pcHeaders = new String[] { "Windows 98", "Windows ME", "Windows 2000", "Windows XP",
			"Windows NT", "Ubuntu" };
	
	/** 手机浏览器的User-Agent里的关键词 */
	private static String[] mobileUserAgents = new String[] { "Nokia", // 诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0
																		// (Nokia5800
																		// XpressMusic)UC
																		// AppleWebkit(like
																		// Gecko)
																		// Safari/530
			"SAMSUNG", // 三星手机
						// SAMSUNG-GT-B7722/1.0+SHP/VPP/R5+Dolfin/1.5+Nextreaming+SMM-MMS/1.2.0+profile/MIDP-2.1+configuration/CLDC-1.1
			"MIDP-2", // j2me2.0，Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2
						// NokiaE75-1 /110.48.125 Profile/MIDP-2.1
						// Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML like
						// Gecko) Safari/413
			"CLDC1.1", // M600/MIDP2.0/CLDC1.1/Screen-240X320
			"SymbianOS", // 塞班系统的，
			"MAUI", // MTK山寨机默认ua
			"UNTRUSTED/1.0", // 疑似山寨机的ua，基本可以确定还是手机
			"Windows CE", // Windows CE，Mozilla/4.0 (compatible; MSIE 6.0;
							// Windows CE; IEMobile 7.11)
			"iPhone", // iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU
						// iPhone OS 4_1 like Mac OS X; zh-cn) AppleWebKit/532.9
						// (KHTML like Gecko) Mobile/8B117
			"iPad", // iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X;
					// zh-cn) AppleWebKit/531.21.10 (KHTML like Gecko)
					// Version/4.0.4 Mobile/7B367 Safari/531.21.10
			"Android", // Android是否也转wap？Mozilla/5.0 (Linux; U; Android
						// 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7)
						// AppleWebKit/530.17 (KHTML like Gecko) Version/4.0
						// Mobile Safari/530.17
			"BlackBerry", // BlackBerry8310/2.7.0.106-4.5.0.182
			"UCWEB", // ucweb是否只给wap页面？ Nokia5800
						// XpressMusic/UCWEB7.5.0.66/50/999
			"ucweb", // 小写的ucweb貌似是uc的代理服务器Mozilla/6.0 (compatible; MSIE 6.0;)
						// Opera ucweb-squid
			"BREW", // 很奇怪的ua，例如：REW-Applet/0x20068888 (BREW/3.1.5.20; DeviceId:
					// 40105; Lang: zhcn) ucweb-squid
			"J2ME", // 很奇怪的ua，只有J2ME四个字母
			"YULONG", // 宇龙手机，YULONG-CoolpadN68/10.14 IPANEL/2.0 CTC/1.0
			"YuLong", // 还是宇龙
			"COOLPAD", // 宇龙酷派YL-COOLPADS100/08.10.S100 POLARIS/2.9 CTC/1.0
			"TIANYU", // 天语手机TIANYU-KTOUCH/V209/MIDP2.0/CLDC1.1/Screen-240X320
			"TY-", // 天语，TY-F6229/701116_6215_V0230 JUPITOR/2.2 CTC/1.0
			"K-Touch", // 还是天语K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
						// Release/30.07.2008 Browser/WAP2.0
			"Haier", // 海尔手机，Haier-HG-M217_CMCC/3.0 Release/12.1.2007
						// Browser/WAP2.0
			"DOPOD", // 多普达手机
			"Lenovo", // 联想手机，Lenovo-P650WG/S100 LMP/LML Release/2010.02.22
						// Profile/MIDP2.0 Configuration/CLDC1.1
			"LENOVO", // 联想手机，比如：LENOVO-P780/176A
			"HUAQIN", // 华勤手机
			"AIGO-", // 爱国者居然也出过手机，AIGO-800C/2.04 TMSS-BROWSER/1.0.0 CTC/1.0
			"CTC/1.0", // 含义不明
			"CTC/2.0", // 含义不明
			"CMCC", // 移动定制手机，K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
					// Release/30.07.2008 Browser/WAP2.0
			"DAXIAN", // 大显手机DAXIAN X180 UP.Browser/6.2.3.2(GUI) MMP/2.0
			"MOT-", // 摩托罗拉，MOT-MOTOROKRE6/1.0 LinuxOS/2.4.20 Release/8.4.2006
					// Browser/Opera8.00 Profile/MIDP2.0 Configuration/CLDC1.1
					// Software/R533_G_11.10.54R
			"SonyEricsson", // 索爱手机，SonyEricssonP990i/R100 Mozilla/4.0
							// (compatible; MSIE 6.0; Symbian OS; 405) Opera
							// 8.65 [zh-CN]
			"GIONEE", // 金立手机
			"HTC", // HTC手机
			"ZTE", // 中兴手机，ZTE-A211/P109A2V1.0.0/WAP2.0 Profile
			"HUAWEI", // 华为手机，
			"webOS", // palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN)
						// AppleWebKit/532.2 (KHTML like Gecko) Version/1.0
						// Safari/532.2 Pre/1.0
			"GoBrowser", // 3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290
							// Safari
			"IEMobile", // Windows CE手机自带浏览器，
			"WAP2.0"// 支持wap 2.0的
	};
}
