package com.project.jcloud.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Slf4j
@Controller
public class fileController {
// 로컬 개발용
//    private static final String file_path = "/Users/jaewoolee/ljw_workspace/jcloud/src/main/resources/static/attaches";
    //서버용
    private static final String file_path = "/home/ljw/apache-tomcat-8.5.76/webapps/jcloud/WEB-INF/classes/static/attaches";
    private static final String img_path = "attaches";

    @Autowired
    fileService fileService;

    @RequestMapping(value="fileUpload.do" , method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam MultipartFile[] uploadfile, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();

        ModelAndView mv = new ModelAndView();

        String fileNewName = "";

        int cnt = Integer.parseInt(session.getAttribute("uploadFileCnt").toString());
        String dir = session.getAttribute("directory").toString();

        for(MultipartFile file : uploadfile){
            if(!file.isEmpty()){
                fileDto dto = new fileDto();

                cnt++;
                dto.setFileSeq(cnt);
                dto.setFileDir(file_path + dir);
                dto.setFileOriName(file.getOriginalFilename());
                dto.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length()));
                dto.setFileSize(String.valueOf(file.getSize()));


                fileNewName = "(" + cnt +")" + "_" + dto.getFileOriName();

                System.out.println("===========================");
                System.out.println(dto.getFileType());
                System.out.println(file.getOriginalFilename());
                System.out.println(file.getSize());
                System.out.println(dto.getFileSeq());
                System.out.println("===========================");

                dto.setFileNewName(fileNewName);
                dto.setImgPath(img_path + dir + dto.getFileNewName());

                fileService.insertFileUpload(dto);
                File newFileName = new File(dto.getFileDir(), fileNewName);
                file.transferTo(newFileName);

                fileService.updateFileCnt(dto);
            }
        }
        mv.setViewName("index");
        List<fileDto> list = new ArrayList<>();
        list = fileService.selectFileList();
        int fCnt = 0;
        fCnt = fileService.selectFileCnt();

        mv.addObject("files",list);
        mv.addObject("uploadFileCnt",fCnt);
        return mv;
    }

}
