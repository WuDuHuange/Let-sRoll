package com.wudu.Roll.controller;

import com.wudu.Roll.entity.Reco;
import com.wudu.Roll.service.RecoService;
import com.wudu.Roll.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reco")
@Slf4j
public class RecoController {
    @Autowired
    private RecoService recoService;
    @PostMapping("/roll")
    public CommonResult roll(@RequestBody Reco reco) {
        try {
            String username = reco.getUsername();
            int userid = reco.getUserid();
            String result = recoService.roll(username, userid);
            return CommonResult.success(result);
        } catch (Exception e) {
            log.error("roll失败", e);
            return CommonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/listReco")
    public CommonResult listReco(@RequestBody Reco reco) {
        try {
            String username = reco.getUsername();
            int userid = reco.getUserid();
            List result = recoService.listReco(username, userid);
            return CommonResult.success(result);
        } catch (Exception e) {
            log.error("listReco失败", e);
            return CommonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/deleteReco")
    public CommonResult deleteReco(@RequestBody Reco reco) {
        try {
            String username = reco.getUsername();
            int userid = reco.getUserid();
            String result = recoService.deleteReco(userid);
            return CommonResult.success(result);
        } catch (Exception e) {
            log.error("deleteReco失败", e);
            return CommonResult.fail(e.getMessage());
        }
    }
}
