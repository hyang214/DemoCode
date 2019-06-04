package com.hyang.demo.zookeeper;

import com.github.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeDataCASTest {

    public static final String SEQ_ZNODE = "/cas";

    private static final String taskName = "CAS-";

    @Before
    public void init() {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 3000, 50000);
        zkClient.delete(SEQ_ZNODE);
        zkClient.create(SEQ_ZNODE, new byte[0], CreateMode.PERSISTENT);
        zkClient.writeData(SEQ_ZNODE, Long.valueOf(1000).toString().getBytes());
    }

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            Long id = getCurrent();
            System.out.println(taskName + i + " obtain id=" +id );
        }
    }

    private Long getCurrent() {
        try {
            ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 3000, 50000);
            Stat stat = new Stat();
            byte[] currentData = zkClient.readData(SEQ_ZNODE, stat);
            Long current = Long.valueOf(new String(currentData));
            Long next = current + 1;
            zkClient.writeData(SEQ_ZNODE, next.toString().getBytes(), stat.getVersion());
            zkClient.close();
            return current;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
