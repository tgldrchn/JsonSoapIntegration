package com.example.json.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class SoapClient {

    @Value("${soap.url}")
    private String soapUrl;

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public String validateToken(String token) {
        String requestXml =
            "<ValidateTokenRequest xmlns='http://example.com/auth'>" +
                "<token>" + token + "</token>" +
            "</ValidateTokenRequest>";

        try {
            StreamSource source = new StreamSource(new StringReader(requestXml));
            StringWriter writer = new StringWriter();

            webServiceTemplate.sendSourceAndReceiveToResult(
                soapUrl, source, new StreamResult(writer)
            );

            String xml = writer.toString();
            System.out.println("=== SOAP VALIDATE RESPONSE ===");
            System.out.println(xml);

            if (xml.contains("true")) {
                if (xml.contains("<userId>")) {
                    int start = xml.indexOf("<userId>") + 8;
                    int end = xml.indexOf("</userId>");
                    if (end > start) return xml.substring(start, end);
                }

                if (xml.contains(":userId>")) {
                    int start = xml.indexOf(":userId>") + 8;
                    int end = xml.indexOf("</", start);
                    if (end > start) return xml.substring(start, end);
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println("=== SOAP ERROR ===");
            e.printStackTrace();
            return null;
        }
    }
}