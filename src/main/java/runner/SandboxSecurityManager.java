package runner;

import java.security.Permission;
import java.util.Set;

public class SandboxSecurityManager extends SecurityManager {


    public void checkExit(int status) {
       throw new SecurityException("System.exit() not allowed");
    }

//this will be updated to greater version so this will be disabled for now
   // public void checkRead(String file) {
  //      throw new SecurityException("File read blocked: " + file+" not allowed");
   // }


    public void checkWrite(String file) {
        throw new SecurityException("File write blocked: " + file+" not allowed");
    }


    public void checkConnect(String host, int port) {
        throw new SecurityException("Network blocked: " + host + ":" + port+" not allowed");
    }


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
                return; // Your own code is safe
            }
            // Also allow absolute paths that point to JavaFX
            if (lib.contains("javafx") || lib.contains("jfx")) {
                return;
            }
        }



        throw new SecurityException("Native library blocked: " + lib+" not allowed");

    }



    public void checkPermission(Permission perm) {
        //everything else should be fine for now
    }


}
