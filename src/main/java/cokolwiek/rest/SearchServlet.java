/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cokolwiek.rest;

import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.templaterenderer.TemplateRenderer;
import static com.google.common.base.Preconditions.checkNotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.Collections.emptyMap;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 *
 * @author polkam
 */
public class SearchServlet extends HttpServlet{
    
    private final TemplateRenderer renderer;
    private final UserManager userManager;
    private final LoginUriProvider loginUriProvider;
    
    private static final Map<String, Object> rendererContext = new HashMap<String, Object>();
    Logger log = Logger.getLogger(SearchServlet.class.getName());

    public SearchServlet(TemplateRenderer renderer,
            UserManager userManager,
            LoginUriProvider loginUriProvider) {
        this.renderer = checkNotNull(renderer, "renderer");
        this.userManager = checkNotNull(userManager, "userManager");
        this.loginUriProvider = checkNotNull(loginUriProvider, "loginUriProvider");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        URI base = URI.create("C:\\Users\\polkam\\Desktop\\JIRARestTut\\restPowtorka\\restCokolwiek\\src\\main\\java\\cokolwiek\\rest");
//        URI absolute = URI.create("C:\\Users\\polkam\\Desktop\\JIRARestTut\\restPowtorka\\restCokolwiek\\src\\main\\resources\\restCokolwiek.properties");
//        URI relative = base.relativize(absolute);
       
        try{
            if (userManager.getRemoteUsername(req) == null) {
                System.out.println("jestem w ifie");
                
                URI reqUri = new URI(req.getRequestURI().toString());
                URI loginUri = loginUriProvider.getLoginUri(reqUri);
                resp.sendRedirect(loginUri.toASCIIString());
            } else {
                System.out.println("jestem w elsie");
                InputStream is = getClass().getClassLoader().getResourceAsStream("restCokolwiek.properties");
                String textToRender,
                       formatterOfSearchTime;
                
                if (is!=null)
                {
                    Properties properties = new Properties();
                    properties.load(is);
                    is.close();
                    textToRender = properties.getProperty("search.title");
                    formatterOfSearchTime = properties.getProperty("search.timeOfSearchFormat");
                }
                else{
                    textToRender = "hakunamatata";
                    formatterOfSearchTime = "";
                }

                rendererContext.put("MojSearch", textToRender);
                rendererContext.put("searchTimeOfSearchFormat", formatterOfSearchTime);

                VelocityEngine ve = new VelocityEngine();

                ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
                ve.setProperty("classpath.resource.loader.class",
                    ClasspathResourceLoader.class.getName());

                ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                    "org.apache.velocity.runtime.log.Log4JLogChute");
                try {    
                    ve.init();

                    Template temp = ve.getTemplate("restCokolwiek.vm"); //tego niegdzie nie u¿ywam
                } catch (Exception ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }


                resp.setContentType("text/html");
                renderer.render("velocity/restCokolwiek.vm", rendererContext, resp.getWriter());
            }
        }
        catch(Exception e){
            throw new ServletException(e);
        }
    }
}
