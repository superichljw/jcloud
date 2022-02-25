package com.project.jcloud.file;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void updateFileCnt(int cnt) throws Exception{
        fileMapper.updateFileCnt(cnt);
    }
}
