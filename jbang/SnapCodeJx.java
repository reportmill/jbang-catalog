///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS https://reportmill.com/SnapCode/app/app10/SnapCodeAll-2025.10.jar
//SOURCES ./SnapCodeJx_mac.java
//SOURCES ./SnapCodeJx_macarm.java
//SOURCES ./SnapCodeJx_win.java
//SOURCES ./SnapCodeJx_winarm.java
//SOURCES ./SnapCodeJx_linux.java

public class SnapCodeJx {

    public static void main(String... args) {

        switch(getArch()) {
            case MacIntel -> SnapCodeJx_mac.main(args);
            case MacArm -> SnapCodeJx_macarm.main(args);
            case WinIntel -> SnapCodeJx_win.main(args);
            case WinArm -> SnapCodeJx_winarm.main(args);
            default -> SnapCodeJx_linux.main(args);
        }
    }

    private enum Arch { MacArm, MacIntel, WinIntel, WinArm, Linux }
    private static Arch getArch()
    {
        String javaVersion = System.getProperty("java.version");
        String javaVendor = System.getProperty("java.vendor");
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();
        System.out.println("Java " + javaVersion + ", vendor: " + javaVendor);
        System.out.println("OS Name: " + osName + ", Arch: " + osArch);

        if (osName.contains("mac"))
            return osArch.contains("aarch64") ? Arch.MacArm : Arch.MacIntel;
        if (osName.contains("windows"))
            return osArch.contains("aarch64") ? Arch.WinArm : Arch.WinIntel;
        
        System.out.println("Assuming linux");
        return Arch.Linux;
    }
}
