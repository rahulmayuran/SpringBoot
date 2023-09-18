# SpringBoot
- Latest spring boot project with necessary dependencies.
- Use Completable Future and its use-case
- Use Thread Pool Executor to manage mutiple threads.
- https://mockaroo.com is used to generate csv files.

# Task
- Read the data from a CSV file generated from Mockaroo website.
- Persist the data in a Database.
```yaml
spring.data.mongodb.uri=mongodb+srv://user:user@cluster0.fi5wg.mongodb.net/cogz-fse2-stockMarket
spring.data.mongodb.database=cogz-fse2-stockMarket
```

## Kafka
-  **Kafka** is a distributed streaming platform because it has 1,2…n nodes or Brokers that could be run across multiple regions & data centers, can build location tracker!.
-  It is fault tolerant, highly scalable as it runs as cluster
- **Kafka Broker** is a VM that runs kafka process that can run local, in cloud
-  **Kafka Clusters** : Can have multiple brokers, each broker has multiple combos of topics and partitions. These clusters communicate to producer and consumer
-  **Kafka Connect Source** for data integration which pulls data from your existing data source
-  **Kafka Connect Sink** takes data from topics and puts to data source
-  **Kafka Stream API** is used for data processing, filtering, grouping, aggregating etc
-  **Topic** segregates the stream of messages produced by different producers, It is a collection of events which is replicated and partitioned, Is analogous to log. The data wont disappear, they can stay for couple of minutes/ hours. There is no size limit for a topic, Can have one node for a big topic
-  **Consumer** can consume data of this partition from offset.
-  **Offset** is a location where consumer reads message from partition.
-  If there are 3 partition and 4 consumers , then the 4th consumer wont be able to receive any message. But reverse is not same, So the No of Partitions is always > No of consumers.

---

## Kafka Configurations

- Kafka needs bootstrap-servers, key serializer and value serializer under **producer**
- **Zookeeper** is used to track the status of Kafka messages and has the cluster information.
- Kafka by default runs in port **9092**.
- Zookeeper by default runs in port **2181**
- Make sure System variables have Jdk path's bin folder correctly set.
- Also set ZOO_HOME and KAFKA_HOME in the same way and change the file to zoo.cfg instead of zoo_order default file.
- Run zookeeper first in 2181 ,then run kafka as it creates a session id, cluster id

```yaml 
[2022-02-08 10:01:39,130] INFO jute.maxbuffer value is 4194304 Bytes (org.apache.zookeeper.ClientCnxnSocket)
[2022-02-08 10:01:39,157] INFO zookeeper.request.timeout value is 0. feature enabled= (org.apache.zookeeper.ClientCnxn)
[2022-02-08 10:01:39,162] INFO [ZooKeeperClient Kafka server] Waiting until connected. (kafka.zookeeper.ZooKeeperClient)
[2022-02-08 10:01:39,178] INFO Opening socket connection to server localhost/0:0:0:0:0:0:0:1:2181. Will not attempt to authenticate using SASL (unknown error) (org.apache.zookeeper.ClientCnxn)
[2022-02-08 10:01:39,242] INFO Socket connection established, initiating session, client: /0:0:0:0:0:0:0:1:60308, server: localhost/0:0:0:0:0:0:0:1:2181 (org.apache.zookeeper.ClientCnxn)
[2022-02-08 10:01:39,390] INFO Session establishment complete on server localhost/0:0:0:0:0:0:0:1:2181, sessionid = 0x17ed79972bb0000, negotiated timeout = 6000 (org.apache.zookeeper.ClientCnxn)
[2022-02-08 10:01:39,397] INFO [ZooKeeperClient Kafka server] Connected. (kafka.zookeeper.ZooKeeperClient)
[2022-02-08 10:01:40,051] INFO Cluster ID = tPUHzyy6RQiG1Fz5_SQNWg (kafka.server.KafkaServer)
[2022-02-08 10:01:40,066] WARN No meta.properties file under dir D:\tmp\kafka-logs\meta.properties (kafka.server.BrokerMetadataCheckpoint)
```

- It creates a log directory under tmp, as per the property file.

```yaml
[2022-02-08 10:01:40,750] INFO Log directory D:\tmp\kafka-logs not found, creating it. (kafka.log.LogManager)
[2022-02-08 10:01:40,821] INFO Loading logs. (kafka.log.LogManager)
[2022-02-08 10:01:40,824] INFO [ThrottledChannelReaper-Produce]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2022-02-08 10:01:40,824] INFO [ThrottledChannelReaper-Request]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2022-02-08 10:01:40,824] INFO [ThrottledChannelReaper-Fetch]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2022-02-08 10:01:40,841] INFO Logs loading complete in 16 ms. (kafka.log.LogManager)
[2022-02-08 10:01:41,004] INFO Starting log cleanup with a period of 300000 ms. (kafka.log.LogManager)
[2022-02-08 10:01:41,051] INFO Starting log flusher with a default period of 9223372036854775807 ms. (kafka.log.LogManager)
[2022-02-08 10:01:42,534] INFO Awaiting socket connections on 0.0.0.0:9092. (kafka.network.Acceptor)
```

- Kafka server gets started

```yaml
[2022-02-08 10:01:44,325] INFO Kafka version: 2.4.1 (org.apache.kafka.common.utils.AppInfoParser)
[2022-02-08 10:01:44,327] INFO Kafka commitId: c57222ae8cd7866b (org.apache.kafka.common.utils.AppInfoParser)
[2022-02-08 10:01:44,356] INFO Kafka startTimeMs: 1644294704316 (org.apache.kafka.common.utils.AppInfoParser)
[2022-02-08 10:01:44,363] INFO [KafkaServer id=0] started (kafka.server.KafkaServer)
```

