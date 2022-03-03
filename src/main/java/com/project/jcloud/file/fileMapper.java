package com.project.jcloud.file;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface fileMapper {

    public void insertFileUpload(fileDto dto);

    public List<fileDto> selectFileList();

    public void updateFileCnt(fileDto dto);

    public int selectFileCnt();
}
