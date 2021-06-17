package com.kerwin.eduService.controller;


import com.kerwin.commonutils.R;
import com.kerwin.eduService.client.VodClient;
import com.kerwin.eduService.entity.EduVideo;
import com.kerwin.eduService.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author kerwin
 * @since 2021-06-11
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    //测试nacos
    @GetMapping("testNacos")
    public R testNacos(){
        return vodClient.testNacos("kerwin+++");
    }


    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = videoService.save(eduVideo);
        if (save) {
            return R.ok();
        }
        return R.error();
    }

    //删除小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        boolean remove = videoService.removeById(id);
        if (remove) {
            return R.ok();
        }
        return R.error();
    }

}

