package cn.african;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基于资源的访问控制
 */
public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermission(){
        login("classpath:shiro-permission.ini", "zhang", "123");
        Assert.assertTrue(subject().isPermitted("user:create"));
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test
    public void testCheckPermission(){
        login("classpath:shiro-permission.ini", "zhang", "123");
        subject().checkPermission("user:create");
        subject().checkPermissions("user:delete", "user:update");
        subject().checkPermissions("user:view");
    }


}
