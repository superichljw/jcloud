package com.project.jcloud.login;

import com.project.jcloud.util.AES256;
import com.project.jcloud.file.fileDto;
import com.project.jcloud.file.fileService;
import com.project.jcloud.util.AES256;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Value("${aes.algorithm}")
    private String algo;

    @Value("${aes.key}")
    private String key;

    @Value("${aes.iv}")
    private String iv;

    @Autowired
    userService userService;

    @Autowired
    fileService fileService;

    @RequestMapping(value="/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value="index.do")
    public ModelAndView main_page(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("user")==null){
            mv.setViewName("redirect:/");
            return mv;
        }

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
    public ModelAndView loginCheck(@ModelAttribute userDto vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*암호화 적용 패스워드*/
        AES256 aes = new AES256();
        String pw = aes.encrypt(algo,key,iv,vo.getUserPw());
        vo.setUserPw(pw);

        userDto loginResult = userService.loginCheck(vo);

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
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write("<script type='text/javascript'>alert('조회된 정보가 없습니다.'); self.close();</script>");
            out.flush();

            mv.setViewName("redirect:/");
        }
        return mv;
    }
    @RequestMapping(value = "logout.do" , method = RequestMethod.POST)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;
    }
}
