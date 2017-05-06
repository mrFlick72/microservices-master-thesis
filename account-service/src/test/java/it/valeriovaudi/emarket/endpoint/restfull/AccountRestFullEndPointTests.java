package it.valeriovaudi.emarket.endpoint.restfull;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.valeriovaudi.emarket.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by mrflick72 on 05/05/17.
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(AccountRestFullEndPoint.class)
@SpringBootTest
@WebAppConfiguration
public class AccountRestFullEndPointTests {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateAccount() throws Exception {
        mockMvc.perform(post("/account")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Account())))
        .andDo(MockMvcResultHandlers.print());
    }
}
