package com.portal.api.constant;

public class SecurityConstant {
    private static final long EXPIRATION_TIME = 432_000;
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String JWT_TOKEN_HEADER ="Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED="Token can't be verified";
    public static final String GET_ARRAY_LLC ="My Test App";
    public static final String GET_ARRAY_ADMINISTRATION="User Management Portal";
    public static final String AUTHORITIES ="Authorities";
    public static final String FORBIDDEN_MESSAGE ="you need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE ="you don't have permission to access this resource";
    public static final String OPTIONS_HTTP_METHOD ="options";
    public static final String [] PUBLIC_URLS ={"/user/login", "/user/register" , "/user/resetpassword/**", "/user/image/**"};
}
