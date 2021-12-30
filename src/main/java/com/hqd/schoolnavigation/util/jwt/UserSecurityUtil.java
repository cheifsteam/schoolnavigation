package com.hqd.schoolnavigation.util.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.hqd.schoolnavigation.Redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class UserSecurityUtil {
    private static final Logger logger = LoggerFactory.getLogger(UserSecurityUtil.class);

    /**
     * 验证请求中的token
     * @return
     */
    @Resource
    public WebTokenUtil webTokenUtil;
    @Resource
    public RedisCache redisCache;
    @Resource
    public HttpSession httpSession;
    public   boolean verifyWebToken(HttpServletRequest req, HttpServletResponse resp) {

        String token = req.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        DecodedJWT jwtToken = WebTokenUtil.decode(token);
        if (jwtToken == null) {
            return false;
        }
        //从JWT里取出存放在payload段里的userid，查询这个用户信息得到用户最后登录时间
        Integer userId = Integer.valueOf(jwtToken.getSubject());

        httpSession.setAttribute("id",userId);
            //校验
          String cacheToken = redisCache.getCacheObject(String.valueOf(userId));
            if (cacheToken!=null)
            {

                String newToken =webTokenUtil.getRefreshToken(WebTokenUtil.getSecretKey(), jwtToken);
                logger.debug("Subject : [" + userId + "] token expired, allow get refresh token [" + newToken + "]");
                resp.setHeader("Set-Token", newToken);
                return true;
            }
            return false;
    }
}
