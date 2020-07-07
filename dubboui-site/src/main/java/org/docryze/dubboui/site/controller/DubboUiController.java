package org.docryze.dubboui.site.controller;

import org.docryze.dubboui.service.DubboUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DubboUiController {
    @Autowired
    private DubboUiService dubboUiService;

    @GetMapping(value = "/listDubboClass")
    public List<String> listDubboClass() throws Exception{
        return dubboUiService.listDubboClass();
    }

    @GetMapping(value = "/listDubboMethod")
    public List<String> listDubboMethod(@RequestParam("className") String className) throws Exception{
        return dubboUiService.listDubboMethod(className);
    }
}
