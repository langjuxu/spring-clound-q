package moc;

import com.alibaba.fastjson.JSONObject;
import com.qian.SpringCloudTest1Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author qian
 * @date 2018/11/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudTest1Application.class)
// mocMvc需要
@WebAppConfiguration
public class MocMvcTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    // 构建mocMvc
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void mocMvcTest01() throws Exception {
        String url = "/test1/zipkin/feng";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void mocMvcTest02() {
        String url = "/test1/moc";
        Map<String, String> map = new HashMap<>();
        map.put("name", "fegnqian");
        MvcResult mvcResult;// 返回执行请求的结果
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)// 模拟向url发送post请求
                    .contentType(MediaType.APPLICATION_JSON)// contentType表示具体请求中的媒体类型信息
                    .content(JSONObject.toJSONString(map)))// content表示具体请求中的参数类型信息
//                    .accept(MediaType.APPLICATION_JSON))// accept指定客户端能够接收的内容类型
                    .andExpect(status().isOk())// 预期返回正确
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                    .andReturn();
            System.err.println(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
