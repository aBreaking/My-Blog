package com.my.blog.website.controller;

import com.my.blog.website.dto.Types;
import com.my.blog.website.model.Vo.ContentVoExample;
import com.my.blog.website.service.IContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.reflect.Type;

/**
 *
 * @author liwei_paas
 * @date 2019/6/21
 */
@RequestMapping("/poetry")
public class PoetryController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(PoetryController.class);

    @Resource
    private IContentService contentService;

    public String index(){
        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc");
        contentVoExample.createCriteria().andTypeEqualTo("poetry");
        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
        contentService.getArticlesWithpage(contentVoExample,1,9);
        return this.render("poetry");
    }
}
