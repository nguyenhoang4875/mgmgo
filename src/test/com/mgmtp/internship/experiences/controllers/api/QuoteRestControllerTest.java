package com.mgmtp.internship.experiences.controllers.api;

import com.mgmtp.internship.experiences.dto.QuoteDTO;
import com.mgmtp.internship.experiences.exceptions.ApiException;
import com.mgmtp.internship.experiences.services.impl.QuoteServiceImpl;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Unit Test for Rest Controller
 *
 * @author vhduong
 */
@RunWith(MockitoJUnitRunner.class)
public class QuoteRestControllerTest {

    @Mock
    private QuoteServiceImpl quoteService;

    @InjectMocks
    private QuoteRestController quoteRestController;

    @Test
    public void shouldReturnQuoteDTOIfNothingWrong(){
        QuoteDTO expectedQuoteDTO = new QuoteDTO("test", "test");
        Mockito.when(quoteService.getQuote()).thenReturn(expectedQuoteDTO);

        QuoteDTO actualQuoteDTO = quoteRestController.getQuote();

        Assert.assertEquals(actualQuoteDTO, expectedQuoteDTO);
    }

    @Test(expected = ApiException.class)
    public void shouldThrowExceptionIfResultNull(){
        Mockito.when(quoteService.getQuote()).thenReturn(null).thenThrow(ApiException.class);

        QuoteDTO quoteDTO = quoteRestController.getQuote();
    }
}