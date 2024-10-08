package com.example.book.utils;

/**
 * @author wangjl
 * @date 2024/8/22
 */
public class Constants {
    //appId
    public static final String APP_ID = "9021000140621545";
    //应用私钥
    public static final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFx7cTqAnebbEkcLDPES1bStoV0/drCJSyinpPRFVPicvqeCAVa8ON5wXr7+Kio33gHtZnFkUVOBWutOfIsAF0Lb1AXFQ3xdAqHjLtDWh7qzIc+2ttosplgHVvAbefUIF1dN+F6efVHysBinxVy7QkQea/L8RYiQQv7KSF/3LwieHNtdg3WLnEdAv+gBCvHIVc+v3Xh+YdY5gImzR7fNeBGkw9Jq/cts6mi2b6v9Z6kdCTuVQ2xbujVgLFkMZvNlzxvkLa1YxFOux0a1Gvl7mUROpxgBEwjNqvBa1h9mSw8XTJkqA8BJgLxe0bD0MkC/0esNSi9YrVIcsGPlNwervBAgMBAAECggEAOT1nlvB6MIKMncPd0utNldckEtzb6x8DyJS5mnrpaZvS9SKqz5+JT4j+nqHIPcBoVmG7aOY9bsPNezAKUHMGXDhJ1mTq3+YymCLNtWHcR7XLPl1Q7NsPcHiTRojckTz391KnZPvc5vJsCz++zVLYY/8ShNEiWKNfeEXvayoOet5Vr+Wd1vyCLlIURGKVB3E2UN/sW9qrF46b1xolZdOTwhgXSbpUBDUjRBBHhAetSJ2GZXOxDtQONoStzGPT1MqkKQ9Z2P6ieLhKIz5Ajn/to5nY5eGvkxYFBZDnz8yiiOV/pfwM3dj8Kxigko8X7xhaGIHfH3nTp27QNaXvcwlNsQKBgQC7OdzXuKjHEFrSmbOTHDiJMSLmauuu38fxnIvss44nOqrnjg+BQEcS6lu1xegZD2wkIoO9RoceB7SBDl5nANjVs29vOGZ2z3KclNwIT584iPzXnLGriiOQnk+fOwyKkir//pYmBNh7w2vDc2d6uJWQ7WMsYJYsA6z/Ow0hKyjwzQKBgQC26/jLEEf9MeHbjChK+lpVM4UTLlsh1HdnAuWw5e6ejHWuFjGBNbXFPLAhNEQMn5IICeGHnUsDXCePrIl0+u2CiQmI42LF5LLlQRYj34qxfUuXzgSH4OqwkNpze583JRVZKaS+j09LnkfjdOFW/kfJlqsZ2F7ZTArxYn5uUycmxQKBgH7F91u6lc30rQrdOFLsUPvUYaVT0LJgpKpdLgHgsuitOPwBnU4PkBCmz+KNV1NgtgDhvgJztxoGbZrVd+aXFDvffBz7O6ZHYXvU8AhQoadOWqZEW6mjp+FM0O0otp09zA/6+wBTV6K1+Eon4nGeeuffVb++xvCWrJVGDuXeq6IhAoGABayiRiJWKVuPxxC8+z5y6Ou1OBgbCZyeq5piZ69qTI7M476agIia/+hXCmClr8KPdCJiVaOifRMxwvo2NWPHVGJN4mozWTIz/nqbZjd8BO9/FdF1QIXQofOMY0K1eK90zxREoQivDAh38G1ixCEVNwKughm9L351ZHGKvH9l2oUCgYBhaKJqUOZ38JAtiZRkCiwkDoHpPdHOOcg9j6EPS7Xa6BYZT3QZfn/0SJi7NRKdRwl23+FakaALrsi9cjnjH6eEg63QN+45UXjFe2/PHpUMLta+8K5SQY4y5XmTCPYPzzpGIqL2/x42DZ996GKQb1F557icf7sRxH0u2Nw7h2MIkg==";
    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhce3E6gJ3m2xJHCwzxEtW0raFdP3awiUsop6T0RVT4nL6nggFWvDjecF6+/ioqN94B7WZxZFFTgVrrTnyLABdC29QFxUN8XQKh4y7Q1oe6syHPtrbaLKZYB1bwG3n1CBdXTfhenn1R8rAYp8Vcu0JEHmvy/EWIkEL+ykhf9y8InhzbXYN1i5xHQL/oAQrxyFXPr914fmHWOYCJs0e3zXgRpMPSav3LbOpotm+r/WepHQk7lUNsW7o1YCxZDGbzZc8b5C2tWMRTrsdGtRr5e5lETqcYARMIzarwWtYfZksPF0yZKgPASYC8XtGw9DJAv9HrDUovWK1SHLBj5TcHq7wQIDAQAB";
    //沙箱接口路径
    public static final String ALIPAY_GATEWAY = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    //签名方式
    public static final String SIGN_TYPE = "RSA2";
    //接口回调地址
    public static final String CALLBACK_URL = "http://localhost:8081/order/alipaycallback";
}
