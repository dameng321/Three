package com.dameng.common.security.handler;

import com.dameng.common.core.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>Description: security 密码处理类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/28 13:49
 **/

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    /**
     * Description： 加密方法
     * @param charSequence 1待加密的密码
     * @version 0.1.0
     * @return java.lang.String
     * @author dameng
     * @date 2021/5/28 13:51
     * @since 0.1.0
     */
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    /**
     * Description：密码对比方法
     * @param charSequence 1未加密的密码
     * @param encoderPassword 2已加密的密码
     * @version 0.1.0
     * @return boolean
     * @author dameng
     * @date 2021/5/28 13:52
     * @since 0.1.0
     */
    @Override
    public boolean matches(CharSequence charSequence, String encoderPassword) {

        return encoderPassword.equals(MD5.encrypt(charSequence.toString()));
    }
}
