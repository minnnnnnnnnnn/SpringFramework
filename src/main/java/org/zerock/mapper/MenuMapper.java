package org.zerock.mapper;

import org.zerock.dto.MenuDto;

import java.util.List;

public interface MenuMapper {

    List<MenuDto> selectMenuKo();
    List<MenuDto> selectMenuEn();
    List<MenuDto> selectMenuCh();

}
