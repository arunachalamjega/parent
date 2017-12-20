<%--
    Author     : Alejandro
    Description: JSP de inicio para el servicio administrar servicios.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/comun/include.jsp" %>

<c:redirect url="/administrarServicios/formulario_administrarServicios.action?accion=buscar" />
