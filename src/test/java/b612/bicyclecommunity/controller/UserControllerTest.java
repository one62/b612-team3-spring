//package b612.bicyclecommunity.controller;
//
//import b612.bicyclecommunity.dto.user.req.MobileKaKaoLoginReq;
//import b612.bicyclecommunity.dto.user.res.TokenRes;
//import b612.bicyclecommunity.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//class UserControllerTest {
//
//    private TokenRes tokenRes;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("mobileKakao Test")
//    public void mobileKakao() throws Exception{
//
//        //given
//        MobileKaKaoLoginReq mobileKaKaoLoginReq = new MobileKaKaoLoginReq("my-kakao-id");
//
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/user/mobile/kakao")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(mobileKaKaoLoginReq)))
//                        .andExpect(MockMvcResultMatchers.status().isOk())
//                        .andDo(MockMvcResultHandlers.print());
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        tokenRes = objectMapper.readValue(contentAsString, TokenRes.class);
//    }
//
//    @Test
//    void info() {
//        System.out.println(tokenRes);
//    }
//
//    @Test
//    void edit() {
//    }
//}