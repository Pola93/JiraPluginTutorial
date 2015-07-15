/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cokolwiek.rest.representations;
import com.atlassian.sal.api.search.SearchMatch;
import com.atlassian.util.concurrent.Nullable;
import com.google.common.base.Function;
import static com.google.common.base.Preconditions.checkNotNull;
import java.net.URI;
import org.codehaus.jackson.annotate.JsonProperty;
import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.sal.api.message.Message;

/**
 *
 * @author polkam
 */
public class MojaKlasaErrorRepresentation {

    @JsonProperty private final String message;
    
    public MojaKlasaErrorRepresentation(Message message, I18nResolver resolver) {
        this.message = checkNotNull(resolver, "resolver").getText(checkNotNull(message, "message"));
    }
    
    public static Function<Message, MojaKlasaErrorRepresentation> apply(final I18nResolver resolver){
        return new Function<Message, MojaKlasaErrorRepresentation>() {

            @Override
            public MojaKlasaErrorRepresentation apply(@Nullable Message message) {
                return new MojaKlasaErrorRepresentation(message, resolver);
            }
        };
    }
    public String getMessage() {
        return message;
    }
}
