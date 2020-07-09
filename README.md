# Spring Cloud Data Flow
## Bank Loan Stream Demo

This demo code shows how to construct a streaming application in [Spring Cloud Data Flow][dataflow] using Java applications written in [Spring Cloud Stream][stream]. 

The demo code in this repository models a simple Bank Loan processing stream whereby new loans are generated and then sorted into APPROVED and DECLINED states by a loan processor. 

By default, the loan applications are confugured to expect RabbitMQ to provide the underlying messaging infrastucture (although this could be switched to Apache Kafka with very little effort).

Spring Cloud Data Flow is important because it takes care of installing, starting and stopping the applications at runtime, and provides the messaging backbone which the applications use to communicate.  

![Diagram of the Bank Loan Stream][diagram]

### How it works:

The `loan-source` and `loan-processor` applications are compiled, packaged, and containerized by the [Spring Boot][boot] Maven plugin. Once built (with `spring-boot:build-image`) the containers are then pushed to [Docker Hub][hub] where they are then publicly accessible. The `log-sink` application is provided by Spring Cloud Data Flow as one of it's ready to use components (although not installed by default).

Scripts in the `scripts` folder deploy the stream using the Spring Cloud Data Flow CLI. The `register-apps.sh` script registers the applications. The `build-flow.sh` script defines and deploys the loan processing stream to dataflow using the properties provided in a text file. The flow defined in the script echo's the [diagram][diagram] above. Note that each of the applications is registered using the `app` type and deployed by Data Flow in parrallel (`||`). This is because the `loan-processor` uses a _single-input_ with _multiple-outputs_ approach.

During the deployment of the stream, Spring Cloud Data Flow takes care of installing the applications onto the infrastructure (K8s, CloudFoundry, etc.). Spring Cloud Data Flow will set the various properties that allow the applications to adapt to the infrastructure provided at runtime (IP's, ports, etc.)

Once deployed, the logs emitted from the `loan-source`, `loan-processor`, and `log-sink` components can be examined for their output.

[hub]: https://hub.docker.com/u/benwilcock
[boot]: https://spring.io/projects/spring-boot
[dataflow]: https://dataflow.spring.io
[stream]: https://spring.io/projects/spring-cloud-stream
[diagram]: https://github.com/benwilcock/spring-cloud-dataflow-demo/blob/master/img/bank-loan-stream.png?raw=true
