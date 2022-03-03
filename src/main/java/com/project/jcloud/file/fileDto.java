package com.project.jcloud.file;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class fileDto {
    private int fileSeq;
    private String fileOriName;
    private String fileNewName;
    private String fileDir;
    private String fileType;
    private String fileSize;
    private String imgPath;
}
