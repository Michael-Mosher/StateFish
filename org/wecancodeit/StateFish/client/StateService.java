
package org.wecancodeit.StateFish.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "StateService", targetNamespace = "http://Controller.StateFish.wecancodeit.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface StateService {


    /**
     * 
     * @param arg0
     * @return
     *     returns org.wecancodeit.StateFish.client.State
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getState", targetNamespace = "http://Controller.StateFish.wecancodeit.org/", className = "org.wecancodeit.StateFish.client.GetState")
    @ResponseWrapper(localName = "getStateResponse", targetNamespace = "http://Controller.StateFish.wecancodeit.org/", className = "org.wecancodeit.StateFish.client.GetStateResponse")
    @Action(input = "http://Controller.StateFish.wecancodeit.org/StateService/getStateRequest", output = "http://Controller.StateFish.wecancodeit.org/StateService/getStateResponse")
    public State getState(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<org.wecancodeit.StateFish.client.State>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getStates", targetNamespace = "http://Controller.StateFish.wecancodeit.org/", className = "org.wecancodeit.StateFish.client.GetStates")
    @ResponseWrapper(localName = "getStatesResponse", targetNamespace = "http://Controller.StateFish.wecancodeit.org/", className = "org.wecancodeit.StateFish.client.GetStatesResponse")
    @Action(input = "http://Controller.StateFish.wecancodeit.org/StateService/getStatesRequest", output = "http://Controller.StateFish.wecancodeit.org/StateService/getStatesResponse")
    public List<State> getStates();

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addState", targetNamespace = "http://Controller.StateFish.wecancodeit.org/", className = "org.wecancodeit.StateFish.client.AddState")
    @ResponseWrapper(localName = "addStateResponse", targetNamespace = "http://Controller.StateFish.wecancodeit.org/", className = "org.wecancodeit.StateFish.client.AddStateResponse")
    @Action(input = "http://Controller.StateFish.wecancodeit.org/StateService/addStateRequest", output = "http://Controller.StateFish.wecancodeit.org/StateService/addStateResponse")
    public boolean addState(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        Fish arg3);

}
