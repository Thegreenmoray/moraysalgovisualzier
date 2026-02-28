package runner;

import java.security.Permission;

public class SandboxSecurityManager extends SecurityManager {

    @Override
    public void checkExit(int status) {
        throw new SecurityException("System.exit() not allowed");
    }

    @Override
    public void checkRead(String file) {
        throw new SecurityException("File read blocked: " + file+" not allowed");
    }

    @Override
    public void checkWrite(String file) {
        throw new SecurityException("File write blocked: " + file+" not allowed");
    }

    @Override
    public void checkConnect(String host, int port) {
        throw new SecurityException("Network blocked: " + host + ":" + port+" not allowed");
    }

    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("Process execution blocked: " + cmd+" not allowed");
    }

    @Override
    public void checkLink(String lib) {
        throw new SecurityException("Native library blocked: " + lib+" not allowed");
    }


    @Override
    public void checkPermission(Permission perm) {
        //everything else should be fine for now
    }


}
