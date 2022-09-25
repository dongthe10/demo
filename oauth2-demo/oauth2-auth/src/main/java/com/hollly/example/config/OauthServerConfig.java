package com.hollly.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author hollly
 * @date 2022/8/21 14:38
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

//
//    //从数据库中查询出客户端信息
//    @Bean
//    public JdbcClientDetailsService clientDetailsService() {
//        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
//        return jdbcClientDetailsService;
//    }
//
//    //token保存策略
    @Bean
    public TokenStore inMemoryTokenStore() {
//        return new JdbcTokenStore(dataSource);
        return new InMemoryTokenStore();
    }
//
//    //授权信息保存策略
//    @Bean
//    public ApprovalStore approvalStore() {
//        return new JdbcApprovalStore(dataSource);
//    }
//
//    //授权码模式专用对象
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }

    //指定客户端登录信息来源
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库取数据
//        clients.withClientDetails(clientDetailsService());

        // 从内存中取数据
//        clients.inMemory()
//                .withClient("baidu")
//                .secret(passwordEncoder.encode("12345"))
//                .resourceIds("product_api")
//                .authorizedGrantTypes(
//                        "authorization_code",
//                        "password",
//                        "client_credentials",
//                        "implicit",
//                        "refresh_token"
//                )// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
//                .scopes("read", "write")// 允许的授权范围
//                .autoApprove(false)
//                //加上验证回调地址
//                .redirectUris("http://www.baidu.com");

//         注意：以下注释的代码在请求了一次 token 之后则可以注释掉，否则如果不换 client 名字的话会因为主键冲突无法插入 client 信息。也可以一开始就注释，手动添加记录到数据库
        clients.inMemory().withClient("client")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code", "refresh_token",
                        "password", "implicit") // 四种认证模式
                .scopes("all")
                .authorities("ROLE_admin","ROLE_user")
                .redirectUris("http://www.baidu.com")
                .accessTokenValiditySeconds(120000)
                .refreshTokenValiditySeconds(50000);
    }

    //检测token的策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()    //允许form表单客户端认证,允许客户端使用client_id和client_secret获取token
                .checkTokenAccess("isAuthenticated()")     //通过验证返回token信息
                .tokenKeyAccess("permitAll()")            // 获取token请求不进行拦截
                .passwordEncoder(passwordEncoder);
    }


    @Autowired(required = false)
    TokenStore inMemoryTokenStore;

    //OAuth2的主配置信息
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .approvalStore(approvalStore())
//                .authenticationManager(authenticationManager)
//                .authorizationCodeServices(authorizationCodeServices())
//                .tokenStore(tokenStore())
//                .userDetailsService(userDetailsService);
        endpoints.tokenStore(inMemoryTokenStore) //配置令牌的存储（这里存放在内存中）
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
