package com.qc.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

	public static final String UTF_8 = "UTF-8";
	
	public static String readFile(String filePath) {
		
		return readFile(new File(filePath));
	}

	public static String readFile(File file) {
		
		if (file.isFile() && file.exists()) {
			
			StringBuilder sb = new StringBuilder();

			try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), UTF_8);
					BufferedReader reader = new BufferedReader(read);) {

				String line;
				while ((line = reader.readLine()) != null) {

					sb.append(line).append("\n");
				}
				
				return sb.toString();
			} catch (Exception e) {//
			}
		}
		return StringUtils.EMPTY;
	}

	@SuppressWarnings("rawtypes")
	public static List readFolder(List<Map<String, String>> list, String folderPath) {
		if (list == null) {
			return null;
		}
		File file = new File(folderPath);
		File[] tempList = file.listFiles();
		if (tempList != null && tempList.length > 0) {
			for (File tmpFile : tempList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", file.getName());
				map.put("path", file.getPath());
				list.add(map);
				if (tmpFile.isDirectory()) {
					readFolder(list, tmpFile.getPath()); // 閫掑綊鍒犻櫎
				}
			}
		}
		return list;
	}

	public static void writeFile(String content, String writePath, String charCoder) {
		try {
			File file = new File(writePath);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), charCoder);
			BufferedWriter reader = new BufferedWriter(osw);
			reader.write(content);
			osw.flush();
			reader.close();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void delFolders(String folderPath) {
		// 鍒犻櫎瀹岄噷闈㈡墍鏈夊唴瀹�
		File file = new File(folderPath);
		// 濡傛灉璺緞鏈韩灏辨槸涓�涓枃浠跺氨鐩存帴鍒犻櫎
		if (!file.isDirectory()) {
			file.delete();
			return;
		}
		// 妫�鏌ユ枃浠跺す閲岄潰鏄惁瀛樺湪鏂囦欢澶�
		File[] tempList = file.listFiles();
		if (tempList != null && tempList.length > 0) {
			for (File tmpFile : tempList) {
				if (tmpFile.isDirectory()) {
					delFolders(tmpFile.getPath()); // 閫掑綊鍒犻櫎
				} else {
					tmpFile.delete();
				}
			}
		} else {
			file.delete();
		}
		delFolders(file.getPath());
	}

	public static void delFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	public static void writeTmpFile(String fileName, String content) {
		// 鑾峰彇绋嬪簭褰撳墠璺緞
		String strDir = System.getProperty("controller.dir");
		// 灏嗚矾寰勫垎闅旂鏇存崲
		String folderpath = strDir;// .replace("/", File.separatorChar);
		String filepath = folderpath + File.separatorChar + fileName + ".tmp";
		FileUtil.writeFile(content, filepath, "utf-8");
	}

	public static String readTmpFile(String fileName) {
		// 鑾峰彇绋嬪簭褰撳墠璺緞
		String strDir = System.getProperty("controller.dir");
		// 灏嗚矾寰勫垎闅旂鏇存崲
		String folderpath = strDir;// .replace("/", File.separatorChar);
		String filepath = folderpath + File.separatorChar + fileName + ".tmp";
		return FileUtil.readFile(filepath);
	}
}