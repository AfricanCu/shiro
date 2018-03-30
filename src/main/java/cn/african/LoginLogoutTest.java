package cn.african;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import  org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutTest {
    @Test
    public void testHelloWorld(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        //3、将securityManager设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        //5、这里的帐号和密码 将来是由用户输入进去
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
        try {
            //6、登录，即身份验证
            subject.login(token);
            System.out.println("登陆成功");
        } catch (AuthenticationException e) {
            //7、身份验证失败
            System.out.println("登录失败");
            e.printStackTrace();
        }
        //8、退出
        subject.logout();
    }

    @Test
    public void testJDBCRealm(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("登陆成功");
        } catch (AuthenticationException e) {
            System.out.println("登陆失败");
            //5、身份验证失败
            e.printStackTrace();
        }
        //6、退出
        subject.logout();
    }
}