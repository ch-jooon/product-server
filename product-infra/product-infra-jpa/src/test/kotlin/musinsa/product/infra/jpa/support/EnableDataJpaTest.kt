package musinsa.product.infra.jpa.support

import com.linecorp.kotlinjdsl.support.spring.data.jpa.autoconfigure.KotlinJdslAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@DataJpaTest
@ContextConfiguration(classes = [TestJpaConfig::class])
@Import(KotlinJdslAutoConfiguration::class)
@ActiveProfiles("jpa-test")
annotation class EnableDataJpaTest
