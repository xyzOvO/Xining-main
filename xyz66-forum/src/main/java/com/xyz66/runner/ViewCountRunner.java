package com.xyz66.runner;

import com.xyz66.domain.entity.Article;
import com.xyz66.mapper.ArticleMapper;
import com.xyz66.utils.RedisCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
public class ViewCountRunner implements ApplicationRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

//    @Override
//    public void run(String... args) throws Exception {
//        //查询博客信息  id  viewCount
//        List<Article> articles = articleMapper.selectList(null);
//        Map<String, Integer> viewCountMap = articles.stream()
//                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
//                    return article.getViewCount().intValue();//
//                }));
//        //存储到redis中
//        redisCache.setCacheMap("article:viewCount",viewCountMap);
//    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("读取文章浏览量->Redis");
        //查询博客信息  id  viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();//
                }));
        //存储到redis中
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}
