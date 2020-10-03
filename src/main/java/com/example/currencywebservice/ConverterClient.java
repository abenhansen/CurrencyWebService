package com.example.currencywebservice;

import com.medium.article.GetConversionAmount;
import com.medium.article.GetConversionAmountResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ConverterClient extends WebServiceGatewaySupport {

    public GetConversionAmountResponse getConversionAmount(String currencyFrom, String CurrencyTo, XMLGregorianCalendar rateDate, BigDecimal amount) {
        GetConversionAmount request = new GetConversionAmount();
        request.setCurrencyFrom(currencyFrom);
        request.setCurrencyTo(CurrencyTo);
        request.setRateDate(rateDate);
        request.setAmount(amount);

        GetConversionAmountResponse response = (GetConversionAmountResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, webServiceMessage -> {
                    ((SoapMessage)webServiceMessage).setSoapAction("http://tempuri.org/GetConversionAmount" );
                } );
        return response;
    }
}
