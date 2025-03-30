package musinsa.product.infra.jpa.entity

import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.querymodel.jpql.entity.Entities
import com.linecorp.kotlinjdsl.querymodel.jpql.expression.Subquery
import musinsa.product.core.domain.Product
import musinsa.product.core.domain.ProductRepository
import musinsa.product.core.domain.exception.ProductException
import org.springframework.stereotype.Component

@Component
class ProductJpaAdapter(
    private val jpaRepository: ProductJpaRepository,
    private val categoryJpaRepository: CategoryJpaRepository,
) : ProductRepository {

    override fun save(product: Product): Product {
        val entity = ProductEntity.from(product)
        return jpaRepository.save(entity)
            .toDomain()
    }

    override fun saveAll(products: List<Product>): List<Product> {
        val entities = products.map { ProductEntity.from(it) }
        return jpaRepository.saveAll(entities)
            .map { it.toDomain() }
    }

    override fun findById(id: Long): Product {
        return jpaRepository.findAll(limit = 1) {
            selectProjection().from(
                entity(ProductEntity::class),
                leftJoin(BrandEntity::class)
                    .on(path(ProductEntity::brandId).eq(path(BrandEntity::id))),
                leftJoin(CategoryEntity::class)
                    .on(path(ProductEntity::categoryId).eq(path(CategoryEntity::id))),
            ).whereAnd(
                path(ProductEntity::id).eq(id),
                path(ProductEntity::deletedAt).isNull(),
            )
        }.firstOrNull()
            ?.toDomain()
            ?: throw ProductException.NotFound()
    }

    override fun findAllByBrandId(brandId: Long): List<Product> {
        return jpaRepository.findAllByBrandId(brandId)
            .map { it.toDomain() }
    }

    /**
     * 카테고리 별 최소 가격 상품 목록을 조회합니다.
     */
    override fun findMinPriceByCategory(): List<Product> {
        return jpaRepository.findAll {
            selectProjection().from(
                entity(ProductEntity::class),
                join(BrandEntity::class)
                    .on(path(ProductEntity::brandId).eq(path(BrandEntity::id))),
                join(CategoryEntity::class)
                    .on(path(ProductEntity::categoryId).eq(path(CategoryEntity::id))),
            ).whereAnd(
                path(ProductEntity::price).eq(minPriceByCategoryIdSubQuery()),
                path(ProductEntity::deletedAt).isNull(),
            )
        }.mapNotNull { it?.toDomain() }
    }

    /**
     * 전체 금액이 가장 저렴한 단일 브랜드 상품 목록을 조회합니다.
     *
     * 쿼리 자체를 단순화하기 위해 개별 쿼리로 나누어 실행합니다.
     * 1. 전체 카테고리 수를 조회 (query)
     * 2. 브랜드, 카테고리 별 최저 가격을 조회 (query)
     * 2-1. 전체 카테고리에 상품이 존재하는 가장 저렴한 브랜드 아이디를 추출 (memory)
     * 3. 해당 브랜드 아이디로 상품 목록을 조회 (query)
     */
    override fun findMinTotalPriceBySingleBrand(): List<Product> {
        // 전체 카테고리 수 조회
        val totalCategoryCount = categoryJpaRepository.count()

        // 브랜드, 카테고리 별 최저 가격 조회
        val minPriceBrandProjection = jpaRepository.findAll {
            selectNew<BrandCategoryMinPriceProjection>(
                path(ProductEntity::brandId),
                path(ProductEntity::categoryId),
                min(path(ProductEntity::price)),
            ).from(
                entity(ProductEntity::class),
            ).groupBy(
                path(ProductEntity::brandId),
                path(ProductEntity::categoryId),
            )
        }
        if (minPriceBrandProjection.isEmpty()) {
            return emptyList()
        }
        // 전체 카테고리에 상품이 존재하는 가장 저렴한 브랜드 아이디 추출
        val lowestPriceBrandId = minPriceBrandProjection.toLowestTotalPriceBrandId(totalCategoryCount)
            ?: return emptyList()

        return jpaRepository.findAll {
            selectProjection().from(
                entity(ProductEntity::class),
                join(BrandEntity::class)
                    .on(path(ProductEntity::brandId).eq(path(BrandEntity::id))),
                join(CategoryEntity::class)
                    .on(path(ProductEntity::categoryId).eq(path(CategoryEntity::id))),
            ).whereAnd(
                path(ProductEntity::brandId).eq(value(lowestPriceBrandId)),
                path(ProductEntity::deletedAt).isNull(),
            )
        }.mapNotNull { it?.toDomain() }
    }


    /**
     * 카테고리 이름으로 최저 가격 상품 목록을 조회합니다.
     */
    override fun findMinPriceByCategoryName(categoryName: String): List<Product> {
        return jpaRepository.findAll {
            selectProjection().from(
                entity(ProductEntity::class),
                join(BrandEntity::class)
                    .on(path(ProductEntity::brandId).eq(path(BrandEntity::id))),
                join(CategoryEntity::class)
                    .on(path(ProductEntity::categoryId).eq(path(CategoryEntity::id))),
            ).whereAnd(
                path(CategoryEntity::name).eq(categoryName),
                path(ProductEntity::price).eq(minPriceByCategoryIdSubQuery()),
                path(ProductEntity::deletedAt).isNull(),
            )
        }.mapNotNull { it?.toDomain() }
    }

    /**
     * 카테고리 이름으로 최고 가격 상품 목록을 조회합니다.
     */
    override fun findMaxPriceByCategoryName(categoryName: String): List<Product> {
        return jpaRepository.findAll {
            selectProjection().from(
                entity(ProductEntity::class),
                join(BrandEntity::class)
                    .on(path(ProductEntity::brandId).eq(path(BrandEntity::id))),
                join(CategoryEntity::class)
                    .on(path(ProductEntity::categoryId).eq(path(CategoryEntity::id))),
            ).whereAnd(
                path(CategoryEntity::name).eq(categoryName),
                path(ProductEntity::price).eq(maxPriceByCategoryIdSubQuery()),
                path(ProductEntity::deletedAt).isNull(),
            )
        }.mapNotNull { it?.toDomain() }
    }

    /**
     * 카테고리 별 최고 가격 서브 쿼리
     */
    private fun Jpql.maxPriceByCategoryIdSubQuery(): Subquery<Long> {
        val subqueryEntity = Entities.entity(ProductEntity::class, alias = "max")
        return select(
            max(subqueryEntity(ProductEntity::price))
        ).from(
            subqueryEntity,
        ).whereAnd(
            subqueryEntity(ProductEntity::categoryId).eq(path(ProductEntity::categoryId)),
            subqueryEntity(ProductEntity::deletedAt).isNull(),
        ).asSubquery()
    }

    /**
     * 카테고리 별 최저 가격 서브 쿼리
     */
    private fun Jpql.minPriceByCategoryIdSubQuery(): Subquery<Long> {
        val subqueryEntity = Entities.entity(ProductEntity::class, alias = "min")
        return select(
            min(subqueryEntity(ProductEntity::price))
        ).from(
            subqueryEntity,
        ).whereAnd(
            subqueryEntity(ProductEntity::categoryId).eq(path(ProductEntity::categoryId)),
            subqueryEntity(ProductEntity::deletedAt).isNull(),
        ).asSubquery()
    }

    private fun Jpql.selectProjection() = selectNew<ProductProjection>(
        path(ProductEntity::id),
        path(ProductEntity::name),
        path(ProductEntity::price),
        path(ProductEntity::createdAt),
        path(ProductEntity::brandId),
        path(BrandEntity::name),
        path(BrandEntity::createdAt),
        path(ProductEntity::categoryId),
        path(CategoryEntity::name),
        path(CategoryEntity::createdAt),
    )
}