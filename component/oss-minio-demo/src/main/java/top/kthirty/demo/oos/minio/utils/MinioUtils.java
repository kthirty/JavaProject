package top.kthirty.demo.oos.minio.utils;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class MinioUtils {
    private static MinioClient instance;
    public static MinioClient getInstance(){
        if(instance != null){
            return instance;
        }
        instance = MinioClient.builder().endpoint("http://192.168.126.128:9000/").credentials("minioadmin","minioadmin").build();
        return instance;
    }

    /**
     * 如果桶存在则不做任何操作
     */
    public static void makeBucket(String bucketName) throws Exception{
        MinioClient instance = getInstance();
        if (!instance.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            instance.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
    /**
     * 仅桶为空时才能删除成功
     */
    public static void removeBucket(String bucketName) throws Exception{
        MinioClient instance = getInstance();
        if (instance.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            instance.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public static void makeDir(String bucket,String path){

    }

    public static void upload(String bucket,String objectName, String fileName) throws Exception{
        instance.uploadObject(UploadObjectArgs.builder().
                bucket(bucket)
                .object(objectName)
                .filename(fileName).build());
    }
    public static void upload(String bucket,String objectName, InputStream inputStream,long objectSize,long partSize,String contentType) throws Exception{
        instance.putObject(PutObjectArgs.builder().bucket(bucket).object(objectName).stream(inputStream,objectSize,partSize).contentType(contentType).build());
    }

    public static void remove(String bucket,String object) throws Exception{
        instance.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(object).build());
    }

    public static String presignedGetObject(String bucket,String object) throws Exception{
        return instance.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket).method(Method.GET).object(object).build());
    }

    public static void main(String[] args) throws Exception {
        getInstance();

//        makeBucket("test1");
//        makeBucket("test2");
//        removeBucket("test2");
//        upload("test1","img/20201016/lufei.gif","C:\\Users\\G006648\\Pictures\\lufei.gif");
//        removeBucket("test1");
//        File file = new File("C:\\\\Users\\\\G006648\\\\Pictures\\\\lufei.gif");
//        InputStream out = new FileInputStream(file);
//        upload("test1","img/20201019/lufei.gif",out,file.length(),1024L*10*1024,"image/gif");
//        remove("test1","img/20201019/lufei.gif");
        String test1 = presignedGetObject("test1", "img/20201019/lufei.gif");
        System.out.println(test1);
    }

}
