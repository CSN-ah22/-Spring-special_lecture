package com.dealim.univ.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.*;

import org.apache.ibatis.session.SqlSession;

import com.dealim.univ.domain.Board;

public class BoardMapperImpl implements BoardMapper {
	
	@Inject
	SqlSession SqlSession;

	@Override
	public void create(Board board) throws Exception {
		
	}

	@Override
	public Board read(Integer boardNo) throws Exception {
		return null;
	}

	@Override
	public void update(Board board) throws Exception {
		
	}

	@Override
	public void delete(Integer boardNo) throws Exception {
		
	}

	@Override
	public List<Board> list(String searchOption, String keyword) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return SqlSession.selectList("board.list", map);
	}

	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return SqlSession.selectOne("board.countArticle",map);
	}

}
