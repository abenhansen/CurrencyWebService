package com.example.currencywebservice;

import com.medium.article.GetConversionAmount;
import com.medium.article.GetConversionAmountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterClientConfig.class, loader = AnnotationConfigContextLoader.class)
public class ConverterSoapTest {

    @Autowired
    ConverterClient client;

    @Test
    public void converter_getConversionAmount() throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        GetConversionAmountResponse response = client.getConversionAmount("DKK", "EUR", now, new BigDecimal(10));
        assertEquals(1.3451172256143695, Double.parseDouble(response.getGetConversionAmountResult().toString()), 01);
    }


}
