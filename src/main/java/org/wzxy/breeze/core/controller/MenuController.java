package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.MenuDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IMenuService;

/**
 * @author 覃能健
 * @create 2020-05
 */
@RestController
@RequestMapping("/medical")
public class MenuController {
    @Autowired
    protected Page<MenuDto> menuPage;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;
    @Autowired
    private MenuDto menuById;

    @PostMapping("/menu")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增菜单")
    public ResponseResult addMenu(@Validated MenuDto menuDto) {
            handle=menuService.addMenu(menuDto);
            Result.renderResult(handle);
            return Result;
    }

    @PutMapping("/menu")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新菜单")
    public ResponseResult updateMenu(@Validated MenuDto menuDto) {
            handle= menuService.updateMenu(menuDto);
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/menu")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "查找菜单")
    public ResponseResult queryMenuById(MenuDto menuDto) {
            menuById = menuService.findMenuById(menuDto.getMenuId());
            Result.renderResult(ResponseCode.OK.getCode(),
                    "查找菜单成功！",
                    menuById,
                    menuService.getAllMenus()
            );
            return Result;
    }


    @DeleteMapping("/menu")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除菜单")
    public ResponseResult deleteMenuById(MenuDto menuDto) {
            handle=menuService.deleteMenuById(menuDto.getMenuId());
            Result.renderResult(handle);
            return Result;
    }


    @GetMapping("/menu/all")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取全部菜单")
    public ResponseResult getAllMenus() {
            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取全部菜单成功！",
                    menuService.getAllMenus()
            );
            return Result;
    }

    @GetMapping("/menu/tree/add")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取菜单树")
    public ResponseResult getRolesTreeOfAdd(){
            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取菜单树成功！",
                    menuService.getTreeOfAdd()
            );
            return Result;
    }

    @GetMapping("/menu/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取菜单分页列表")
    public ResponseResult queryMenuByPage(MenuDto menuDto) {
            menuPage=menuService.findMenuByPage(menuDto.getNowPage(), menuDto.getPageSize());
            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取菜单分页列表成功！",
                    menuPage
            );
            return Result;
    }


}
