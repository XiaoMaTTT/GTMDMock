package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.vo.BaseResponseVO;

import com.gtmdmock.admin.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "project相关操作")
@RequestMapping("/project")
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "获取所有的项目")
    @GetMapping("/list")
    public BaseResponseVO getAllProjects(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){

        PageHelper.startPage(pageNumber,5);
        List<Project> projects = projectService.getAllProjects();
        PageInfo<Project> pageInfo = new PageInfo<>(projects);
        return BaseResponseVO.success(pageInfo);
    }

    @ApiOperation(value = "根据projectId获取项目")
    @GetMapping("/get")
    public BaseResponseVO getProjectById(@RequestParam(value = "projectId") Integer projectId){
        Project project = projectService.getProjectById(projectId);
        return BaseResponseVO.success(project);
    }

    @ApiOperation(value = "添加一个项目，如果isOpen为1，在添加的同时，也会同步至core")
    @PostMapping("/add")
    public BaseResponseVO addProject(@RequestBody Project project){

        projectService.insertProjectToCore(project);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "更新一个项目，可以通过更改isOpen字段，来开启/关闭项目是否生效")
    @PostMapping("/upd")
    public BaseResponseVO updateProject(@RequestBody Project project){
        projectService.updateProjectOfCore(project);
        return BaseResponseVO.success("更新成功");
    }

    @ApiOperation(value = "删除一个项目，同时删除core中已经生效的MockServerClient")
    @GetMapping("/del")
    public BaseResponseVO deleteProject(@RequestParam(value = "projectId") Integer projectId){
        projectService.deleteProjectOfCore(projectId);
        return BaseResponseVO.success("success");
    }
}
