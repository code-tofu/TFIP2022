package ibf2022.batch2.csf.backend.repositories;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import ibf2022.batch2.csf.backend.models.Extract;

@Repository
public class ImageRepository {

    public static final String type = "image";
    public static final String bucket = "tofu-test";

    @Autowired
    private AmazonS3 s3;

    public ArrayList<String> uploadFiles(Map<String, String> bundleData, ArrayList<Extract> files) throws IOException {
        ArrayList<String> imgURLs = new ArrayList<>();
        Map<String, String> userData = new HashMap<>();
        userData.put("title", bundleData.get("title"));
        userData.put("name", bundleData.get("name"));
        userData.put("uploadDate", bundleData.get("uploadDate"));
        userData.put("bundleID", bundleData.get("bundleID"));

        for (Extract file : files) {
            imgURLs.add(uploadFile(userData, file.getFiledata(), file.getFilename(), file.getFiletype(),
                    bundleData.get("bundleID")));
        }
        return imgURLs;
    }

    // TODO: Task 3
    public String uploadFile(Map<String, String> userData, byte[] file, String fileName, String fileType,
            String dirName) throws IOException {

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(type + fileType);
        metadata.setContentLength(file.length);
        metadata.setUserMetadata(userData);

        // UUID filename
        String key = dirName + "/" + UUID.randomUUID().toString().substring(0, 8);

        PutObjectRequest putReq = new PutObjectRequest(bucket, key,
                new ByteArrayInputStream(file), metadata);
        // Make the file publically accessible
        putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult result = s3.putObject(putReq);
        System.out.printf("Uploaded:" + result.toString());

        return s3.getUrl(bucket, key).toString();
    }
}
