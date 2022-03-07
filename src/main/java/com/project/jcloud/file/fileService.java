package com.project.jcloud.file;

import java.util.List;
import java.util.Map;

public interface fileService {

    public void insertFileUpload_ljw(fileDto dto) throws Exception;

    public void insertFileUpload_lsw(fileDto dto) throws Exception;

    public List<fileDto> selectFileList_ljw() throws Exception;

    public List<fileDto> selectFileList_lsw() throws Exception;

    public void updateFileCnt_ljw(fileDto dto) throws Exception;

    public void updateFileCnt_lsw(fileDto dto) throws Exception;

    public int selectFileCnt_ljw() throws Exception;

    public int selectFileCnt_lsw() throws Exception;
}
