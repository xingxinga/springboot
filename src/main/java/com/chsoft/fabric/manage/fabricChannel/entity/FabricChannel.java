package com.chsoft.fabric.manage.fabricChannel.entity;

public class FabricChannel {
    private String id;

    private String channelName;

    private String channelFilePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelFilePath() {
        return channelFilePath;
    }

    public void setChannelFilePath(String channelFilePath) {
        this.channelFilePath = channelFilePath == null ? null : channelFilePath.trim();
    }
}