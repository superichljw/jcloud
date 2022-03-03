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
    public void insertFileUpload(fileDto dto) throws Exception{
        log.debug("insertFileUpload START");
        fileMapper.insertFileUpload(dto);
        log.debug("insertFileUpload END");
    }

    @Override
    public List<fileDto> selectFileList() throws Exception{
        List<fileDto> list = new ArrayList<>();
        list = fileMapper.selectFileList();
        return list;
    }

    @Override
    public void updateFileCnt(fileDto dto) throws Exception{
        fileMapper.updateFileCnt(dto);
    }

    @Override
    public int selectFileCnt() throws Exception{
        int cnt = 0;
        cnt = fileMapper.selectFileCnt();
        return cnt;
    }
}
