package br.com.cooperative.assembly.facede.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cooperative.assembly.domain.UserInfo;


@FeignClient(url = "${client.url.userInfo}", name = "userInfo-client")
public interface UserInfoClient {

    @GetMapping("/users/{document}")
    UserInfo findUser(@PathVariable("document") String document);

}
