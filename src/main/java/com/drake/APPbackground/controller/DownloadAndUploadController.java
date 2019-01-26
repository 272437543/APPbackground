package com.drake.APPbackground.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 上传下载控制器
 * 
 * @author xjn
 *
 */
@RequestMapping("/file")
@Controller("DownloadAndUploadController")
public class DownloadAndUploadController extends FatherController {
	private String basePath = "src/main/resources/static/";
	@RequestMapping("/index")
	public String index()
	{
		return "file/index";
	}
	
	@RequestMapping("/download")
	public void download(String filename, HttpServletRequest request,
			HttpServletResponse response) {
		if (filename == null)
			filename = basePath + "default.jpg";
		else filename = basePath + filename;
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("application/octet-stream");
		FileInputStream fis = null;
		try {
			File file = new File(filename);
			fis = new FileInputStream(file);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ file.getName());
			IOUtils.copy(fis, response.getOutputStream());
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFileAction(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("id") Long id) {
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("success");
        InputStream fis = null;
        OutputStream outputStream = null;
        try {
            fis = uploadFile.getInputStream();
            outputStream = new FileOutputStream(basePath+"uploadedFile/"+uploadFile.getOriginalFilename());
            IOUtils.copy(fis,outputStream);
            //modelAndView.addObject("sucess", "上传成功");
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //modelAndView.addObject("sucess", "上传失败!");
        return "fail";
    }

}
