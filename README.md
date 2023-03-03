# feign-formdata-example
example for file transferring with spring-cloud-openfeign

1. start application and client applications
2. use client controller to send data to application

```shell
http -f POST :8080/test dto@'/home/olga/IdeaProjects/feign-formdata-example/postman/test.json;type=application/json' file@'/home/user/feign-formdata-example/postman/test-file.pdf;type=application/pdf'
```
```shell
http -f POST :8080/teststream dto@'/home/olga/IdeaProjects/feign-formdata-example/postman/test.json;type=application/json' file@'/home/user/feign-formdata-example/postman/test-file.pdf;type=application/pdf'
```