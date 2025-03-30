package musinsa.product.infra.jpa.support

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["musinsa.product.infra.jpa"])
@EntityScan(basePackages = ["musinsa.product.infra.jpa"])
class TestJpaConfig