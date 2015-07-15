package cokolwiek.rest.resources;

import cokolwiek.rest.representations.MojaKlasaResultsRepresentation;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.search.SearchProvider;
import com.atlassian.sal.api.search.SearchResults;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.sal.api.user.UserManager;
import com.sun.jersey.api.uri.UriBuilderImpl;
import java.net.URI;
import static java.net.URI.create;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A resource of message.
 */
@Path("/{message}")
public class MojaKlasa {
    private final SearchProvider searchProvider;
    private final ApplicationProperties applicationProperities;
    private final UserManager userManager;
    private final I18nResolver i18nResolver;

    public MojaKlasa(SearchProvider searchProvider, 
            ApplicationProperties applicationProperities,
            UserManager userManager1,
            I18nResolver i18nResolver1) {
        this.searchProvider = checkNotNull(searchProvider, "searchProvider");
        this.applicationProperities = checkNotNull(applicationProperities, "applicationProperities");
        this.userManager = checkNotNull(userManager1, "userManager1");
        this.i18nResolver = checkNotNull(i18nResolver1, "i18nResolver1");
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMessage(@PathParam("message") String message)
    {
       SearchResults results = searchProvider.search(
               userManager.getRemoteUsername(),
               checkNotNull(message, "message"));
       URI self = buildSelfLink(message);
       return Response.ok(new MojaKlasaResultsRepresentation(results, self, i18nResolver)).build();
    }  
    
    public URI buildSelfLink(String message){
        URI base = create(applicationProperities.getBaseUrl()).normalize();
        return new UriBuilderImpl()
                .path(base.getPath())
                .path("/rest/mojaklasa/1.0")
                .path(MojaKlasa.class)
                .build(message);
    }
}