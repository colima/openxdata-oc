
package org.openxdata.server.module.openclinica.ws.studylist;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import org.openxdata.server.module.openclinica.ws.studylist.Ws;
import org.openxdata.server.module.openclinica.ws.studylist.WsService;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "wsService", targetNamespace = "http://openclinica.org/ws/studyListGet/v1", wsdlLocation = "h:/ws/studyListGetWsdl.wsdl.xml")
public class WsService
    extends Service
{

    private final static URL WSDL_LOCATION;
    private final static QName WSSERVICE = new QName("http://openclinica.org/ws/studyListGet/v1", "wsService");
    private final static QName WSSOAP11 = new QName("http://openclinica.org/ws/studyListGet/v1", "wsSoap11");

    static {
        URL url = null;
        try {
            url = new URL("file:/H:/ws/studyListGetWsdl.wsdl.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public WsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsService() {
        super(WSDL_LOCATION, WSSERVICE);
    }

    /**
     * 
     * @return
     *     returns Ws
     */
    @WebEndpoint(name = "wsSoap11")
    public Ws getWsSoap11() {
        return (Ws)super.getPort(WSSOAP11, Ws.class);
    }

}
