package ibf2022.batch2.csf.backend.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Extract;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

    public static JsonObject getJSONObj(String jsonString) {
        StringReader sr = new StringReader(jsonString.toString());
        JsonReader jsr = Json.createReader(sr);
        return (JsonObject) jsr.read();
    }

    public static Map<String, String> bundleGenerator(JsonObject contentJson) {
        Map<String, String> bundleData = new HashMap<>();
        bundleData.put("bundleID", UUID.randomUUID().toString().substring(0, 8));
        bundleData.put("uploadDate", (new Date()).toString());
        bundleData.put("name", contentJson.getString("name"));
        bundleData.put("title", contentJson.getString("title"));
        bundleData.put("comments", contentJson.getString("comments"));
        System.out.println("Generated Bundle:" + bundleData);
        return bundleData;
    }

    // can use lastIndexOf and substring but i make life complicated and i started
    // on this so i can't turn back now
    public static String[] getNameType(String originalName) {
        char[] chars = originalName.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (Character.compare(chars[i], '.') == 0) {
                chars[i] = '!'; // since files cannot have !
                break;
            }
        }
        return String.valueOf(chars).split("!", 2);
    }

    private static final int BUFFER_SIZE = 4096;
    private static final String PATH_ROOT = "C:/Users/ftc_b/GIT/TFIP-CSF/CSFR02/photoapp/data";

    public static ArrayList<Extract> unzipFile(MultipartFile archive) throws IOException {
        ArrayList<Extract> files = new ArrayList<>();
        ZipInputStream zipIS = new ZipInputStream(archive.getInputStream());
        ZipEntry entry = zipIS.getNextEntry();

        while (entry != null) {
            System.out.println("Name:" + entry.getName()
                    + "|Bytes:" + entry.getSize());

            // // writing to local
            // writeFile(zipIS, PATH_ROOT + entry.getName());

            // reading to arraylist
            String[] filedata = getNameType(entry.getName());
            byte[] newFile = new byte[(int) entry.getSize()];
            zipIS.read(newFile, 0, (int) entry.getSize());
            Extract newExtract = new Extract(newFile, filedata[0], filedata[1]);
            files.add(newExtract);
            System.out.println(newExtract);
            zipIS.closeEntry();
            entry = zipIS.getNextEntry();
        }
        System.out.println(files.size() + "files Extracted");
        zipIS.close();
        return files;
    }

    private static void writeFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        System.out.print("Read:" + read + "|Write To:" + filePath);
        bos.close();
    }

}