# Spring Cloud Data Flow
## Bank Loan Stream Demo

This demo code shows how to construct a stream in [Spring Cloud Data Flow][dataflow] using Java applications written in [Spring Cloud Stream][stream].

![Diagram of the Bank Loan Stream][diagram]

### How it works:

1. The `loan-source` and `loan-processor` applications are compiled, packaged, and containerized by the [Spring Boot][boot] Maven plugin. 
1. The containers are then pushed to [Docker Hub][hub] where they are publicly accessible.
1. Scripts in the `scripts` folder then deploy the stream using the Spring Cloud Data Flow CLI.
  * The `register-apps.sh` script registers the applications.
  * The `build-flow.sh` script creates (defines) and deploys the stream to dataflow using the properties provided.
1. Spring Cloud Data Flow takes care of installing the applications onto your chosen infrastructure (K8s, CloudFoundry, etc.)
  * During this process, Spring Cloud Data Flow will set properties that allow the applications to adapt to the infrastructure given (IP's, ports, etc.)

Once deployed, take a look at the logs emitted from the `loan-source`, `loan-processor`, and `log-sink` components for their output.

[hub]: https://hub.docker.com/u/benwilcock
[boot]: https://spring.io/projects/spring-boot
[dataflow]: https://dataflow.spring.io
[stream]: https://spring.io/projects/spring-cloud-stream
[diagram]: https://github.com/benwilcock/spring-cloud-dataflow-demo/blob/master/img/bank-loan-stream.png?raw=true
