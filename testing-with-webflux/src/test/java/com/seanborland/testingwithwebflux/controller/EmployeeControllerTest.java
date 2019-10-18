//package com.seanborland.testingwithwebflux.controller;
//
//import com.seanborland.testingwithwebflux.model.Employee;
//import com.seanborland.testingwithwebflux.service.EmployeeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
////@RunWith(MockitoJUnitRunner.class) //This won't work because this is an IT test and need a bigger context loaded.
//@WebFluxTest(EmployeeController.class)
//public class EmployeeControllerTest {
//
//    @Autowired
//    WebTestClient webTestClient;
//
//    @MockBean //Use @MockBean when using @RunWith(SpringRunner.class).
//    private EmployeeService employeeService;
//
////    @Test
////    public void testGetEmployeeById() {
////        Employee employee = Employee.builder().id(1).city("delhi").age(23).name("ABC").build();
////        Mono<Employee> employeeMono = Mono.just(employee);
////
////        when(employeeService.getEmployeeById(1)).thenReturn(employeeMono);
////
////        webTestClient.get()
////                .uri("/employees/1")
////                .accept(MediaType.APPLICATION_JSON)
////                .exchange()
////                .expectStatus().isOk()
////                .expectBody(Employee.class)
////                .value(employee1 -> employee.getAge(), equalTo(23));
////    }
//
//    @Test
//    public void testDeleteEmployeeById() {
//
//        when(employeeService.deleteEmployeeById(1)).thenReturn(Mono.just("Employee with id 1 is deleted."));
//
//        webTestClient.delete()
//                .uri("/employees/1")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(String.class)
//                .isEqualTo("Employee with id 1 is deleted.");
//
//    }
//
////    @Test
////    public void testCreateEmployee() {
////
////        Employee employee = Employee.builder().id(1).city("delhi").age(23).name("ABC").build();
////        Mono<Employee> employeeMono = Mono.just(employee);
////        when(employeeService.createEmployee(employee)).thenReturn(employeeMono);
////
////        webTestClient.post()
////                .uri("/employees")
////                .contentType(MediaType.APPLICATION_JSON)
////                .accept(MediaType.APPLICATION_JSON)
////                .body(Mono.just(employee), Employee.class)
////                .exchange()
////                .expectStatus().isCreated();
////
////    }
//}
