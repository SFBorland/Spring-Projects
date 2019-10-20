//package com.seanborland.testingwithwebflux.controller;
//
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.restdocs.JUnitRestDocumentation;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
//import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
//
//@RunWith(SpringRunner.class)
////@ContextConfiguration(classes = EmployeeController.class)
//public class RestDocs {
//
//    @Rule
//    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
//
////    @Autowired
////    ApplicationContext context;
//
//    private WebTestClient webTestClient;
//
//    @Before
//    public void setUp() {
//        this.webTestClient = WebTestClient.bindToController(EmployeeController.class)
//                .configureClient().baseUrl("localhost:8080")
//                .filter(documentationConfiguration(restDocumentation))
//                .build();
//    }
//
//    @Test
//    public void sample() throws Exception {
//        this.webTestClient.get().uri("/sean").exchange()
//                .expectStatus().isOk().expectBody()
//                .consumeWith(document("user",
//                        responseFields(
//                                fieldWithPath("contact.email").description("The user's email address"),
//                                fieldWithPath("contact.name").description("The user's name"))));
//    }
//
//}
