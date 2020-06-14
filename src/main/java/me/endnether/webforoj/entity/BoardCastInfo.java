package me.endnether.webforoj.entity;

import me.endnether.webforoj.mapper.BoardCastInfoMapper;

import java.util.Date;

public class BoardCastInfo {
    private int infoId;
    private String title;
    private String content;
    private Date date;
    private String publisher;
    private BoardCastInfoMapper boardCastInfoMapper;

    public BoardCastInfo(String title, String content, Date date, String publisher) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.publisher = publisher;
    }

    public BoardCastInfo(int infoId, BoardCastInfoMapper boardCastInfoMapper) {
        this.infoId = infoId;
        this.boardCastInfoMapper = boardCastInfoMapper;
    }

    public void getInfo() {
        if (infoId == 0) return;
    }

    public BoardCastInfo() {

    }
}
