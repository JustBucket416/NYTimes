
package android.academy.nytimes.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedium {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }
}
