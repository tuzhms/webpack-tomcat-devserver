package jsp.experiments.dev.server


import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.gradle.api.logging.Logger

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ProxyServlet extends HttpServlet {
    private final CloseableHttpClient httpClient
    private final Logger log
    private final String proxyHost
    private final String subPath
    private ServletConfig config

    ProxyServlet(Logger log, String proxyHost, String subPath) {
        this.httpClient = HttpClientBuilder.create().build()
        this.log = log
        this.proxyHost = proxyHost
        this.subPath = subPath
    }

    @Override
    void init(ServletConfig config) throws ServletException {
        this.config = config
    }

    @Override
    ServletConfig getServletConfig() {
        return config
    }

    @Override
    void service(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        httpProxy(rq, rs)
    }

    @Override
    String getServletInfo() {
        return "proxy info"
    }

    @Override
    void destroy() {
        httpClient.close()
    }

    private void httpProxy(HttpServletRequest rq, HttpServletResponse rs) {
        def request = new HttpUriRequestBase(rq.method, URI.create(proxyHost + rq.requestURI.replaceFirst(subPath, '')))

        def rqLogMessage = new StringBuilder("<< Rq >> $request")

        def headerNames = rq.headerNames
        while (headerNames.hasMoreElements()) {
            String name2 = headerNames.nextElement()
            def headers = rq.getHeaders(name2)
            while (headers.hasMoreElements()) {
                def value = headers.nextElement()
                rqLogMessage += "         $name2: $value"
                request.addHeader(name2, value)
            }
        }
        log.debug rqLogMessage.toString()

        try (def response = httpClient.execute(request) as CloseableHttpResponse) {
            def rsLogMessage = new StringBuilder("<< Rs >> $response")
            (rs as HttpServletResponse).status = response.getCode()
            for (def header : response.getHeaders()) {
                rsLogMessage += "         $header.name: $header.value"
                (rs as HttpServletResponse).setHeader(header.name, header.value)
            }
            log.debug rsLogMessage.toString()

            if (response?.entity?.content != null) {
                if (response.getFirstHeader("Content-Type")?.value?.startsWith('image') ?: false) {
                    response.entity.writeTo((rs as HttpServletResponse).outputStream)
                } else {
                    def writer = new BufferedWriter(new OutputStreamWriter((rs as HttpServletResponse).outputStream))
                    new BufferedReader(new InputStreamReader(response.entity.content)).lines()
                            .forEach {
                                writer.write(it)
                                writer.newLine()
                            }

                    writer.flush()
                }
            }
        }
    }
}
