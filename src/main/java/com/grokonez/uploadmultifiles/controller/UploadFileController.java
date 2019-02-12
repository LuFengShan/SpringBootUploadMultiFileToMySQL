package com.grokonez.uploadmultifiles.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grokonez.uploadmultifiles.model.FileModel;
import com.grokonez.uploadmultifiles.repository.FileRepository;

@Controller
public class UploadFileController {
	
	@Autowired
	FileRepository fileRepository;
	
    @GetMapping("/")
    public String index() {
        return "uploadform";
    }
    
    @PostMapping("/")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
    	List<String> fileNames = new ArrayList<String>();
    	
		try {
			List<FileModel> storedFile = new ArrayList<>();
			
			for(MultipartFile file: files) {
				FileModel fileModel = fileRepository.findByName(file.getOriginalFilename());
				if(fileModel != null) {
					// update new contents
					fileModel.setPic(file.getBytes());
				}else {
					fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
				}
				
				fileNames.add(file.getOriginalFilename());				
				storedFile.add(fileModel);
			}
			
			// Save all Files to database
	    	fileRepository.saveAll(storedFile);
	    	
			model.addAttribute("message", "文件上传成功!");
			model.addAttribute("files", fileNames);
		} catch (Exception e) {
			model.addAttribute("message", "文件上传失败!");
			model.addAttribute("files", fileNames);
		}
		
        return "uploadform";
    }
}
