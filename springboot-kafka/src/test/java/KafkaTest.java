import com.xf.kafka.KafkaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * kafka test
 *
 * @author xiaofan
 * @date
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class KafkaTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void contextLoads() {
        try {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test", "luoye", "hello kafka");
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println(result.toString());
                    System.out.println("推送消息成功");
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("推送消息失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}