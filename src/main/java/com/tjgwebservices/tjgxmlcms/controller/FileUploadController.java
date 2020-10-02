package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.model.FileUpload;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext context;
    
    @RequestMapping(value = "/uploads", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileUpload file = new FileUpload();
        ModelAndView modelAndView = new ModelAndView("uploads","command",file);
        return modelAndView;
    }
    
    @RequestMapping(value="/uploads", method = RequestMethod.POST)
    public String uploadFile(@Validated FileUpload file, BindingResult result,
            ModelMap model) throws IOException {
        if (result.hasErrors()){
            System.out.println("validation errors");
            return "uploads";            
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = file.getFile();
            String uploadPath = context.getRealPath("") + File.separator +
                    "temp" + File.separator;
            FileCopyUtils.copy(file.getFile().getBytes(),
                    new File(uploadPath+file.getFile().getOriginalFilename()
                    ));
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName",fileName);
            return "success";
            
        }
        
    }
}
