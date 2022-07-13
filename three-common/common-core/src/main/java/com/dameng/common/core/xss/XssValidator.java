package com.dameng.common.core.xss;

import com.dameng.common.core.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>
 *  自定义xss校验工具类
 * </p>
 *
 * @author 大梦
 * @since 2020-5-20
 */
public class XssValidator implements ConstraintValidator<Xss, String>
{
    private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext)
    {
        if (StringUtils.isBlank(value))
        {
            return true;
        }
        return !containsHtml(value);
    }

    public static boolean containsHtml(String value)
    {
        Pattern pattern = Pattern.compile(HTML_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}