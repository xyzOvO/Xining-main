package com.xyz66.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xyz66.domain.entity.Article;
import com.xyz66.service.ArticleService;
import com.xyz66.utils.RedisCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    //    @Scheduled(cron = "0/5 * * * * ?")
//    public void updateViewCount(){
//        //获取redis中的浏览量
//        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
//
//        List<Article> articles = viewCountMap.entrySet()
//                .stream()
//                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
//                .collect(Collectors.toList());
//        //更新到数据库中
//        articleService.updateBatchById(articles);
//
//    }

    @Scheduled(cron = "10/30 * * * * ? ")// 30S刷新一次
    public void updateViewCount() {

        //获取redis中浏览量数据
        // 获取缓冲Map，key 的名字为 article:viewCount
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
//        System.out.println(viewCountMap);
        // 将Map转换为List
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                // 转换为Article文章对象，key为文章ID，value为浏览量
                .map(entry ->
                        new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        log.info("------执行定时任务：更新文章浏览量--------");
//        System.out.println("------执行定时任务：更新文章浏览量--------");
        for (Article article : articles) {
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, article.getId());
            updateWrapper.set(Article::getViewCount, article.getViewCount());
            articleService.update(updateWrapper);
        }
        log.info("--------------更新完毕----------------");
//        System.out.println("--------------更新完毕----------------");
    }
}
