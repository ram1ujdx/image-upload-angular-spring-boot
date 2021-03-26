package com.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController 
{
	private String uploadLocation = "src/main/resources/static/images";
	
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
    
	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
		if (file == null) {
			throw new RuntimeException("No File Upladed...");
		}
		
		String originalName = file.getOriginalFilename();
		String name = file.getName();
//		byte[] bytes = file.getBytes();
	
		Path path = Paths.get(uploadLocation);
         
	        if (!Files.exists(path)) {
	            Files.createDirectories(path);
	        }
	         
	        try {
	        	InputStream inputStream = file.getInputStream();
	        
	            Path filePath = path.resolve(originalName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	            System.err.println(filePath);
	           
	        } catch (IOException e) {        
	           e.printStackTrace();
	        } 
	        
		

		String contentType = file.getContentType();
		long size = file.getSize();
		logger.info("original-name: " + originalName);
		logger.info("name: " + name);
		logger.info(" File Type: " + contentType);
		logger.info("size: " + size);
		
		//////
		
		
		
		
		return "upload-success.jsp";
	}
	
	@GetMapping("/image")
	public String displayImage(Model m) {
		m.addAttribute("image", "images/final-bg.png");
		return "show-image.jsp";
	}
	
	
	
	}
    
     
