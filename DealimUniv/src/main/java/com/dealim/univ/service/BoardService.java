package com.dealim.univ.service;

import java.util.List;

import com.dealim.univ.domain.Board;

public interface BoardService {
   
   public void register(Board board) throws Exception;
   
   public Board read(Integer boardNo) throws Exception;
   
   public void modify(Board board) throws Exception;
   
   public void remove(Integer boardNo) throws Exception;
   
   public List<Board> list(String searchOption, String keyword) throws Exception;
   
   public int countArticle(String searchOption, String keyword) throws Exception;
}