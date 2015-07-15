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

/**
 *
 * @author polkam
 */
public class MojaKlasaMatchRepresentation {
    
    @JsonProperty private final String title;
    @JsonProperty private final @Nullable String excerpt;
    @JsonProperty private final URI uri;

    public MojaKlasaMatchRepresentation(SearchMatch searchMatch) {
        checkNotNull(searchMatch, "searchMatch");
        this.title = checkNotNull(searchMatch.getTitle(), "title");
        this.excerpt = searchMatch.getExcerpt();
        this.uri = URI.create(checkNotNull(searchMatch.getUrl(), "uri")).normalize();
    }
    
    public MojaKlasaMatchRepresentation(@JsonProperty("title") String title,
        @JsonProperty("excerpt") @Nullable String excerpt,
        @JsonProperty("uri") URI uri)
    {
        this.title = checkNotNull(title, "title");
        this.excerpt = excerpt;
        this.uri = checkNotNull(uri, "uri");
    }
    
    public static Function<SearchMatch, MojaKlasaMatchRepresentation> apply(){
        return new Function<SearchMatch, MojaKlasaMatchRepresentation>() {

            @Override
            public MojaKlasaMatchRepresentation apply(@Nullable SearchMatch searchMatch) {
                return new MojaKlasaMatchRepresentation(searchMatch);
            }
        };
    }

    public String getTitle() {
        return title;
    }

    public @Nullable String getExcerpt() {
        return excerpt;
    }

    public URI getUri() {
        return uri;
    }
    
}
