package cn.african;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RoleTest extends BaseTest{

    @Test
    public void testHasRole(){
        login("classpath:shiro-role.ini", "zhang", "123");
        Assert.assertTrue(subject().hasRole("role1"));
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }

    @Test
    public void testCheckRole(){
        login("classpath:shiro-role.ini","zhang", "123");
        subject().checkRole("role1");
        subject().checkRoles("role1", "role3");
    }
}
