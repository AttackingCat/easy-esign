package io.github.easy.esign.constant;

public interface Constant {
    String DELETE = "DELETE";
    String GET = "GET";
    String POST = "POST";
    String PUT = "PUT";

    // sandbox url
    String endpointSandbox = "https://smlopenapi.esign.cn";
    // prod url
    String endpoint = "https://openapi.esign.cn";

    String JSON_CT = "application/json; charset=UTF-8";
    String PDF_CT = "application/pdf;";
}
