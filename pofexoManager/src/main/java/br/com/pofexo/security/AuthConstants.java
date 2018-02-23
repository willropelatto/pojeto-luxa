package br.com.pofexo.security;

public class AuthConstants {
//    public static final String SECRET = "SecretKeyToGenJWTs";
	public static final String SECRET = "MarxinhuVoxeXafadu";
//    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final long EXPIRATION_TIME = 10_800_000; // 3h
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";
    public static final String TEAM_REG = "/team/register";
    public static final String UPD_PLA = "/main/update";
    public static final String ALL_SHITS = "/**";
}
