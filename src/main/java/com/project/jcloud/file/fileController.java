package com.project.jcloud.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
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

    @RequestMapping(value = "fileDownload.do")
    public void download(HttpServletResponse response, @RequestParam Map<String, Object> paramMap) throws Exception{
        String path = "";
        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("tistory.png", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

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
                dto.setImgPath(file_path + dir + dto.getFileNewName());

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

        mv.setViewName("redirect:index.do");
        return mv;
    }

    @RequestMapping(value="getImg.do" , method=RequestMethod.GET)
    public void getImg(
            @RequestParam(value="imgPath") String imgPath,
            HttpServletResponse response) throws Exception{

        String filePath = imgPath;
        log.debug("IMAGE PATH ::: " + imgPath);
        getImage(filePath,response);
    }

    public void getImage(String imgPath, HttpServletResponse response) throws Exception{

        File file = new File(imgPath);
        if(!file.isFile()){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write("<script type='text/javascript'>alert('조회된 정보가 없습니다.'); self.close();</script>");
            out.flush();
            return;
        }

        FileInputStream fis = null;
        new FileInputStream(file);

        BufferedInputStream in = null;
        ByteArrayOutputStream bStream = null;
        try {
            fis = new FileInputStream(file);
            in = new BufferedInputStream(fis);
            bStream = new ByteArrayOutputStream();
            int imgByte;
            while ((imgByte = in.read()) != -1) {
                bStream.write(imgByte);
            }

            String type = "";
            String ext = FilenameUtils.getExtension(file.getName());

            System.out.println("EXT ::: " + ext);
            log.debug("EXT ::: " + ext);
            if (ext != null && !"".equals(ext)) {
                if ("jpg".equals(ext.toLowerCase())) {
                    type = "image/jpeg";
                } else {
                    type = "image/" + ext.toLowerCase();
                }
                System.out.println("TYPE ::: " + type);
                log.debug("TYPE ::: " + type);
            } else {
                log.debug("Image fileType is null.");
            }

            response.setHeader("Content-Type", type);
            response.setContentLength(bStream.size());

            bStream.writeTo(response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Exception e) {
            log.debug("{}", e);
        } finally {
            if (bStream != null) {
                try {
                    bStream.close();
                } catch (Exception est) {
                    log.debug("IGNORED: {}", est.getMessage());
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ei) {
                    log.debug("IGNORED: {}", ei.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception efis) {
                    log.debug("IGNORED: {}", efis.getMessage());
                }
            }
        }
    }

}
