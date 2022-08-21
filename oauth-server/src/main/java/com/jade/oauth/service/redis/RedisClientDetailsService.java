package com.jade.oauth.service.redis;

import com.jade.common.oauth.constant.OauthConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

public class RedisClientDetailsService extends JdbcClientDetailsService {
    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
        super.setSelectClientDetailsSql(OauthConstant.DEFAULT_SELECT_STATEMENT);
        super.setFindClientDetailsSql(OauthConstant.DEFAULT_FIND_STATEMENT);
    }

    @Override
    @Cacheable(value = OauthConstant.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
