package musinsa.product.core.domain.catalog


interface CatalogFinder<T : Catalog> {
    val supportType: CatalogType
    fun findCatalog(query: CatalogQuery): T
}