package com.atguigu.guli.service.edu.controller.admin;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author linteng
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据Id删除讲师", notes = "根据ID删除讲师，逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("讲师ID") @PathVariable("id")String id){
        boolean result=teacherService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码" , required = true) @PathVariable("page") Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable("limit") Long limit,
                      @ApiParam("查询对象") TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam,teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("更新成功");
        }else{
            return R.ok().message("数据不存在");
        }
    }

    @ApiOperation("根据ID获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师ID") @PathVariable("id") String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher != null){
            return R.ok().data("item",teacher);
        }else{
            return R.error().message("数据不存在");
        }
    }

}

