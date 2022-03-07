package com.project.jcloud.file;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("fileUploadService")
public class fileServiceImpl implements fileService{

    @Autowired
    fileMapper fileMapper;

    @Override
    public void insertFileUpload_ljw(fileDto dto) throws Exception{
        log.debug("insertFileUpload START");
        fileMapper.insertFileUpload_ljw(dto);
        log.debug("insertFileUpload END");
    }

    @Override
    public void insertFileUpload_lsw(fileDto dto) throws Exception{
        log.debug("insertFileUpload START");
        fileMapper.insertFileUpload_lsw(dto);
        log.debug("insertFileUpload END");
    }

    @Override
    public List<fileDto> selectFileList_ljw() throws Exception{
        List<fileDto> list = new ArrayList<>();
        list = fileMapper.selectFileList_ljw();
        return list;
    }

    @Override
    public List<fileDto> selectFileList_lsw() throws Exception{
        List<fileDto> list = new ArrayList<>();
        list = fileMapper.selectFileList_lsw();
        return list;
    }

    @Override
    public void updateFileCnt_ljw(fileDto dto) throws Exception{
        fileMapper.updateFileCnt_ljw(dto);
    }

    @Override
    public void updateFileCnt_lsw(fileDto dto) throws Exception{
        fileMapper.updateFileCnt_lsw(dto);
    }

    @Override
    public int selectFileCnt_ljw() throws Exception{
        int cnt = 0;
        cnt = fileMapper.selectFileCnt_ljw();
        return cnt;
    }

    @Override
    public int selectFileCnt_lsw() throws Exception{
        int cnt = 0;
        cnt = fileMapper.selectFileCnt_lsw();
        return cnt;
    }
}
