package ink.whi.saibackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.mapper.UserMapper;
import ink.whi.saibackend.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class TaleUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(TaleUtil.class);

    private static final long ONE_MONTH = 30 * 24 * 60 * 60 * 1000L;

    /**
     * MD5加密
     *
     * @param source 密码
     * @return 加密后的字符串
     */
    public static String MD5encoder(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] encode = messageDigest.digest((source + WebConstant.MD5.MD5_SALT).getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte en : encode) {
                hexString.append(String.format("%02x", en));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("[Error]: {}", e.getMessage());
            return "";
        }
    }

    /**
     * 根据请求获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 生成token
     *
     * @param username
     * @return
     */
    public static String createToken(String username) {
        try {
            // claims:payload的私有声明
            Algorithm algorithm = Algorithm.HMAC256(WebConstant.JWT.JWT_KEY);
            String token = JWT.create()
                    .withSubject(username)
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + ONE_MONTH))
                    .sign(algorithm);
            return WebConstant.JWT.TOKEN_PREFIX + token;
        } catch (JWTCreationException exception) {
            //Invalid Signing
            LOGGER.error("[Error]: {}", exception.getMessage());
        }
        return "";
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static String isVerify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(WebConstant.JWT.JWT_KEY);
            String subject = JWT.require(algorithm)
                    .build()
                    .verify(token.replace(WebConstant.JWT.TOKEN_PREFIX, ""))
                    .getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            LOGGER.error("[Error]: {}", e.getMessage());
            return "";
        }
    }

    /**
     * 检验token是否需要更新
     * @param token
     * @return
     */
    public static boolean isNeedUpdate(String token) {
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC256(WebConstant.JWT.JWT_KEY))
                    .build()
                    .verify(token.replace(WebConstant.JWT.TOKEN_PREFIX, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e) {
            throw BusinessException.withErrorCode("token验证失败");
        }
        return (expiresAt.getTime() - System.currentTimeMillis()) < (ONE_MONTH>>1);
    }

}
