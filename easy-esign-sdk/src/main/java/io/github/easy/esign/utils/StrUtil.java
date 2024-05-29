package io.github.easy.esign.utils;

/**
 *
 */
public class StrUtil {
    /**
     * 打印 Sa-Token 版本字符画
     */
    public static void printEasyEsign() {
        String str = "  ______                                _               \n" +
                " |  ____|                              (_)              \n" +
                " | |__    __ _  ___  _   _    ___  ___  _   __ _  _ __  \n" +
                " |  __|  / _` |/ __|| | | |  / _ \\/ __|| | / _` || '_ \\ \n" +
                " | |____| (_| |\\__ \\| |_| | |  __/\\__ \\| || (_| || | | |\n" +
                " |______|\\__,_||___/ \\__, |  \\___||___/|_| \\__, ||_| |_|\n" +
                "                      __/ |                 __/ |       \n" +
                "                     |___/                 |___/        ";
        System.out.println(str);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(final CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return true;
        }
        for (int i = 0; i < cs.length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
