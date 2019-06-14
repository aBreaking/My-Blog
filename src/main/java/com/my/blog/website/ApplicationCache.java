package com.my.blog.website;


import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dto.Types;
import com.my.blog.website.model.Bo.ArchiveBo;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.service.ISiteService;
import com.my.blog.website.utils.MapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 程序启动后，初始化一系列的缓存
 * @author liwei_paas
 * @date 2019/6/14
 */
@Component
public class ApplicationCache implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationCache.class);

    private MapCache mapCache = MapCache.single();
    @Resource
    IMetaService metaService;
    @Resource
    private ISiteService siteService;

    @Override
    public void afterPropertiesSet()  {
        initMetas();
        initArchives();
    }

    /**
     * 初始化t_metas的数据
     */
    public void initMetas(){
        mapCache.hset("meta",Types.COLUMN.getType(),metaService.getMetaList(Types.COLUMN.getType(), null, WebConst.MAX_POSTS));
        mapCache.hset("meta",Types.CATEGORY.getType(),metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS));
        mapCache.hset("meta",Types.TAG.getType(),metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS));
    }

    /**
     * 初始化归档数据
     */
    public void initArchives(){
        List<ArchiveBo> archives = siteService.getArchives();
        mapCache.set("archives",archives);
    }
}
