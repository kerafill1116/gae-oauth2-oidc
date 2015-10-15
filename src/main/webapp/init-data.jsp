<%@ page session="false"%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="gae.oauth2.oidc.schema.Client" %>
<%@ page import="gae.oauth2.oidc.schema.ResourceServer" %>
<%@ page import="gae.oauth2.oidc.schema.TokenValidationEndpointAuthMethod" %>
<%@ page import="gae.oauth2.oidc.schema.ClientType" %>
<%@ page import="gae.oauth2.oidc.schema.ApplicationType" %>
<%@ page import="gae.oauth2.oidc.schema.TokenEndpointAuthMethod" %>
<%
    ResourceServer resourceServer = new ResourceServer("OpenID Connect Authentication Server", null, TokenValidationEndpointAuthMethod.RESOURCE_SERVER_SECRET_BASIC, null, null);


    Client client = new Client("OpenID Connect Authentication Client", ClientType.CONFIDENTIAL, ApplicationType.NATIVE, null, null, null, null, TokenEndpointAuthMethod.CLIENT_SECRET_BASIC, 0, false, null, null);


%>
