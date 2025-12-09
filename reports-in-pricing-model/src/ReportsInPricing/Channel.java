/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportsInPricing;

/**
 *
 * @author serena
 */

public class Channel {
    private String channelId;
    private String name;
    private String type; 

    public Channel(String channelId, String name, String type) {
        this.channelId = channelId;
        this.name = name;
        this.type = type;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }


    public String toString() {
        return name + " (" + type + ")";
    }
}   
