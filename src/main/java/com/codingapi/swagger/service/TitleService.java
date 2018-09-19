package com.codingapi.swagger.service;

import com.codingapi.swagger.model.Catalog;
import com.codingapi.swagger.utils.MyFileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lorne
 * @date 2018/9/19
 * @description
 */
@Component
public class TitleService {



    public List<Catalog> loadCatalogs(File file) {

        List<Catalog> list = new ArrayList<>();
        String path = file.getParent();

        for(String fileName:file.getParentFile().list()){
            File mdFile = new File(path+"/"+fileName);
            String title = MyFileUtils.readOneLine(mdFile);
            title = title.replaceAll("#","");

            Catalog catalog = new Catalog();
            catalog.setPath(fileName);
            catalog.setTitle(title);
            list.add(catalog);
        }

        return list;
    }
}
