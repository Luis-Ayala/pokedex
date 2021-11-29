package com.bankaya.deliverable.pokedex.pokeapp.config;

import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;

/**
 * Custom SoapFaultMappingExceptionResolver for catch all exceptions.
 */
public class CustomDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(CustomDefinitionExceptionResolver.class);

    private static final QName CODE = new QName("code");
    private static final QName STATUS = new QName("status");
    private static final QName MESSAGE = new QName("message");

    private static final String DEFAULT_FAULT_STRING = "ERROR";

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        LOG.error("Exception processed.", ex);
        if (ex instanceof PokedexException) {
            PokedexException exception = (PokedexException) ex;
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(CODE).addText(String.valueOf(exception.getHttpCode()));
            detail.addFaultDetailElement(STATUS).addText(exception.getHttpStatus());
            detail.addFaultDetailElement(MESSAGE).addText(exception.getMessage());
        } else {
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(CODE).addText(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            detail.addFaultDetailElement(STATUS).addText(ex.getCause().getClass().getName());
            detail.addFaultDetailElement(MESSAGE).addText(ex.getMessage());
        }
    }

    @Override
    protected SoapFaultDefinition getFaultDefinition(Object endpoint, Exception ex) {
        LOG.error("Exception processed.", ex);
        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        faultDefinition.setFaultStringOrReason(DEFAULT_FAULT_STRING);
        return faultDefinition;
    }
}
