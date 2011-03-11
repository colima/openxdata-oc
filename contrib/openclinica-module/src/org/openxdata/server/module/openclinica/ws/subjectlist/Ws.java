
package org.openxdata.server.module.openclinica.ws.subjectlist;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import org.openxdata.server.module.openclinica.ws.subjectlist.GetSubjectsRequest;
import org.openxdata.server.module.openclinica.ws.subjectlist.GetSubjectsResponse;
import org.openxdata.server.module.openclinica.ws.subjectlist.Ws;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 * 
 */
@WebService(name = "ws", targetNamespace = "http://openclinica.org/ws/subjectListGet/v1", wsdlLocation = "h:/ws/subjectListGetWsdl.wsdl.xml")
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface Ws {


    /**
     * 
     * @param getSubjectsRequest
     * @return
     *     returns org.openxdata.server.module.openclinica.ws.subjectlist.GetSubjectsResponse
     */
    @WebMethod
    @WebResult(name = "getSubjectsResponse", targetNamespace = "http://openclinica.org/ws/subjectListGet/v1", partName = "getSubjectsResponse")
    public GetSubjectsResponse getSubjects(
        @WebParam(name = "getSubjectsRequest", targetNamespace = "http://openclinica.org/ws/subjectListGet/v1", partName = "getSubjectsRequest")
        GetSubjectsRequest getSubjectsRequest);

}
