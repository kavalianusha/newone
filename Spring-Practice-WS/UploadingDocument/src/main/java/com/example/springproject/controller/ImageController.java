package com.example.springproject.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springproject.result.Result;

@RestController
public class ImageController {
	
@PostMapping("/upload-files")
public Result uploadImage(@RequestParam("file") MultipartFile file)throws Exception {
	
	
	System.out.println(file.getName());
	System.out.println(file.getOriginalFilename());
	System.out.println(file.getContentType());
	System.out.println(file.getSize());

    String Path_Directory = "D:\\Spring-Practice-WS\\UploadingDocument\\src\\main\\resources\\static\\images";
	//String Path_Directory = new ClassPathResource("static/images/").getFile().getAbsolutePath();
	Files.copy(file.getInputStream(), Paths.get(Path_Directory+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
	Result result = new Result();
	result.setResult("Successfully Image uploaded");
		return result;
	}
	

}
