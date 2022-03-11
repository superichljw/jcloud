package com.project.jcloud.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
//    private static final String file_path = "/home/ljw/apache-tomcat-8.5.76/webapps/jcloud/WEB-INF/classes/static/attaches";

//        private static final String file_path = "/home/ljw/jcloud/attaches";
    private static final String img_path = "/home/ljw/jcloud/attaches";

    @Autowired
    fileService fileService;

    @Value("${file.path}")
    private String file_path;

    @RequestMapping(value="fileUpload.do" , method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam MultipartFile[] uploadfile, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();

        ModelAndView mv = new ModelAndView();

        String fileNewName = "";

        int cnt = Integer.parseInt(session.getAttribute("uploadFileCnt").toString());
        String dir = session.getAttribute("directory").toString();
        String user = session.getAttribute("user").toString();


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
                dto.setImgPath(dto.getFileDir()+ dto.getFileNewName());

                if(user.equals("ljw")){
                    fileService.insertFileUpload_ljw(dto);
                }else if(user.equals("lsw")){
                    fileService.insertFileUpload_lsw(dto);
                }

                File newFileName = new File(dto.getFileDir(), fileNewName);
                file.transferTo(newFileName);



                if(user.equals("ljw")){
                    fileService.updateFileCnt_ljw(dto);
                }else if(user.equals("lsw")){
                    fileService.updateFileCnt_lsw(dto);
                }

            }
        }
        mv.setViewName("index");
        List<fileDto> list = new ArrayList<>();
        int fCnt = 0;

        if(user.equals("ljw")){
            list = fileService.selectFileList_ljw();
            fCnt = fileService.selectFileCnt_ljw();
        }else if(user.equals("lsw")){
            list = fileService.selectFileList_lsw();
            fCnt = fileService.selectFileCnt_lsw();
        }


        mv.addObject("files",list);
        mv.addObject("uploadFileCnt",fCnt);
        return mv;
    }

}
