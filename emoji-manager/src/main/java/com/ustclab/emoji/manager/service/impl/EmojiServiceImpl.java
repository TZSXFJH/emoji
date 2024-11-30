package com.ustclab.emoji.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ustclab.emoji.common.exception.EmojiException;
import com.ustclab.emoji.common.model.dao.Emoji;
import com.ustclab.emoji.common.model.vo.EmojiVo;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.common.util.TimeUtil;
import com.ustclab.emoji.manager.mapper.EmojiMapper;
import com.ustclab.emoji.manager.service.EmojiService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@Service
@Slf4j
public class EmojiServiceImpl implements EmojiService {
    @Resource
    EmojiMapper emojiMapper;

    @Override
    public Map<String, Integer> count(String startTime, String endTime) {
        Timestamp startTimestamp;
        Timestamp endTimestamp;
        try{
            startTimestamp = startTime == null ? null : TimeUtil.parseToTimestamp(startTime);
            endTimestamp = endTime == null ? null : TimeUtil.parseToTimestamp(endTime);
        } catch (Exception e) {
            throw new EmojiException(ResultCodeEnum.TIME_ERROR);
        }
        List<Emoji> emojiList = emojiMapper.get(startTimestamp, endTimestamp);
        Map<String, Integer> emojiMap = new HashMap<>();
        for(Emoji e : emojiList) {
            emojiMap.merge(e.getEmojiCode(), 1, Integer::sum);
        }
        return emojiMap;
    }

    @Override
    public List<Map<String, Integer>> hourly(String startTime) {
        Timestamp startTimestamp;
        // 如果为空, 就获取当前时间前一天, 否则获取
        try{
            startTimestamp = startTime == null ? Timestamp.valueOf(LocalDate.now().atStartOfDay()) : TimeUtil.parseToTimestamp(startTime);
        } catch (Exception e) {
            throw new EmojiException(ResultCodeEnum.TIME_ERROR);
        }
        // 查询 24 小时内的 Emoji 数量
        List<Emoji> emojiList = emojiMapper.get(startTimestamp, new Timestamp(startTimestamp.getTime() + 24 * 60 * 60 * 1000));
        List<Map<String, Integer>> emojiMapList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            emojiMapList.add(new HashMap<>());
        }

        for (Emoji emoji : emojiList) {
            Timestamp emojiTimestamp = emoji.getTimestamp();

            // 计算 emoji 所属的时间段
            long diffInMilliseconds = emojiTimestamp.getTime() - startTimestamp.getTime();
            int periodIndex = (int) (diffInMilliseconds / (2 * 60 * 60 * 1000)); // 每 2 小时一个时间段


            for(int i = periodIndex; i < 12; i++) {
                emojiMapList.get(i).merge(emoji.getEmojiCode(), 1, Integer::sum);
            }
        }
        return emojiMapList;
    }

    @Override
    public void export(HttpServletResponse response) {
        try {
            // 设置响应结果类型
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // URLEncoder.encode防止中文乱码
            String fileName = URLEncoder.encode("表情数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            // 查询数据库中的数据
            List<EmojiVo> emojiList = emojiMapper.getAll();

            // 写出数据到浏览器端
            EasyExcel.write(response.getOutputStream(), EmojiVo.class)
                    .sheet("表情数据")
                    .doWrite(emojiList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
