package com.study.project.commom.filter;

import com.study.project.commom.base.CommonHeaders;
import com.study.project.commom.context.UnauthorizedException;
import com.study.project.commom.context.ValidationException;
import com.study.project.service.IUserService;
import com.study.project.service.domin.User;
import com.study.project.utils.Assert;
import com.study.project.utils.DigitalTools;
import com.study.project.utils.SecurityUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.study.project.commom.context.BizConstants.*;

/**
 * @Description:
 * @Date: 2020/9/3  14:53
 * @Author: dongdong
 */
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private static  final String  HEADER_ACCESS_TOKEN="Authorization";
    private static final  String  TOKEN_PREFIX="Bearer ";

    private IUserService userService;

    public JwtTokenFilter(IUserService userService){
        this.userService=userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
       try {
           //报文头信息校验
           CommonHeaders commonHeaders = SecurityUtils.getCommonHeaders(request);
           //提取token
           String jwtToken = StringUtils.isNoneBlank(request.getHeader(HEADER_ACCESS_TOKEN)) ?
                   request.getHeader(HEADER_ACCESS_TOKEN) : request.getParameter(HEADER_ACCESS_TOKEN);
           log.info("[doFilterInternal]--ip:{},jwtToken:{}", request.getRemoteAddr() + "|" +
                   request.getHeader("X-Real-IP") + "|" + request.getHeader("X-Forward-For"), jwtToken);
           Assert.authorized(StringUtils.isNotBlank(jwtToken), "Access Token is empty");
           //提取authentication
           Authentication authentication = this.getAuthentication(jwtToken, commonHeaders);
           SecurityContextHolder.getContext().setAuthentication(authentication);
           chain.doFilter(request, response);
       }catch(UnauthorizedException ue){

       }catch(ValidationException ve){

       }catch(Exception e){

       }
    }

    private void writeResponse(HttpServletResponse response,Object errorObj) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write("");
    }



    private Authentication getAuthentication(String jwtToken,CommonHeaders headers){
        String appId=headers.getAppId();
        Assert.authorized(AGENT_APPID.equals(appId) || DRIVER_APPID.equals(appId) || CHANNEL_APPID.equals(appId),"不支持的登录，请检查APPID");
        Assert.authorized(headers.getClientId(),"clientId is empty");
        User user =null;
        if(CHANNEL_APPID.equals(appId)){
            Assert.notBlank(headers.getClientId(),"clientId is empty");
            Assert.notBlank(headers.getTimestamp(),"timestamp is empty");
            Assert.validate(DigitalTools.checkDecimalPlaces(headers.getTimestamp(),0),"timestamp is illegal");
            user=userService.findChannelById("","","","");
        }else{
            Assert.authorized(jwtToken.startsWith(TOKEN_PREFIX),"Invalid Access Token");
            String id=SecurityUtils.getUserIdFromToken(jwtToken.substring(7));
            Assert.authorized(id,"Invalid Access Token");
            user=userService.findById(id,appId);
        }
        Assert.authorized(user,"无效用户，请退登重试");
        return user2Authentication(user);
    }

    private Authentication user2Authentication(User user) {
        if(user==null || CollectionUtils.isEmpty(user.getRoles())){
            return  null;
        }
        //角色
        List<GrantedAuthority> authorities=user.getRoles().stream().map(x->new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
        //权限
        if(!CollectionUtils.isEmpty(user.getPermissions())){
            List<GrantedAuthority> permissions=user.getPermissions().stream().map(p->new SimpleGrantedAuthority(p)).collect(Collectors.toList());
            authorities.add(permissions);
        }
        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,authorities);
        return authentication;
    }


}
