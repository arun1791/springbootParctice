package com.sla.excel.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sla.excel.model.ExcelResponseDTO;
import com.sla.excel.model.QuaterReportDTO;
import com.sla.excel.utils.ExcelUtils;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class ExcelController {

	@Autowired
	private ExcelUtils excelUtils;
	@Autowired
	ResourceLoader resourceLoader;
	
//	@Autowired
//	HttpServletRequest httpRequest;
//	
//	@Value("${serverfilePath}")
//	private String file;
	// get file path loaction 
//	@GetMapping("/data")
//	public ResponseEntity<List<ExcelResponse>>  readCSV(HttpServletRequest request) throws URISyntaxException{
//		return ResponseEntity.ok(excelUtils.readCSV());
//	}
	@CrossOrigin(origins = "*")
	@PostMapping("/data")
	public ResponseEntity<List<ExcelResponseDTO>>  readCSV(HttpServletRequest request ,@RequestBody QuaterReportDTO quatorReportDTO) throws URISyntaxException{
		return ResponseEntity.ok(excelUtils.readCSV(quatorReportDTO));
	}
	//as a pass reqest param file 
	@GetMapping("/excel")
	public ResponseEntity<List<ExcelResponseDTO>>  readExcel(@RequestParam("file") MultipartFile file) throws IOException, ParseException{
		return null;
		//return ResponseEntity.ok(excelUtils.readFile(file));
	}
	//get file from resources folder
	@GetMapping("/excelFromResources")
	public ResponseEntity<List<ExcelResponseDTO>>  readExcelFromResoures() throws IOException, ParseException{
		return null;
		//return ResponseEntity.ok(excelUtils.readFilefromResources());
		
			
	}
}
