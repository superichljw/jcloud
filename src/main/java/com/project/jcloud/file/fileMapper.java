package com.project.jcloud.file;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface fileMapper {

    public void insertFileUpload_ljw(fileDto dto);

    public void insertFileUpload_lsw(fileDto dto);

    public List<fileDto> selectFileList_ljw();

    public List<fileDto> selectFileList_lsw();

    public void updateFileCnt_ljw(fileDto dto);

    public void updateFileCnt_lsw(fileDto dto);

    public int selectFileCnt_ljw();

    public int selectFileCnt_lsw();
}
