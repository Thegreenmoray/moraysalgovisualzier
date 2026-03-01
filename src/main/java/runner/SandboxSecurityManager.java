package runner;

import java.lang.reflect.ReflectPermission;
import java.security.Permission;
//will eventually be replaced with GraalVM, but this will be fine for now.
public class SandboxSecurityManager extends SecurityManager {


    public void checkExit(int status) {
       throw new SecurityException("System.exit() not allowed");
    }

//this will be updated to greater version so this will be disabled for now
   // public void checkRead(String file) {
  //      throw new SecurityException("File read blocked: " + file+" not allowed");
   // }


    public void checkWrite(String file) {
        throw new SecurityException("File write blocked: " +file+" not allowed");
    }

//no botnet connections
    public void checkConnect(String host, int port) {
        throw new SecurityException("Network blocked: " + host + ":" + port+" not allowed");
    }

//No fork bombs or Deletion of the OS or hard drive
    public void checkExec(String cmd) {
        throw new SecurityException("Process execution blocked: " + cmd+" not allowed");
    }


    public void checkLink(String lib) {

        // Check if this is a system/JavaFX library
        if (lib != null) {
            if (lib.contains("moraysalgovisualizer") ||
                    lib.contains("graph_theory")
            ||lib.contains("animations")
            ||lib.contains("set_theory")
             ||lib.contains("tests")

            ) {
                return;
            }
            // Also allow absolute paths that point to JavaFX
            if (lib.contains("javafx") || lib.contains("jfx")) {
                return;
            }
        }



        throw new SecurityException("Native library blocked: " + lib+" not allowed");

    }



    public void checkPermission(Permission perm) {
        // Block setAccessible attempts
        if (perm instanceof ReflectPermission) {
            throw new SecurityException("Reflection access blocked: " + perm.getName());
        }

        // Block method invocation permissions
        if (perm instanceof RuntimePermission) {
            String name = perm.getName();
            if (name.equals("accessDeclaredMembers") ||
                    name.equals("suppressAccessChecks")) {
                throw new SecurityException("Runtime permission blocked: " + name);
            }
        }
    }


}
