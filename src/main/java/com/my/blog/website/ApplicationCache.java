package com.my.blog.website;


import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dto.Types;
import com.my.blog.website.model.Bo.ArchiveBo;
import com.my.blog.website.model.Vo.ContentVo;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.service.ISiteService;
import com.my.blog.website.utils.MapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        /*mapCache.set("archives",archives);
        for (ArchiveBo archiveBo : archives){
            String date = archiveBo.getDate();
            mapCache.hset("archives",date,archiveBo);
        }*/

        List<ArchiveBo> archiveBoList = new ArrayList<>();
        if (archives.size()<=10){
            archiveBoList.addAll(archives);
        }else{
            for (int i=0;i<10;i++){
                ArchiveBo archiveBo = archives.get(i);
                archiveBoList.add(archiveBo);
                mapCache.hset("archives",archiveBo.getDate(),archiveBo);
            }
            ArchiveBo archiveBo = new ArchiveBo();
            archiveBo.setDate(archives.get(10).getDate()+"以前");
            int count = 0;
            List<ContentVo> contentVoList = new ArrayList<>();
            for (int i=10;i<archives.size();i++){
                ArchiveBo bo = archives.get(i);
                count += Integer.parseInt(bo.getCount());
                contentVoList.addAll(bo.getArticles());
            }
            archiveBo.setCount(String.valueOf(count));
            archiveBo.setArticles(contentVoList);
            mapCache.hset("archives",archiveBo.getDate(),archiveBo);
            archiveBoList.add(archiveBo);
        }
        mapCache.set("archives",archiveBoList);
    }
}
