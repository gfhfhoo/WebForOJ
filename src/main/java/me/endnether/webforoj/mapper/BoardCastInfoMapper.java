package me.endnether.webforoj.mapper;


import me.endnether.webforoj.entity.BoardCastInfo;

import java.util.List;
import java.util.Map;

public interface BoardCastInfoMapper {
    public List<Map<String, Object>> getAllBoardCastInfo();
    public int addSingleBoardCastInfo(BoardCastInfo info);
    public int addMultiBoardCastInfo(List<BoardCastInfo> list);
    public int delSingleBoardCastInfo(int infoId);
    public int addMultiBoardCastInfo(int[] list);
}
