package ink.whi.saibackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TaleUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(TaleUtil.class);

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

    public static String createToken(UserInfo userInfo) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(WebConstant.JWT.JWT_KEY);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            //Invalid Signing
            LOGGER.error("[Error]: {}", exception.getMessage());
        }
        return "";
    }

    public boolean VerifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(WebConstant.JWT.JWT_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .acceptLeeway(60)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            LOGGER.error("[Error]: {}", e.getMessage());
            return false;
        }
        return true;
    }

}
