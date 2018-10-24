package com.example.contacts.controller;

import com.example.contacts.model.FileUpload;
import com.example.contacts.service.CustomerService;
import com.example.contacts.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    FileValidator fileValidator;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "uploadPage", method = RequestMethod.GET)
    public ModelAndView uploadPage() {
        ModelAndView model = new ModelAndView("upload_page");
        FileUpload formUpload = new FileUpload();
        model.addObject("formUpload", formUpload);

        return model;
    }

    @RequestMapping(value="/doUpload", method=RequestMethod.POST)
    public String doUpload(@ModelAttribute("formUpload") FileUpload fileUpload, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, JAXBException {
        //validate
        fileValidator.validate(fileUpload, result);

        if(result.hasErrors()) {
            return "upload_page";
        } else {
            //doUpload
            redirectAttributes.addFlashAttribute("fileNames", uploadAndImportDb(fileUpload));
            return "redirect:/success";
        }
    }

    @RequestMapping(value="/success", method=RequestMethod.GET)
    public ModelAndView success() {
        ModelAndView model = new ModelAndView("success");

        return model;
    }

    private List<String> uploadAndImportDb(FileUpload fileUpload) throws IOException, JAXBException {
        List <String> fileNames = new ArrayList<String>();
        List <String> paths = new ArrayList<String>();

        CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

        String filePath = null;

        for(CommonsMultipartFile multipartFile : commonsMultipartFiles) {
            filePath = "C:\\Contacts\\" + multipartFile.getOriginalFilename();
            File file = new File(filePath);
            //copy files
            FileCopyUtils.copy(multipartFile.getBytes(), file);
            fileNames.add(multipartFile.getOriginalFilename());

            paths.add(filePath);
        }

        //process parse and import data
        customerService.process(paths);

        return fileNames;
    }
}
