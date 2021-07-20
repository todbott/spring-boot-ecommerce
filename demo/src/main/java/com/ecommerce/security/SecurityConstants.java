package com.ecommerce.security;

public class SecurityConstants {
    public static final String LOGIN_URL = "/login"; 
    public static final String SIGN_UP_URL = "/users/record";
    public static final String MAIN_SHOP_URL = "/demo/shop";
    public static final String SEARCH_SHOP_URL = "/demo/shop/search";
    public static final String ADD_TO_CART_URL = "/demo/addItemToCart";
    public static final String DELETE_FROM_CART_URL = "/demo/deleteItemFromCart";
    public static final String BUY_ITEM_URL = "/demo/buyItem";
       
    // These next ones should be protected...
    public static final String ADMIN_HOME_URL = "/admin_home";
    public static final String UPLOAD_URL = "/upload";

    public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
    public static final String HEADER_NAME = "Authorization";
    public static final Long EXPIRATION_TIME = 1000L*60*30;
}