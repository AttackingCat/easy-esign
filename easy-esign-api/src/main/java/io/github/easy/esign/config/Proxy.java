package io.github.easy.esign.config;

import lombok.Data;

@Data
public class Proxy {

    String hostName;

    Integer port;

    String username;

    String password;
}
