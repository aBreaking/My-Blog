package com.my.blog.website.service;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dto.MetaDto;
import com.my.blog.website.dto.Types;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @{USER}
 * @{DATE}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IMetaServiceTest {

    @Resource
    IMetaService metaService;

    @Test
    public void test01(){
        List<MetaDto> list = metaService.getMetaList(Types.COLUMN.getType(), null, WebConst.MAX_POSTS);
        for (MetaDto metaDto : list){
            System.out.println(metaDto.getName());
        }
    }
}
