package org.wecancodeit.StateFish.Controller;

public class StateServiceProxy implements org.wecancodeit.StateFish.Controller.StateService {
  private String _endpoint = null;
  private org.wecancodeit.StateFish.Controller.StateService stateService = null;
  
  public StateServiceProxy() {
    _initStateServiceProxy();
  }
  
  public StateServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initStateServiceProxy();
  }
  
  private void _initStateServiceProxy() {
    try {
      stateService = (new org.wecancodeit.StateFish.Controller.StateServiceSoapServiceLocator()).getStateServiceSoapPort();
      if (stateService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)stateService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)stateService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (stateService != null)
      ((javax.xml.rpc.Stub)stateService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.wecancodeit.StateFish.Controller.StateService getStateService() {
    if (stateService == null)
      _initStateServiceProxy();
    return stateService;
  }
  
  public java.util.Collection getStates(){
    if (stateService == null)
      _initStateServiceProxy();
    return stateService.getStates();
  }
  
  public org.wecancodeit.StateFish.Model.State getState(java.lang.String stateAbbreviation){
    if (stateService == null)
      _initStateServiceProxy();
    return stateService.getState(stateAbbreviation);
  }
  
  public boolean addState(java.lang.String name, java.lang.String abbreviation, java.lang.String motto, org.wecancodeit.StateFish.Model.Fish fish){
    if (stateService == null)
      _initStateServiceProxy();
    return stateService.addState(name, abbreviation, motto, fish);
  }
  
  
}