package jd.utils;

import org.junit.Assert;
import org.junit.Test;

public class TestStringUtils {

  private String str =
    "\n\nCREATE TABLE jd.table1\n(\n    "
      + "\"timestamp\" timestamp,\n    table1 int,\n    "
      + "f_cookie varchar(2048),\n    ipaddr varchar(2048),\n    "
      + "DEFAULT regexp_replace(table1.f_cookie, 'abc;abc', '')\n);\n\n"
      + "ALTER TABLE jd.table1 ADD PRIMARY KEY (f_cookie)";

  @Test
  public void testQuotedIndexOf() {
    Assert.assertEquals(184, StringUtils.indexOf(this.str, ';', '\''));
  }

  @Test
  public void testSplitOnce() {
    String expected = "\n\nCREATE TABLE jd.table1\n(\n    "
      + "\"timestamp\" timestamp,\n    table1 int,\n    "
      + "f_cookie varchar(2048),\n    ipaddr varchar(2048),\n    "
      + "DEFAULT regexp_replace(table1.f_cookie, 'abc;abc', '')\n)";
    Assert.assertEquals(expected, StringUtils.splitOnce(this.str, ';', '\''));
  }

  @Test
  public void testSimpleReplaceBetween() {
    String original = "Lorem ipsum dolor sit amet";
    String expected = "Lorem ipsum foo sit amet";
    Assert.assertEquals(
      expected, StringUtils.replaceBetween("ipsum", "sit", original, " foo "));

  }
}
