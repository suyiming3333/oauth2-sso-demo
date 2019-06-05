package com.sym.oauth2ssoserver.config;

import com.sym.oauth2ssoserver.service.JwtTokenEnhancer;
import com.sym.oauth2ssoserver.service.MyTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: AuthorizationServerConfig
 * @Package com.sym.oauth2ssoserver.config
 * @Description: OSS 认证服务器配置
 * @date 2019/5/16 22:16
 */

@Configuration
@EnableAuthorizationServer//开启认证服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }


    /**token增强，可自定义token**/
//    @Bean
//    public TokenEnhancer tokenEnhancer(){
//        return new MyTokenEnhancer();
//    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
        return new JwtTokenEnhancer();
    }

//    @Bean
//    public TokenStore tokenStore() {
//        // 基于 JDBC 实现，令牌保存到数据库
//        return new JdbcTokenStore(dataSource());
//    }

    /**
     * jwtToken 对比于普通token，省去了持久化一步
     * **/
    @Bean
    public JwtTokenStore jwtTokenStore() {
        //定义tokenStore类型
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwtToken转换器
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        //AccessToken转换器-定义token的生成方式,这里使用jwt
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //对称加密只需要加入key等其他信息
        jwtAccessTokenConverter.setSigningKey("corn");
        return jwtAccessTokenConverter;
    }

    @Bean
    public ClientDetailsService jdbcClientDetails() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer());
        enhancerList.add(jwtAccessTokenConverter());
        enhancerChain.setTokenEnhancers(enhancerList);
        // 设置令牌
        endpoints.tokenStore(jwtTokenStore());
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        /**设置enhancerList**/
        endpoints.tokenEnhancer(enhancerChain);
        /**注入defaultTokenService后 jwtToken不生效**/
//        endpoints.tokenServices(defaultTokenServices());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 读取客户端配置
        clients.withClientDetails(jdbcClientDetails());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //主要是让/oauth/token支持client_id以及client_secret作登录认证
        security.allowFormAuthenticationForClients();
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()").checkTokenAccess("permitAll()");
//        security.tokenKeyAccess("permitAll()");
    }

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        /**配置定义客户端注册信息 从数据库获取**/
//        clients.jdbc(dataSource());
//        //内存定义客户端注册信息
///*        clients.inMemory()
//                .withClient("my-client-1")
//                .secret("12345678")
//                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .scopes("all")
//                .redirectUris("http://www.baidu.com");  */
//    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
//    }


//    @Bean
//    public TokenStore tokenStore(){
//        return new InMemoryTokenStore();
//    }
//

    /**用来对token进行相关设置，比如设置token有效时长**/
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(jwtTokenStore());//设置token的存储方式
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setAuthenticationManager(authenticationManager);
        tokenServices.setClientDetailsService(jdbcClientDetails());
//        tokenServices.setTokenEnhancer(tokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }


//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        //配置授权服务端点
//        endpoints.accessTokenConverter(jwtAccessTokenConverter());
//        endpoints.tokenStore(jwtTokenStore());
//    }
//

}
