package cokolwiek.rest;

import com.atlassian.sal.api.search.SearchResults;
import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MojaKlasaModel {

    @XmlElement(name = "value")
    private String message;

    public MojaKlasaModel() {
    }

    public MojaKlasaModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}