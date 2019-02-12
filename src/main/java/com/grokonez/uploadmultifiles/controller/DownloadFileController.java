package com.grokonez.uploadmultifiles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.grokonez.uploadmultifiles.model.FileInfo;
import com.grokonez.uploadmultifiles.model.FileModel;
import com.grokonez.uploadmultifiles.repository.FileRepository;

@Controller
public class DownloadFileController {

	@Autowired
	FileRepository fileRepository;

	/*
	 * 检索文件的信息
	 */
	@GetMapping("/files")
	public String getListFiles(Model model) {
		List<FileInfo> fileInfos = fileRepository.findAll()
				.stream()
				.map(
						fileModel -> {
							String filename = fileModel.getName();
							String url = MvcUriComponentsBuilder.fromMethodName(DownloadFileController.class,
									"downloadFile", fileModel.getName()).build().toString();
							return new FileInfo(filename, url);
						}
				)
				.collect(Collectors.toList());

		model.addAttribute("files", fileInfos);
		return "listfiles";
	}

	/*
	 * 下载文件
	 */
	@GetMapping("/files/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
		FileModel file = fileRepository.findByName(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(file.getPic());
	}
}