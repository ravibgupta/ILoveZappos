package in.ravi.ilovezappos.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {

    @SerializedName("originalTerm")
    private String originalTerm;

    @SerializedName("results")
    private List<Product> results;

    @SerializedName("term")
    private String term;

    public String getOriginalTerm() {
        return originalTerm;
    }

    public List<Product> getResults() {
        return results;
    }

    public String getTerm() {
        return term;
    }
}