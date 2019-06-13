package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.dto.QuoteDTO;
import com.mgmtp.internship.experiences.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Quote service for QuoteDTO.
 *
 * @author thuynh
 */
@Service
public class QuoteServiceImpl implements QuoteService {
    public static final String RANDOM_QUOTE_URL = "https://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";

    @Autowired
    private  RestTemplate restTemplate;

    @Override
    public QuoteDTO getQuote() {
        QuoteDTO[] result = restTemplate.getForObject(RANDOM_QUOTE_URL, QuoteDTO[].class);
        return (result!=null && result.length>0) ? result[0] : null;
    }
}
