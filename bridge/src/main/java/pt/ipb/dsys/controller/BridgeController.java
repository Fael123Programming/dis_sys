package pt.ipb.dsys.controller;

// GET to http://127.0.0.1:7070/api/ping

import org.springframework.web.bind.annotation.*;
import pt.ipb.dsys.bridge.dto.StudentDto;
import pt.ipb.dsys.bridge.service.MessageService;

@RestController
public class BridgeController {
    private final MessageService service;

    public BridgeController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/greetings")
    public String greetings() {
        return "<html><h1>Hello!</h1><h2>Welcome to my API.</h2></html>";
    }

    @GetMapping("/hello/{number}")
    public String helloWorld(
            @PathVariable(value = "number") int number,
            @RequestParam(value = "name") String name
    ) {
        return String.format("<html><h1>Hello, %s! You are student number %d</h1></html>", name, number);
    }

    @PostMapping("/hello")
    public String newStudent(
            @RequestBody StudentDto dto
    ) {
        // Send to message queue.
        service.sendMessage(dto);
        return String.format("'%s' created!", dto.toString());
    }
}
