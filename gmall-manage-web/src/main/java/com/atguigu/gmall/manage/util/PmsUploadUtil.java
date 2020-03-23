package com.atguigu.gmall.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.io.IOException;

public class PmsUploadUtil {


    public static String uploadImage(MultipartFile multipartFile) {

        //配置fdfs的全局连接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath(); //获得配置文件的路径
        //
        String url = "http://192.168.1.7";

        //初始化
        try {
            String path= URLDecoder.decode(tracker,"utf-8");
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();

        //获得一个trackerServer的实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //通过tracker获得一个Storage客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);
        //配置上传地址
        try {
            byte[] bytes = multipartFile.getBytes();//获得上传的二进制对象

            String originalFilename = multipartFile.getOriginalFilename();

            int lastIndexPoint = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(lastIndexPoint + 1);

            String[] jpgs = storageClient.upload_file(bytes, extName, null);


            for (String jpg : jpgs) {
                url += "/" + jpg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return url;
    }
}
