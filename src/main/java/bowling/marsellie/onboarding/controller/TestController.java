package bowling.marsellie.onboarding.controller;

import bowling.marsellie.onboarding.Endpoints;
import bowling.marsellie.onboarding.dto.ResultCreateDTO;
import bowling.marsellie.onboarding.dto.TestCreateDTO;
import bowling.marsellie.onboarding.dto.TestDTO;
import bowling.marsellie.onboarding.entity.test.Result;
import bowling.marsellie.onboarding.service.TestService;
import bowling.marsellie.onboarding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.TEST)
public class TestController {
    private final TestService testService;
    private final UserService userService;

    @GetMapping
    public List<TestDTO> list() {
        return testService.findAllTests();
    }

    @GetMapping("/{id}")
    public TestDTO findOne(@PathVariable Long id) {
        return testService.findOne(id);
    }

    @PostMapping
    public TestDTO create(@RequestBody TestCreateDTO testCreateDTO) {
        return testService.createTest(testCreateDTO);
    }

    @PostMapping("/result")
    public Result create(
            @AuthenticationPrincipal User user,
            @RequestBody ResultCreateDTO resultCreateDTO
    ) {
        return testService.createResult(userService.findByUsername(user), resultCreateDTO);
    }
}
