package com.ustclab.emoji.manager.service;

import java.util.List;
import java.util.Map;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
public interface EmojiService {
    Map<String, Integer> count(String startTime, String endTime);


    List<Map<String, Integer>> hourly(String startTime);
}
