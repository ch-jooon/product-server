package musinsa.product.infra.jpa.support

import musinsa.product.core.fixture.ProductFixture
import musinsa.product.infra.jpa.entity.BrandEntity
import musinsa.product.infra.jpa.entity.CategoryEntity
import musinsa.product.infra.jpa.entity.ProductEntity
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

fun TestEntityManager.saveAllFixture() {
    ProductFixture.products.forEach { product ->
        runCatching {
            persist(ProductEntity.from(product))
        }.getOrElse {
            persist(ProductEntity.from(product.copy(id = 0)))
        }
    }

    ProductFixture.categories.forEach { category ->
        runCatching {
            persist(CategoryEntity.from(category))
        }.getOrElse {
            persist(CategoryEntity.from(category.copy(id = 0)))
        }
    }

    ProductFixture.brands.forEach { brand ->
        runCatching {
            persist(BrandEntity.from(brand))
        }.getOrElse {
            persist(BrandEntity.from(brand.copy(id = 0)))
        }
    }

    flush()
}

/**
 * 테스트 환경에서 사용하는 데이터베이스의 시퀀스를 초기화합니다.
 */
fun TestEntityManager.resetFixtureId() {
    entityManager.createNativeQuery("ALTER TABLE product ALTER COLUMN id RESTART WITH 1").executeUpdate()
    entityManager.createNativeQuery("ALTER TABLE brand ALTER COLUMN id RESTART WITH 1").executeUpdate()
    entityManager.createNativeQuery("ALTER TABLE category ALTER COLUMN id RESTART WITH 1").executeUpdate()
}