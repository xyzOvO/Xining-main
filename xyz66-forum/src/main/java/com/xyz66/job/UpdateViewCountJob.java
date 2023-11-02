package com.xyz66.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xyz66.domain.entity.Article;
import com.xyz66.service.ArticleService;
import com.xyz66.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
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

    @Scheduled(cron = "10/30 * * * * ? ")
    public void updateViewCount() {

        //获取redis中浏览量数据
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        System.out.println(viewCountMap);
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry ->
                        new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        System.out.println("执行定时任务：更新文章浏览量");
        for (Article article : articles) {
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, article.getId());
            updateWrapper.set(Article::getViewCount, article.getViewCount());
            articleService.update(updateWrapper);
        }
    }
}
