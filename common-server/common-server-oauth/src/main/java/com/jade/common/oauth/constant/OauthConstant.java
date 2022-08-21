package com.jade.common.oauth.constant;

public class OauthConstant {

    public static final String BEARER_TOKEN_TYPE = "Bearer";

    public static final String LOGIN_SUCCESS_STATUS = "0";
    public static final String LOGIN_FAIL_STATUS = "1";
    public static final String LOGIN_SUCCESS = "Success";
    public static final String LOGOUT = "Logout";
    public static final String LOGIN_FAIL = "Error";

    public static final String DETAILS_USER_ID = "user_id";
    public static final String DETAILS_USERNAME = "username";

    public static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";
    public static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";
    public static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    public static final String OAUTH_ACCESS = "oauth:access:";
    public static final String CLIENT_DETAILS_KEY = "oauth:client:details";
}
