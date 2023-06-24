package com.example.wifiscaner;

public class Element {
    private String ssid;
    private String level;
    private String bssid;
    private String frequency;
    private String capabilities;
    private String encryption;

    public String getEncryption() {
        return encryption;
    }

    public Element(String ssid, String bssid, String frequency, String level, String capabilities, String encryption) {
        this.ssid = ssid;
        this.bssid=bssid;
        this.frequency=frequency;
        this.encryption=encryption;
        this.capabilities = capabilities;
        this.level = level;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public String getLevel() {
        return level;
    }
}
