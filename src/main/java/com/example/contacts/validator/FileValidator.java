package com.example.contacts.validator;

import com.example.contacts.model.FileUpload;
import com.example.contacts.utils.CommonUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class FileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FileUpload.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FileUpload fileUpload = (FileUpload) target;

        CommonsMultipartFile[] multipartFiles = fileUpload.getFiles();

        for(CommonsMultipartFile multipartFile: multipartFiles) {
            //check file size
            if(multipartFile.getSize() == 0) {
                errors.rejectValue("files", "required.file");
                break;
            }
            //check file extension
            //I accept also .txt files
            String fileExtension = CommonUtils.getFileExtension(multipartFile.getOriginalFilename().toLowerCase());
            if(!fileExtension.equals("xml")&& !fileExtension.equals("csv") && !fileExtension.equals("txt")) {
                errors.rejectValue("files", "file.extension.allowed");
                break;
            }
        }
    }
}
