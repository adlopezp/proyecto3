/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.handlers;

import co.edu.uniandes.ecos.statusquo.centralizador.percistence.facade.AuditoriaWsFacade;
import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.AuditoriaWs;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.NodeList;

/**
 *
 * @author Dev
 */
@Stateless
public class LogingServerHandler implements SOAPHandler<SOAPMessageContext> {

    @EJB
    private AuditoriaWsFacade auditoria;

    @Override
    public boolean handleMessage(SOAPMessageContext mc) {
        try {
            final SOAPMessage message = mc.getMessage();
            SOAPHeader header = message.getSOAPHeader();
            NodeList userIdNode = header.getElementsByTagNameNS("*", "idtxcliente");
            Map<String, List<String>> headers;
            AuditoriaWs transaccion = new AuditoriaWs();
            List<String> variables = new ArrayList<String>();
            StringWriter sw = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message.getSOAPPart()),
                    new StreamResult(sw));
            boolean direction = ((Boolean) mc.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY));
            if (direction) {
                headers = (Map<String, List<String>>) mc.get(SOAPMessageContext.HTTP_REQUEST_HEADERS);
                if (headers != null && headers.get("idtx") != null && !headers.isEmpty()) {
                    transaccion = auditoria.find(new BigDecimal(headers.get("idtx").get(0)));
                }
                transaccion.setFechaOut(new Date());
                transaccion.setMensajeOut(sw.toString());
            } else {
                headers = (Map<String, List<String>>) mc.get(SOAPMessageContext.HTTP_REQUEST_HEADERS);
                if (headers != null && headers.get("idtx") != null && !headers.isEmpty()) {
                    transaccion = auditoria.find(new BigDecimal(headers.get("idtx").get(0)));
                } else {
                    HttpServletRequest req = (HttpServletRequest) mc.get(SOAPMessageContext.SERVLET_REQUEST);
                    if (req != null) {
                        transaccion.setHostActor(req.getRemoteAddr());
                    }
                }
                transaccion.setFechaIn(new Date());
                transaccion.setMensajeIn(sw.toString());
            }
            if (headers == null) {
                headers = new HashMap<String, List<String>>();
            }
            if (transaccion.getId() == null) {
                if (headers != null && headers.get("idtxcliente") != null) {
                    transaccion.setIdcliente(headers.get("idtxcliente").get(0));
                }

                if (userIdNode != null && userIdNode.getLength() != 0) {
                    transaccion.setIdcliente(userIdNode.item(0).getChildNodes().item(0).getNodeValue());
                }
                transaccion.setInOut(!direction);
                auditoria.create(transaccion);
                if (!direction) {
                    variables.clear();
                    variables.add(transaccion.getId().toString());
                    headers.put("idtx", variables);
                } else {
                    QName idtx = new QName("https://co.edu.uniandes.ecos.statusquo.operador.ws.handlers/", "idtxcliente");
                    SOAPHeaderElement tag = header.addHeaderElement(idtx);
                    tag.addTextNode(transaccion.getId().toString());
                    message.saveChanges();
                }
            } else {
                userIdNode = header.getElementsByTagNameNS("*", "idtx");
                if (userIdNode != null && userIdNode.getLength() != 0) {
                    transaccion.setIdcliente(userIdNode.item(0).getChildNodes().item(0).getNodeValue());
                }
                QName idtx = new QName("https://co.edu.uniandes.ecos.statusquo.operador.ws.handlers/", "idtx");
                SOAPHeaderElement tag = header.addHeaderElement(idtx);
                tag.addTextNode(transaccion.getId().toString());
                QName idtxcliente = new QName("https://co.edu.uniandes.ecos.statusquo.operador.ws.handlers/", "idtxcliente");
                SOAPHeaderElement tag2 = header.addHeaderElement(idtxcliente);
                tag2.addTextNode(transaccion.getIdcliente());
                message.saveChanges();
                auditoria.edit(transaccion);
            }
            return true;
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public void close(MessageContext mc
    ) {
    }

    @Override
    public boolean handleFault(SOAPMessageContext mc
    ) {
        return true;
    }
}
