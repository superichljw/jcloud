package com.project.jcloud.file;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface fileMapper {

    public void insertFileUpload(fileDto dto);

    public List<fileDto> selectFileList();

    public void updateFileCnt(int cnt);
}
