package kr.hs.dsm.devlib.global.feign

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "feign")
class FeignProperties(
    val secretKey: String,
    val accessKey: String
)