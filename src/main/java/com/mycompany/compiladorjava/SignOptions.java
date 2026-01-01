package com.mycompany.compiladorjava;

import java.util.ArrayList;
import java.util.List;

public class SignOptions {
    public String signtoolExe;
    public String pfxPath;
    public String pfxPassword;
    public String timestampUrl = "http://timestamp.digicert.com";

    public List<String> signCommand(String filePath) {
        List<String> cmd = new ArrayList<>();
        cmd.add(signtoolExe);
        cmd.add("sign");
        cmd.add("/f");
        cmd.add(pfxPath);
        cmd.add("/p");
        cmd.add(pfxPassword);
        cmd.add("/fd");
        cmd.add("SHA256");
        cmd.add("/tr");
        cmd.add(timestampUrl);
        cmd.add("/td");
        cmd.add("SHA256");
        cmd.add("/v");
        cmd.add(filePath);
        return cmd;
    }

    public List<String> verifyCommand(String filePath) {
        List<String> cmd = new ArrayList<>();
        cmd.add(signtoolExe);
        cmd.add("verify");
        cmd.add("/pa");
        cmd.add("/v");
        cmd.add(filePath);
        return cmd;
    }
}