package com.ustclab.emoji.common.util;

import com.ustclab.emoji.common.model.dao.User;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
public class UserLocalUtil {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();
    public static void set(User user) {
        threadLocal.set(user);
    }
    public static User get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
