package com.dealim.univ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealim.univ.domain.Board;
import com.dealim.univ.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
   
   @Autowired
   BoardMapper boardMapper;

   @Override
   public void register(Board board) throws Exception {
      boardMapper.create(board);
   }

   @Override
   public Board read(Integer boardNo) throws Exception {
      return boardMapper.read(boardNo);
   }

   @Override
   public void modify(Board board) throws Exception {
      boardMapper.update(board);
   }

   @Override
   public void remove(Integer boardNo) throws Exception {
      boardMapper.delete(boardNo);
   }

   @Override
   public List<Board> list(String searchOption, String keyword) throws Exception {
      return boardMapper.list(searchOption, keyword);
   }
   // 게시글 레코드 갯수
   @Override
   public int countArticle(String searchOption, String keyword) throws Exception {
	      return boardMapper.countArticle(searchOption, keyword);
	   }
}