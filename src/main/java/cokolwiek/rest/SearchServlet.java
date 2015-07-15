/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cokolwiek.rest;

import com.atlassian.templaterenderer.TemplateRenderer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static final Map<String, Object> rendererContext = new HashMap<String, Object>();
    Logger log = Logger.getLogger(SearchServlet.class.getName());

    public SearchServlet(TemplateRenderer renderer) {
        this.renderer = renderer;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {   
        
//        File file = new File("\\restCokolwiek.properties");
//        FileInputStream fileInput = new FileInputStream(file);
//        Properties properties = new Properties();
//        properties.load(fileInput);
//        fileInput.close();
//        String textToRender = properties.getProperty("search.title");
//
//        log.log(Level.INFO, textToRender + " moj log");

        String textToRender = "hakunamatata";
        rendererContext.put("MojSearch", textToRender);

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
//        PrintWriter writer = resp.getWriter();
//        writer.append("<html><body><h1>dsafsadfg</h1></body></html>");
    }
    
}
