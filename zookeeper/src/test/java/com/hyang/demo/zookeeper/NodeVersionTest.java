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
public class NodeVersionTest {

    //提前创建好存储Seq的"/createSeq"结点 CreateMode.PERSISTENT
    public static final String SEQ_ZNODE = "/seq";

    private static final String taskName = "Concurrent-";

    @Before
    public void init() {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 3000, 5000);
        zkClient.delete(SEQ_ZNODE);
        zkClient.create(SEQ_ZNODE, new byte[0], CreateMode.PERSISTENT);
    }

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 3000, 5000);
            Stat stat =zkClient.writeData(SEQ_ZNODE, null, -1);
            int versionAsSeq = stat.getVersion();
            System.out.println(taskName + i + " obtain seq=" +versionAsSeq );
            zkClient.close();
        }
    }

}
