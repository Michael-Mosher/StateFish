
package org.wecancodeit.StateFish.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.wecancodeit.StateFish.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetStatesResponse_QNAME = new QName("http://Controller.StateFish.wecancodeit.org/", "getStatesResponse");
    private final static QName _AddState_QNAME = new QName("http://Controller.StateFish.wecancodeit.org/", "addState");
    private final static QName _GetStates_QNAME = new QName("http://Controller.StateFish.wecancodeit.org/", "getStates");
    private final static QName _GetState_QNAME = new QName("http://Controller.StateFish.wecancodeit.org/", "getState");
    private final static QName _AddStateResponse_QNAME = new QName("http://Controller.StateFish.wecancodeit.org/", "addStateResponse");
    private final static QName _GetStateResponse_QNAME = new QName("http://Controller.StateFish.wecancodeit.org/", "getStateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.wecancodeit.StateFish.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetState }
     * 
     */
    public GetState createGetState() {
        return new GetState();
    }

    /**
     * Create an instance of {@link AddStateResponse }
     * 
     */
    public AddStateResponse createAddStateResponse() {
        return new AddStateResponse();
    }

    /**
     * Create an instance of {@link GetStateResponse }
     * 
     */
    public GetStateResponse createGetStateResponse() {
        return new GetStateResponse();
    }

    /**
     * Create an instance of {@link GetStatesResponse }
     * 
     */
    public GetStatesResponse createGetStatesResponse() {
        return new GetStatesResponse();
    }

    /**
     * Create an instance of {@link GetStates }
     * 
     */
    public GetStates createGetStates() {
        return new GetStates();
    }

    /**
     * Create an instance of {@link AddState }
     * 
     */
    public AddState createAddState() {
        return new AddState();
    }

    /**
     * Create an instance of {@link Fish }
     * 
     */
    public Fish createFish() {
        return new Fish();
    }

    /**
     * Create an instance of {@link State }
     * 
     */
    public State createState() {
        return new State();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller.StateFish.wecancodeit.org/", name = "getStatesResponse")
    public JAXBElement<GetStatesResponse> createGetStatesResponse(GetStatesResponse value) {
        return new JAXBElement<GetStatesResponse>(_GetStatesResponse_QNAME, GetStatesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller.StateFish.wecancodeit.org/", name = "addState")
    public JAXBElement<AddState> createAddState(AddState value) {
        return new JAXBElement<AddState>(_AddState_QNAME, AddState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStates }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller.StateFish.wecancodeit.org/", name = "getStates")
    public JAXBElement<GetStates> createGetStates(GetStates value) {
        return new JAXBElement<GetStates>(_GetStates_QNAME, GetStates.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller.StateFish.wecancodeit.org/", name = "getState")
    public JAXBElement<GetState> createGetState(GetState value) {
        return new JAXBElement<GetState>(_GetState_QNAME, GetState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller.StateFish.wecancodeit.org/", name = "addStateResponse")
    public JAXBElement<AddStateResponse> createAddStateResponse(AddStateResponse value) {
        return new JAXBElement<AddStateResponse>(_AddStateResponse_QNAME, AddStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller.StateFish.wecancodeit.org/", name = "getStateResponse")
    public JAXBElement<GetStateResponse> createGetStateResponse(GetStateResponse value) {
        return new JAXBElement<GetStateResponse>(_GetStateResponse_QNAME, GetStateResponse.class, null, value);
    }

}
