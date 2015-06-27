package com.jyn.demo.fileupload.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope(value="prototype")
@RequestMapping("/fileupload")
public class FileuploadController {
	
	@Value("${app}")
	private String app;
	
	@Value("${viewsuffix}")
	private String viewsuffix;
	
	@Value("${uploadDirName}")
	private String uploadDirName;
	
	@RequestMapping(value="", method={RequestMethod.GET})
	public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) throws FileNotFoundException, IOException{
		return new ModelAndView("uploadfile/index");
	}
	
	@RequestMapping(value="upload", method={RequestMethod.POST})
	public ModelAndView upload(HttpServletRequest request,
            HttpServletResponse response){
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
		MultipartFile f = mRequest.getFile("file");
		FileOutputStream fos = null;
		ModelAndView mav = new ModelAndView("common/result");
		String real_path = request.getSession().getServletContext().getRealPath("/");
		String localFileName = real_path + "/" + uploadDirName + "/" + UUID.randomUUID();
		try{
			File localFile = new File(localFileName);
			fos = new FileOutputStream(localFile);
			fos.write(f.getBytes());
			mav.addObject("result", "文件上传成功：SUCCESS!");
			return mav;
		}catch(FileNotFoundException e){
			mav.addObject("result", "FileNotFound(" + e.getMessage() + "):" + localFileName);
			return mav;
		}catch(IOException e){
			mav.addObject("result", "文件写入有异常（" + e.getMessage() + "):" + localFileName);
			return mav;
		}finally{
			if(fos != null){
				try{
					fos.close();
				}catch(IOException e){
					
				}
			}
		}
	}
	
}