package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.board.domain.BoardPagingVo;
import com.board.domain.BoardVo;
import com.board.domain.PagingResponse;
import com.board.domain.SearchVo;
import com.board.menus.domain.MenuVo;

@Mapper
public interface BoardPagingMapper {

	int count(BoardVo boardVo);

	List<BoardPagingVo> getBoardPagingList(
			String menu_id, String title, String writer, int offset, int pageSize);


}
