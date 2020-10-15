package top.kthirty.demo.oos.minio.utils;

import io.minio.MinioClient;


public class MinioUtils {
    private static MinioClient instance;
    public static MinioClient getInstance(){
        if(instance != null){
            return instance;
        }
        instance = MinioClient.builder().endpoint("http://192.168.126.128:9000/").credentials("minioadmin","minioadmin").build();
        return instance;
    }

    public static void makeBucket(String bucketName){
        MinioClient instance = getInstance();
        instance.make
    }

    public static void main(String[] args) {
        System.out.println("test");
    }

}
