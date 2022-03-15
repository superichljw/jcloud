package com.project.jcloud.login;


import com.project.jcloud.file.fileDto;
import com.project.jcloud.file.fileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class UserController {

//    private static final String file_path = "/Users/jaewoolee/ljw_workspace/jcloud/src/main/resources/static/attaches/";

    @Autowired
    userService userService;

    @Autowired
    fileService fileService;

    @RequestMapping(value="/")
    public String index(){
        return "login";
    }

    @RequestMapping(value="index.do")
    public ModelAndView main_page(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();

        int cnt = Integer.parseInt(session.getAttribute("uploadFileCnt").toString());
        String user = session.getAttribute("user").toString();

        List<fileDto> list = new ArrayList<>();

        if(user.equals("ljw")){
            cnt = fileService.selectFileCnt_ljw();
            list = fileService.selectFileList_ljw();
        }else if(user.equals("lsw")){
            cnt = fileService.selectFileCnt_lsw();
            list = fileService.selectFileList_lsw();
        }
        mv.addObject("files",list);
        mv.addObject("uploadFileCnt",cnt);

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value="login.do" , method = RequestMethod.POST)
    public ModelAndView loginCheck(@ModelAttribute userDto vo, HttpServletRequest request) throws Exception {
        userDto loginResult = userService.loginCheck(vo);
//        System.out.println(userService.loginCheck(vo).getUserId());
        log.debug(vo.getUserId());
        log.debug(vo.getUserPw());
        ModelAndView mv = new ModelAndView();

        if(loginResult.getUserId()!= "" || loginResult != null){

            HttpSession session = request.getSession();
            session.setAttribute("msg","success!");
            session.setAttribute("name",loginResult.getUserName());
            session.setAttribute("directory",loginResult.getUserDir());
            session.setAttribute("wifi",loginResult.getConfWifi());
            session.setAttribute("user",loginResult.getUserId());
            session.setAttribute("uploadFileCnt",loginResult.getUploadFileCnt());

            mv.setViewName("redirect:index.do");
        }else{
            mv.setViewName("redirect:index.do");
            mv.addObject("msg","failure");
        }
        return mv;
    }
}
