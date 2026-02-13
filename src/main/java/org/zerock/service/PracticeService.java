package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.dto.MenuDto;
import org.zerock.mapper.MenuMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PracticeService {

    private final MenuMapper menuMapper;

    public PracticeService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    // mapper 호출
    public List<MenuDto> getMenus(String lang) {

        // db 연동 후 mapper 호출
        if (lang == null) {
            lang = "ko";
        }

        if (lang.equals("ko")) {
            return menuMapper.selectMenuKo();
        } else if (lang.equals("en")) {
            return menuMapper.selectMenuEn();
        } else {
            return menuMapper.selectMenuCh();
        }
    }

    // 데이터 가공
    public Map<String, MenuDto> getMenuMap(String lang) {

        List<MenuDto> menus = getMenus(lang);

        Map<String, MenuDto> menuMap = new HashMap<>();
        for (MenuDto menu : menus) {
            menuMap.put(menu.getPk(), menu);
        }
        return menuMap;
    }
}
