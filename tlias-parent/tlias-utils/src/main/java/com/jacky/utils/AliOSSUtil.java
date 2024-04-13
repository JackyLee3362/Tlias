package com.jacky.utils;

import com.aliyun.oss.*;
// import com.aliyun.oss.common.auth.CredentialsProviderFactory;
// import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtil {


    @Autowired
    private AliOSSProperties aliOSSProperties;


    
    @SuppressWarnings("null")
    public String upload(MultipartFile file) throws Exception {


        String endpoint = aliOSSProperties.getEndpoint();
        String accessKey = aliOSSProperties.getAccessKey();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessKeySecret);
        String url = null;
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            int index = fileName.lastIndexOf('.');
            String extName = fileName.substring(index);
            String objectName = UUID.randomUUID() + extName;
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            System.out.println(result);
            // 拼接返回结果
            url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}
