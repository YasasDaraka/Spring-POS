package lk.ijse.gdse66.spring;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lk.ijse.gdse66.spring.config.WebAppConfig;
import lk.ijse.gdse66.spring.config.WebRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebRootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String uploadsDirectory = System.getProperty("java.io.tmpdir");
        System.out.println(uploadsDirectory);
        registration.setMultipartConfig(
                new MultipartConfigElement(uploadsDirectory,
                        1024*1024*10,
                        1024*1024*20,
                        1024*1024));
    }
}
