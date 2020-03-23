package com.atguigu.gmall.manage;

import com.sun.demo.jvmti.hprof.Tracker;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URLDecoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {

        //配置fdfs的全局连接地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath(); //获得配置文件的路径
        String path= URLDecoder.decode(tracker,"utf-8");
        //初始化
        ClientGlobal.init(path);

        TrackerClient trackerClient = new TrackerClient();
        //获得一个trackerServer的实例
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        //通过tracker获得一个Storage客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);
        //配置上传地址
        String[] jpgs = storageClient.upload_file("F:/DownLoad Manager/Chrome Download/a.jpg", "jpg", null);
        //
        String url = "http://192.168.1.7";

        for (String jpg : jpgs) {
            url += "/" + jpg;
        }

        System.out.println(url);
    }

}
