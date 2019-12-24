
package com.ghandour.bbank.data.model.login.profile;

import com.ghandour.bbank.data.model.login.Client;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("client")
    @Expose
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
