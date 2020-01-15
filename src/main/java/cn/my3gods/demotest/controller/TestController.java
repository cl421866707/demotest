package cn.my3gods.demotest.controller;

import cn.my3gods.demotest.dto.InnerDto;
import cn.my3gods.demotest.dto.RequestDto;
import cn.my3gods.demotest.model.User;
import cn.my3gods.demotest.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/")
public class TestController {

    @GetMapping("user")
    public User test(){
        return User.builder().id("1").name("Tom").age(19).build();
    }

    @PostMapping("validator")
    public ResponseEntity<String> testValidator(@RequestBody @Valid RequestDto<InnerDto> requestDto) throws JsonProcessingException {
        return ResponseEntity.ok(JsonUtils.object2Json(requestDto));
    }
}
