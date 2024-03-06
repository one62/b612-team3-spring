//package b612.bicyclecommunity.controller;
//
//import b612.bicyclecommunity.domain.team.Kind;
//import b612.bicyclecommunity.dto.team.req.JoinReq;
//import b612.bicyclecommunity.dto.team.req.SaveReq;
//import b612.bicyclecommunity.dto.user.req.MobileKaKaoLoginReq;
//import b612.bicyclecommunity.dto.user.res.TokenRes;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//class TeamControllerTest {
//
//    private TokenRes tokenRes;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MobileKaKaoLoginReq mobileKaKaoLoginReq = new MobileKaKaoLoginReq("my-kakao-id");
//
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/user/mobile/kakao")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(mobileKaKaoLoginReq)))
//                        .andExpect(MockMvcResultMatchers.status().isOk());
////                        .andDo(MockMvcResultHandlers.print());
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        tokenRes = objectMapper.readValue(contentAsString, TokenRes.class);
//    }
//
//    @Test
//    @DisplayName("팀 저장")
//    void save() throws Exception {
//
//        for(int i=0;i<20;i++){
//
//            SaveReq saveReq = new SaveReq(
//                    UUID.randomUUID().toString(),
//                    "기본",
//                    "서울시",
//                    LocalDateTime.now(),
//                    Kind.HOBBY
//            );
//
//            System.out.println(saveReq.getName());
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/team/save")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .header("accessToken", tokenRes.getAccessToken())
//                    .content(objectMapper.writeValueAsString(saveReq)))
//                    .andExpect(MockMvcResultMatchers.status().isOk());
//        }
//    }
//
//    @Test
//    void info() {
//    }
//
//    @Test
//    void join() {
//    }
//
//    @Test
//    @DependsOn("save")
//    @DisplayName("팀 검색")
//    void search() throws Exception {
//
//        save();
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/team/search")
//                        .param("kind", "name")
//                        .param("keyword", "5")
//                        .param("page", "0")
//                        .param("size", "5")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("accessToken", tokenRes.getAccessToken()))
//                        .andReturn();
//
//        String responseBody = result.getResponse().getContentAsString();
//        System.out.println(responseBody);
//    }
//}