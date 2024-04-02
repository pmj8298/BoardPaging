package com.board.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardPagingVo;
import com.board.domain.BoardVo;
import com.board.domain.Pagination;
import com.board.domain.PagingResponse;
import com.board.domain.SearchVo;
import com.board.mapper.BoardPagingMapper;
import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private   MenuMapper          menuMapper;
	
	@Autowired
	private   BoardPagingMapper   boardPagingMapper;
	
//  /BoardPaging/List?nowpage=1&menu_id=MENU01&title=&writer=
	@RequestMapping("/List")
	public   ModelAndView   list(int nowpage, BoardVo  boardPagingVo) {
		
		log.info("boardPagingVo : {}", boardPagingVo );
		
		// 메뉴 목록
		List<MenuVo>  menuList   =  menuMapper.getMenuList();
		
		
		// 게시물 목록  페이징
		 // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
		// count: 현재 Menu_id 의 데이터 총 자료수 를 알려준다 -페이지 번호 출력하기 위해
		// menu_id=MENU01&title=&writer=
        int count = boardPagingMapper.count( boardPagingVo );
        PagingResponse<BoardPagingVo> response = null;
        if (count < 1) {
        	response =  new PagingResponse<>(Collections.emptyList(), null);
        }

        SearchVo    searchVo   =  new SearchVo();
        searchVo.setPage(nowpage);
        
        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, searchVo);
        searchVo.setPagination(pagination);
        
        String      menu_id   =  boardPagingVo.getMenu_id();
        String      title     =  boardPagingVo.getTitle();
        String      writer    =  boardPagingVo.getWriter();        
        int         offset    =  searchVo.getOffset();
        int         pageSize  =  searchVo.getPageSize();

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<BoardPagingVo> list = boardPagingMapper.getBoardPagingList(
        		menu_id, title, writer, offset, pageSize);
        response =  new PagingResponse<>(list, pagination);
		
		System.out.println( response );
				
		ModelAndView  mv         =  new ModelAndView();
		mv.addObject("menu_id",    menu_id ); // pagingmenus.jsp
		mv.addObject("nowpage",    nowpage ); // pagingmenus.jsp
		
		mv.addObject("menuList",   menuList );
		mv.addObject("response",   response );
		mv.addObject("searchVo",   searchVo );
		mv.setViewName("boardpaging/list");
		return   mv;
		
	}
	
	//  /Board/WriteForm
	@RequestMapping("/WriteForm")
	public  ModelAndView   writeForm() {
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/write");
		return mv;	
		
	}
	
	
}
