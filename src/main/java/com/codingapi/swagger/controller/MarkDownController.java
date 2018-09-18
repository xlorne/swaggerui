package com.codingapi.swagger.controller;

import com.codingapi.swagger.service.MarkdownService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * @author lorne
 * @date 2018/9/13
 * @description
 */
@Controller
public class MarkDownController {

    @Autowired
    private MarkdownService markdownService;

    @RequestMapping(value = "/{file}", method = RequestMethod.GET)
    public String markdownView(Model model,@PathVariable("file") String file)  throws IOException {
        String filePath = String.format("static/md/%s",file);
        Resource resource = new ClassPathResource(filePath);
        String md = IOUtils.toString(resource.getInputStream(),"utf-8");
        model.addAttribute("list", resource.getFile().getParentFile().list());
        model.addAttribute("md", markdownService.parseMarkdownString(md));
        return "markdown";
    }


}
