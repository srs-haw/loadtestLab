package com.haw.srs.loadtest;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class MyEmbeddedServletContainerCustomizer implements
        WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private static final Logger LOG = LoggerFactory.getLogger(MyEmbeddedServletContainerCustomizer.class);

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        LOG.info("Customizing Tomcat connector");
//        factory.setContextPath("");
//        factory.setPort(8080);
        factory.addConnectorCustomizers(this::customizeConnector);
    }

    private void customizeConnector(Connector connector) {
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        int defaultMaxThreads = protocol.getMaxThreads();
        LOG.info("Tomcat connector maxThreads = " + defaultMaxThreads);
//        int MAX_THREADS = 200;
//        protocol.setMaxThreads(MAX_THREADS);
//        LOG.info("Changed Tomcat connector maxThreads from " + defaultMaxThreads + " to " + MAX_THREADS);
    }
}
