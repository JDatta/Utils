package jd.utils;

import java.util.regex.Pattern;

public class StringUtils {

  /**
   * Returns the index within this string of the first occurrence of
   * the specified character that it does not appear inside a quote defined by
   * the quote character. If a character with value
   * {@code ch} occurs in the character sequence represented by
   * this {@code String} object, then the index (in Unicode
   * code units) of the first such occurrence is returned. For
   * values of {@code ch} in the range from 0 to 0xFFFF
   * (inclusive), this is the smallest value <i>k</i> such that:
   * <blockquote>
   *
   * <pre>
   * this.charAt(<i>k</i>) == ch
   * </pre>
   *
   * </blockquote>
   * is true. For other values of {@code ch}, the operation is undefined.
   * If no such character occurs in this string, then {@code -1} is returned.
   *
   * @param ch a character (Unicode code point).
   * @return the index of the first occurrence of the character in the
   *         character sequence represented by this object, or
   *         {@code -1} if the character does not occur.
   * @param str The string to search
   * @param ch a character (Unicode code point).
   * @param quoteChar the character that defines the quote.
   * @return the index of the first occurrence of the character in the
   *         character sequence represented by this object, or
   *         {@code -1} if the character does not occur.
   */
  public static int indexOf(final String str, final int ch, final int quoteChar) {
    return indexOf(str, 0, ch, quoteChar);
  }

  /**
   * Returns the index within this string of the first occurrence of the
   * specified character that it does not appear inside a quote defined by
   * the quote character. It starts the search at the specified index.
   * <p>
   * If a character with value {@code ch} occurs in the
   * character sequence represented by this {@code String}
   * object at an index no smaller than {@code fromIndex}, then
   * the index of the first such occurrence is returned. For values
   * of {@code ch} in the range from 0 to 0xFFFF (inclusive),
   * this is the smallest value <i>k</i> such that:
   * <blockquote>
   *
   * <pre>
   * (this.charAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &gt;= fromIndex)
   * </pre>
   *
   * </blockquote>
   * is true. For other values of {@code ch}, the operation is undefined.
   *
   * In either case, if no such character occurs in this
   * string at or after position {@code fromIndex}, then
   * {@code -1} is returned.
   *
   * <p>
   * There is no restriction on the value of {@code fromIndex}. If it
   * is negative, it has the same effect as if it were zero: this entire
   * string may be searched. If it is greater than the length of this
   * string, it has the same effect as if it were equal to the length of
   * this string: {@code -1} is returned.
   *
   * <p>
   * All indices are specified in {@code char} values
   * (Unicode code units).
   *
   * @param ch a character (Unicode code point).
   * @param fromIndex the index to start the search from.
   * @return the index of the first occurrence of the character in the
   *         character sequence represented by this object that is greater
   *         than or equal to {@code fromIndex}, or {@code -1}
   *         if the character does not occur.
   */
  public static int indexOf(final String str, final int fromIndex, final int ch, final int quoteChar) {
    final char[] chars = str.toCharArray();
    boolean inQuote = false;
    for (int i = fromIndex; i < chars.length; i++) {
      if (quoteChar == chars[i]) {
        inQuote = !inQuote;
      }
      if (!inQuote && chars[i] == ch) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Splits the string at the first occurrence of the given character where it
   * does not appear inside a quote as defined by the quote character.
   *
   * @param str The base string
   * @param ch code point to split at
   * @param quoteChar code point the defines the quote
   * @return
   */
  public static String splitOnce(final String str, final int ch, final int quoteChar) {
    return str.substring(0, indexOf(str, ch, quoteChar));
  }

  /**
   * Given a string, it replaces the contents between start token and end token.
   * Tokens always match their first occurrence
   *
   * For example, a call to:
   *
   * replaceBetween("ipsum", "sit", "Lorem ipsum dolor sit amet", "foo");
   * will return
   * "Lorem ipsum foo sit amet"
   *
   * @param startToken start token from where replace needs to start
   * @param endToken end token to where replace needs to end
   * @param orig original string
   * @param replacement the replacement which needs to be placed between start
   *          and end token.
   * @return string with replacement done
   */
  public static String replaceBetween(
    final String startToken, final String endToken, final String orig,
    final String replacement) {

    final String pattern =
      "(?s)(.*?" + Pattern.quote(startToken) +
        " ).*?(" + Pattern.quote(endToken) + ")";
    final String repl = "$1" + replacement + "$2";

    return orig.replaceAll(pattern, repl);
  }
}
