/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cokolwiek.rest.representations;

import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.sal.api.search.SearchResults;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import static com.google.common.collect.Iterables.transform;
import java.util.Collection;

/**
 *
 * @author polkam
 */
public class MojaKlasaResultsRepresentation {
    
    @JsonProperty private final Collection<MojaKlasaMatchRepresentation> matches;
    @JsonProperty private final Collection<MojaKlasaErrorRepresentation> errors;
    @JsonProperty private final long searchTime;
    @JsonProperty private final Map<String, URI> links;
    

    public MojaKlasaResultsRepresentation(SearchResults results, URI self, I18nResolver i18nResolver) {
        this.matches = ImmutableList.copyOf(transform(
        results.getMatches(),
        MojaKlasaMatchRepresentation.apply()));
        this.errors = ImmutableList.copyOf(transform(
        results.getErrors(),
        MojaKlasaErrorRepresentation.apply(i18nResolver)));
        this.links = ImmutableMap.of("self", self);
        this.searchTime = checkNotNull(results, "results").getSearchTime();
    }

    @JsonCreator
    MojaKlasaResultsRepresentation(@JsonProperty("searchTime") long searchTime,
            @JsonProperty("links") Map<String, URI> links,
            @JsonProperty("matches") Collection<MojaKlasaMatchRepresentation> matches,
            @JsonProperty("errors") Collection<MojaKlasaErrorRepresentation> errors) {
        this.searchTime = searchTime;
        this.links = ImmutableMap.copyOf(links);
        this.matches = ImmutableList.copyOf(matches);
        this.errors = ImmutableList.copyOf(errors);
    }

    public long getSearchTime() {
        return searchTime;
    }
    
    public Map<String, URI> getLinks() {
        return links;
    }

    public Collection<MojaKlasaMatchRepresentation> getMatches() {
        return matches;
    }

    public Collection<MojaKlasaErrorRepresentation> getErrors() {
        return errors;
    }
    
}
