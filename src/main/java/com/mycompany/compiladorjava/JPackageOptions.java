package com.mycompany.compiladorjava;

import java.util.ArrayList;
import java.util.List;

public class JPackageOptions {
    public enum Type { APP_IMAGE, EXE }

    public String jpackageExe;
    public Type type = Type.APP_IMAGE;
    public String name;
    public String inputDir;
    public String mainJar;
    public String mainClass;
    public String appVersion;
    public String vendor;
    public String destDir;
    public String iconPath;

    // Opciones Windows para EXE
    public boolean winShortcut;
    public boolean winMenu;
    public boolean winDirChooser;
    public boolean winPerUserInstall;

    public List<String> toCommand() {
        List<String> cmd = new ArrayList<>();
        cmd.add(jpackageExe);
        cmd.add("--type");
        cmd.add(type == Type.APP_IMAGE ? "app-image" : "exe");

        if (name != null && !name.isBlank()) { cmd.add("--name"); cmd.add(name); }
        if (inputDir != null && !inputDir.isBlank()) { cmd.add("--input"); cmd.add(inputDir); }
        if (mainJar != null && !mainJar.isBlank()) { cmd.add("--main-jar"); cmd.add(mainJar); }
        if (mainClass != null && !mainClass.isBlank()) { cmd.add("--main-class"); cmd.add(mainClass); }
        if (appVersion != null && !appVersion.isBlank()) { cmd.add("--app-version"); cmd.add(appVersion); }
        if (vendor != null && !vendor.isBlank()) { cmd.add("--vendor"); cmd.add(vendor); }
        if (destDir != null && !destDir.isBlank()) { cmd.add("--dest"); cmd.add(destDir); }
        if (iconPath != null && !iconPath.isBlank()) { cmd.add("--icon"); cmd.add(iconPath); }

        if (type == Type.EXE) {
            if (winShortcut) { cmd.add("--win-shortcut"); }
            if (winMenu) { cmd.add("--win-menu"); }
            if (winDirChooser) { cmd.add("--win-dir-chooser"); }
            if (winPerUserInstall) { cmd.add("--win-per-user-install"); }
        }
        return cmd;
    }
}