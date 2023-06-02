package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Extract;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
import ibf2022.batch2.csf.backend.utils.Utils;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
public class UploadController {

    @Autowired
    ImageRepository imgRepo;

    // TODO: Task 2, Task 3, Task 4
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postUpload(@RequestPart String content, @RequestPart MultipartFile archive) {
        System.out.println("Recieved:" + Utils.getJSONObj(content).toString() + "," + archive.getOriginalFilename());
        ArrayList<String> imgURLs;
        JsonObject created;
        // this should be in a service but i'm lazy
        try {

            ArrayList<Extract> filedata = Utils.unzipFile(archive);
            Map<String, String> bundledata = Utils.bundleGenerator(Utils.getJSONObj(content));
            imgURLs = imgRepo.uploadFiles(bundledata, filedata);
            System.out.println(imgURLs);
            created = Json.createObjectBuilder().add("bundleId", bundledata.get("bundleID")).build();

        } catch (IOException IOErr) {
            System.out.println(IOErr);
            return ResponseEntity.internalServerError().build();
        }
        // return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toString());
    }

    // TODO: Task 5

    // TODO: Task 6

}
