package com.study.project.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.study.project.commom.base.CommonHeaders;
import com.study.project.commom.context.UnauthorizedException;
import com.study.project.service.domin.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static com.auth0.jwt.JWT.create;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Description:
 * @Date: 2020/9/3  15:20
 * @Author: dongdong
 */
public class SecurityUtils {
    private static final String USER_ID="x-yunke-id";
    private static final String APP_ID="x-app-id";
    public  static final String ATT_COMMON_HEADER="x-common-headers";
    private static final long TOKEN_TTL=90*24*60*60*1000L;
    private static final String TOKEN_SECRET="A12asdbeyfjha1246xcadghtuy81dvads631asdgsdrytr7sbcsdrttrwadagkl9p";
    private static final String TOKEN_ISSURE="YUNKE.COM";
    private static final String HEADER_REQUEST_ID="requestId";
    private static final String HEADER_TIMESTAMP="timestamp";
    private static final String HEADER_APP_ID="appId";
    private static final String HEADER_CLIENT_ID="clientId";
    private static final String HEADER_APP_VERSION="appVersion";
    private static final String HEADER_JS_CODE="jsCode";


      public static CommonHeaders getCommonHeaders(){
          ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
          if(requestAttributes!=null){
              HttpServletRequest request=requestAttributes.getRequest();
              Object  attribute=request.getAttribute(ATT_COMMON_HEADER);
              return (CommonHeaders)attribute;
          }else{
              return  null;
          }
      }


    public static CommonHeaders getCommonHeaders(HttpServletRequest request) {
        CommonHeaders res = new CommonHeaders();
        res.setRequestId(StringUtils.isNotBlank(request.getHeader(HEADER_REQUEST_ID))
                ? request.getHeader(HEADER_REQUEST_ID) : request.getParameter(HEADER_REQUEST_ID));
        res.setTimestamp(StringUtils.isNotBlank(request.getHeader(HEADER_TIMESTAMP))
                ? request.getHeader(HEADER_TIMESTAMP) : request.getParameter(HEADER_TIMESTAMP));
        res.setAppId(StringUtils.isNotBlank(request.getHeader(HEADER_APP_ID)) ? request.getHeader(HEADER_APP_ID)
                : request.getParameter(HEADER_APP_ID));
        res.setClientId(StringUtils.isNotBlank(request.getHeader(HEADER_CLIENT_ID))
                ? request.getHeader(HEADER_CLIENT_ID) : request.getParameter(HEADER_CLIENT_ID));
        res.setAppVersion(StringUtils.isNotBlank(request.getHeader(HEADER_APP_VERSION))
                ? request.getHeader(HEADER_APP_VERSION) : request.getParameter(HEADER_APP_VERSION));
        res.setJsCode(StringUtils.isNotBlank(request.getHeader(HEADER_JS_CODE)) ? request.getHeader(HEADER_JS_CODE)
                : request.getParameter(HEADER_JS_CODE));
        return res;
    }


    public static String getAppIdFormHeader(){
          CommonHeaders header=getCommonHeaders();
          Assert.notNull(header,"No http header found");
          String  appId=header.getAppId();
          Assert.notBlank(appId,"AppId is mandatory");
          return  appId;
    }

    public static String getAppVersionFromHeader() {
        CommonHeaders header = getCommonHeaders();
        Assert.notNull(header, "No http header found");

        String appVersion = header.getAppVersion();
        Assert.notBlank(appVersion, "AppVersion is mandatory");
        return appVersion;
    }

    public static String getJsCodeFromHeader() {
        CommonHeaders header = getCommonHeaders();
        Assert.notNull(header, "No http header found");

        String jsCode = header.getJsCode();
        Assert.notBlank(jsCode, "jsCode is null");
        return jsCode;
    }

    public static String getIdByAuth(){
          User user=getUserByAuth();
          Assert.authorized(user,"Access denied!");
          return  user.getId();
    }

    public static String getPhoneByAuth(){
        User user=getUserByAuth();
        Assert.authorized(user,"Access denied!");
        return  user.getPhone();
    }

    public static User getUserByAuth(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Assert.authorized(authentication,"Access denied!");
        return (User)authentication.getPrincipal();
    }

    public static String buildJwtToken(String userId,String appId){
          try{
           return    create().withIssuer(TOKEN_ISSURE).withClaim(USER_ID,userId).withClaim(APP_ID,appId).
                      withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_TTL)).
                      sign(Algorithm.HMAC256(TOKEN_SECRET));
          }catch(UnsupportedEncodingException e){
              throw  new UnauthorizedException("Build token faild");
          }
    }

    public static String buildJwtToken(String userId,String appId,long ttl){
          try{
              return    create().withIssuer(TOKEN_ISSURE).withClaim(USER_ID,userId).withClaim(APP_ID,appId).
                      withExpiresAt(new Date(System.currentTimeMillis()+ttl)).
                      sign(Algorithm.HMAC256(TOKEN_SECRET));
          }catch(Exception e){
              throw  new UnauthorizedException("Build token faild");
          }
    }


    public static String  getUserIdFromToken(String jwtToken){
          try{
              JWTVerifier verifier= JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(TOKEN_ISSURE).build();
              DecodedJWT jwt=verifier.verify(jwtToken);
              return jwt.getClaim(USER_ID).asString();
          }catch(Exception e){
              return null;
          }
    }

    public  static String buildMd5Token(String appId,String clientId,String secret,String timestamp){
          //SHA1（appId+clientId+SHA1(timestamp)+MD5（SECRET)）
        String token=DigestUtil.sha1(appId+clientId+DigestUtil.sha1(timestamp,"UTF-8")+DigestUtil.md5(secret,"UTF-8"),"UTF-8").toUpperCase();
        return  token;
    }



}