- Define a **@Configuration** class with a **@Bean** method of NewTopic to create the topic fse_stock and run the microservice , add
```yaml
spring.kafka.bootstrap-servers=localhost:9092 
```
to application-properties file.

```yaml
Creating topic fse_stock with configuration {} and initial partition assignment Map(0 -> ArrayBuffer(0)) (kafka.zk.AdminZkClient)
[2022-02-08 21:33:57,283] INFO [ReplicaFetcherManager on broker 0] Removed fetcher for partitions Set(fse_stock-0) (kafka.server.ReplicaFetcherManager)
[2022-02-08 21:33:57,635] INFO [Log partition=fse_stock-0, dir=D:\tmp\kafka-logs] Loading producer state till offset 0 with message format version 2 (kafka.log.Log)
[2022-02-08 21:33:57,720] INFO [Log partition=fse_stock-0, dir=D:\tmp\kafka-logs] Completed load of log with 1 segments, log start offset 0 and log end offset 0 in 243 ms (kafka.log.Log)
[2022-02-08 21:33:57,764] INFO Created log for partition fse_stock-0 in D:\tmp\kafka-logs\fse_stock-0 with properties
```

- Now , in the console of microservice, you can find below message

```yaml
bootstrap.servers = [localhost:9092]
client.dns.lookup = use_all_dns_ips
client.id = 
connections.max.idle.ms = 300000
default.api.timeout.ms = 60000…
2022-02-08 21:37:58.796  INFO 11788 --- [  restartedMain] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 3.0.0
2022-02-08 21:37:58.796  INFO 11788 --- [  restartedMain] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 8cb0a5e9d3441962
2022-02-08 21:37:58.796  INFO 11788 --- [  restartedMain] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1644336478796
```

- Write the configurations for producer with the help of **producerConfig** class map having key and value serializers, ProducerFactory which takes producerconfig as arg and kafkaTemplate which takes producerFactory instance, all marked with @Bean.
- The moment you produce these messages, you can check from cmd for the reception of these messages with below command for consumer.
- To consume in code ,Similarly write consumer logic with the above pattern followed for producer except for the fact that, consumer uses KafkaListenerContainerFactory with all big names.
- Now, to get the messages that producer and consumer are talking, we need a listener. Do similar approach, but this time mark it as @Component instead of @Configuration and just listen to the data that holds the topics and group id with @Kafkalistener.

```yaml
2022-02-09 15:34:56.195  INFO 12560 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-stockGroup-2, groupId=stockGroup] Successfully joined group with generation Generation{generationId=3, memberId='consumer-stockGroup-2-59bdfb1b-4af9-452c-8e69-02f85e42192d', protocol='range'}
2022-02-09 15:34:56.200  INFO 12560 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-stockGroup-2, groupId=stockGroup] Finished assignment for group at generation 3: {consumer-stockGroup-2-59bdfb1b-4af9-452c-8e69-02f85e42192d=Assignment(partitions=[fse_stock-0])}
2022-02-09 15:34:56.216  INFO 12560 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-stockGroup-2, groupId=stockGroup] Successfully synced group in generation Generation{generationId=3, memberId='consumer-stockGroup-2-59bdfb1b-4af9-452c-8e69-02f85e42192d', protocol='range'}
2022-02-09 15:34:56.221  INFO 12560 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-stockGroup-2, groupId=stockGroup] Notifying assignor about the new Assignment(partitions=[fse_stock-0])
2022-02-09 15:34:56.221  INFO 12560 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-stockGroup-2, groupId=stockGroup] Adding newly assigned partitions: fse_stock-0
2022-02-09 15:34:56.228  INFO 12560 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-stockGroup-2, groupId=stockGroup] Setting offset for partition fse_stock-0 to the committed offset FetchPosition{offset=23, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[host.docker.internal:9092 (id: 0 rack: null)], epoch=0}}
2022-02-09 15:34:56.229  INFO 12560 --- [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : stockGroup: partitions assigned: [fse_stock-0]
From Kafka Listener -> Inside Stock Service ~ 0.16274912986653
```

1. Reference : [Git Repo Link](https://github.com/java2786/cts_batch_5/tree/master/kafka)
1. Kafka setup - [Devglan_kafka](https://www.devglan.com/corejava/apache-kafka-java-example)
1. Kafka and Docker - [Kafka+Docker](https://www.kaaiot.com/blog/kafka-docker)

---		
### Kafka Commands

1. Start zookeeper - $ `./bin/windows/zookeeper-server-start.bat ./config/zookeeper.properties` Or in case of higher version, simply type `zkServer` in cmd after setting zookeeper path.

1. Start kafka - `$ .\bin\windows\kafka-server-start.bat .\config\server.properties`

1. Create topic
    - To create 1 topic with 1 partition -> `.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_topic_name`
    - To create 1 topic with 3 partitions -> `.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic threePartitions`

1. List topics - `$ .\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181`

1. Delete topic -
    - Go to `kafka/config/server.properties and add delete.topic.enable=true`
    - Execute this command in cmd -> `.\bin\windows\kafka-topics.bat --zookeeper localhost:2181 -topic stock_topic --delete`. This is marked for deletion because I used _ in topic.

1. Produce into topic - $ `.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic kafka_topic`

1. Consume from topic - `$ .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka_topic --from-beginning`
