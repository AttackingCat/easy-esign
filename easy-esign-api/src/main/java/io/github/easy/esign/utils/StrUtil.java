package io.github.easy.esign.utils;

/**
 *
 */
public class StrUtil {

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

    public static String format(String template, Object... args) {
        if (template == null || args == null || args.length == 0) return template;

        StringBuilder sb = new StringBuilder(template.length() + 16 * args.length);
        int argIndex = 0;
        int cursor = 0;
        int bracePos;

        while ((bracePos = template.indexOf("{}", cursor)) != -1 && argIndex < args.length) {
            sb.append(template, cursor, bracePos);
            sb.append(args[argIndex] != null ? args[argIndex].toString() : "null");
            cursor = bracePos + 2;
            argIndex++;
        }

        sb.append(template.substring(cursor));
        return sb.toString();
    }
}
