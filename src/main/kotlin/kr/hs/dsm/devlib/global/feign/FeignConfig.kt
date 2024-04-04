package kr.hs.dsm.devlib.global.feign

import feign.Logger
import feign.RequestInterceptor
import feign.RequestTemplate
import feign.Response
import feign.codec.ErrorDecoder
import kr.hs.dsm.devlib.global.error.InternalServerError
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@EnableFeignClients(basePackages = ["kr.hs.dsm.devlib"])
@Configuration
@Import(FeignClientErrorDecoder::class)
class FeignConfig(
    val feignProperties: FeignProperties
) {

    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("X-Naver-Client-Id", feignProperties.accessKey)
            requestTemplate.header("X-Naver-Client-Secret", feignProperties.secretKey)
        }
    }

    @Bean
    @ConditionalOnMissingBean(value = [ErrorDecoder::class])
    fun commonFeignErrorDecoder(): FeignClientErrorDecoder = FeignClientErrorDecoder()
}

class FeignClientErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            println(response.status())
            println(response.body())
            throw InternalServerError
        }
        return feign.FeignException.errorStatus(methodKey, response)
    }
}