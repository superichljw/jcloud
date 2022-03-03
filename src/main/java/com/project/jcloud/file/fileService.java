package com.project.jcloud.file;

import java.util.List;
import java.util.Map;

public interface fileService {

    public void insertFileUpload(fileDto dto) throws Exception;

    public List<fileDto> selectFileList() throws Exception;

    public void updateFileCnt(fileDto dto) throws Exception;

    public int selectFileCnt() throws Exception;
}
