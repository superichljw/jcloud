package com.project.jcloud.file;

import java.util.List;

public interface fileService {

    public void insertFileUpload(fileDto dto) throws Exception;

    public List<fileDto> selectFileList() throws Exception;
}
