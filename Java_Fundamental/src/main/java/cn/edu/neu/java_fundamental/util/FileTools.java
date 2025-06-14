package cn.edu.neu.java_fundamental.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileTools {
    private static final String PATH="data";
    public static int writeStringToFile(String fileName, String content) throws IOException {
        File targetfile = new File( PATH,fileName);
        File parentDir = targetfile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (targetfile.exists()) {
            targetfile.delete();
        }
        targetfile.createNewFile();
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(targetfile)) {
            byte[] bs = content.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            fos.write(bs);
            fos.flush();
            return content.length();
        } catch (IOException e) {
            System.out.println("文件写入失败: " + e.getMessage());
            return -1;
        }
    }
    public static String readStringFromFile(String fileName) throws IOException {
        File targetfile = new File(PATH, fileName);
        if (!targetfile.exists()) {
            throw new IOException("文件不存在: " + fileName);
        }
        try (java.io.FileInputStream fis = new java.io.FileInputStream(targetfile)) {
            byte[] bs = new byte[fis.available()];
            fis.read(bs);
            return new String(bs, Charset.forName("utf8"));
        } catch (IOException e) {
            System.out.println("文件读取失败: " + e.getMessage());
            return null;
        }
    }
}
