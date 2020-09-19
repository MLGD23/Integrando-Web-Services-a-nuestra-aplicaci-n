package com.example.w4118project.restAPI;

public class ConstantesRestAPI {

    //Agregar Token valido para Instagram Basic Display API/Graph API
    public static final String ACCESS_TOKEN = "";

    public static String USER_ID = "";
    public static final String ROOT_URL = "https://graph.instagram.com/";
    public static final String KEY_GET_RECENT_MEDIA = "{user}/media?fields=like_count,caption,id,media_type,media_url,username";
    public static final String KEY_ME = "me";
    public static final String KEY_GET_PROFILE = "{user}?fields=username";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";

    public static final String URL_GET_USER_PROFILE = KEY_GET_PROFILE + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_RECENT_MEDIA = KEY_GET_RECENT_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
