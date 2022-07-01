package com.example.demo.spring.mvc;


import org.springframework.web.util.UriComponentsBuilder;



public class PersonHandler {
//
//
//    // ...
//
//    public ServerResponse listPeople(ServerRequest request) {
//
//        PersonHandler handler = new PersonHandler();
//        // ...
//        RouterFunction<ServerResponse> route = route()
//                .GET("/person/{id}", accept(APPLICATION_JSON), handler::getPerson)
//                .GET("/person", accept(APPLICATION_JSON), handler::listPeople)
//                .POST("/person", handler::createPerson)
//                .build();
//        return null;
//    }
//
//    public ServerResponse createPerson(ServerRequest request) {
//
//        try {
//            request.body(String.class);
//            request.params();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ok().contentType(MediaType.APPLICATION_JSON).body("abc");
//        // ...
//
//        HandlerFunction<ServerResponse> responseHandlerFunction = request1 -> ok().body("hello");
//        ok().build();
//        return ok().contentType(MediaType.APPLICATION_JSON).body("abc");
//    }
//
//    public ServerResponse getPerson(ServerRequest request) {
//
//        UriComponents uriComponents = UriComponentsBuilder
//                .fromUriString("https://example.com/hotels/{hotel}")
//                .queryParam("q", "{q}")
//                .encode()
//                .build();
//
//        URI uri = uriComponents.expand("Westin", "123").toUri();
//        // ...
//        return null;
//    }

    public static void main(String[] args) {
        String url = UriComponentsBuilder
                .fromUriString("https://example.com/hotels/{hotel}")
                .queryParam("room","{q}")
                .encode()
                .build().expand("wesdddddd","102").toUriString();
        System.out.println(url);
    }
}
