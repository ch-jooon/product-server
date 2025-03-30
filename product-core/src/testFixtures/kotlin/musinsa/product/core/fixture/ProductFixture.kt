package musinsa.product.core.fixture

import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Category
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.Product

object ProductFixture {
    // Brand
    val 브랜드A = Brand(id = 1, name = "A")
    val 브랜드B = Brand(id = 2, name = "B")
    val 브랜드C = Brand(id = 3, name = "C")
    val 브랜드D = Brand(id = 4, name = "D")
    val 브랜드E = Brand(id = 5, name = "E")
    val 브랜드F = Brand(id = 6, name = "F")
    val 브랜드G = Brand(id = 7, name = "G")
    val 브랜드H = Brand(id = 8, name = "H")
    val 브랜드I = Brand(id = 9, name = "I")

    val brands = listOf(
        브랜드A, 브랜드B, 브랜드C,
        브랜드D, 브랜드E, 브랜드F,
        브랜드G, 브랜드H, 브랜드I
    )

    // Category
    val 상의 = Category(id = 1, name = "상의")
    val 아우터 = Category(id = 2, name = "아우터")
    val 바지 = Category(id = 3, name = "바지")
    val 스니커즈 = Category(id = 4, name = "스니커즈")
    val 가방 = Category(id = 5, name = "가방")
    val 모자 = Category(id = 6, name = "모자")
    val 양말 = Category(id = 7, name = "양말")
    val 액세서리 = Category(id = 8, name = "액세서리")

    val categories = listOf(
        상의, 아우터, 바지, 스니커즈,
        가방, 모자, 양말, 액세서리
    )

    // Brand A 제품
    val A_상의 = Product(id = 1, name = "A 상의", price = Price(11200), brand = 브랜드A, category = 상의)
    val A_아우터 = Product(id = 2, name = "A 아우터", price = Price(5500), brand = 브랜드A, category = 아우터)
    val A_바지 = Product(id = 3, name = "A 바지", price = Price(4200), brand = 브랜드A, category = 바지)
    val A_스니커즈 = Product(id = 4, name = "A 스니커즈", price = Price(9000), brand = 브랜드A, category = 스니커즈)
    val A_가방 = Product(id = 5, name = "A 가방", price = Price(2000), brand = 브랜드A, category = 가방)
    val A_모자 = Product(id = 6, name = "A 모자", price = Price(1700), brand = 브랜드A, category = 모자)
    val A_양말 = Product(id = 7, name = "A 양말", price = Price(1800), brand = 브랜드A, category = 양말)
    val A_액세서리 = Product(id = 8, name = "A 액세서리", price = Price(2300), brand = 브랜드A, category = 액세서리)

    // Brand B 제품
    val B_상의 = Product(id = 9, name = "B 상의", price = Price(10500), brand = 브랜드B, category = 상의)
    val B_아우터 = Product(id = 10, name = "B 아우터", price = Price(5900), brand = 브랜드B, category = 아우터)
    val B_바지 = Product(id = 11, name = "B 바지", price = Price(3800), brand = 브랜드B, category = 바지)
    val B_스니커즈 = Product(id = 12, name = "B 스니커즈", price = Price(9100), brand = 브랜드B, category = 스니커즈)
    val B_가방 = Product(id = 13, name = "B 가방", price = Price(2100), brand = 브랜드B, category = 가방)
    val B_모자 = Product(id = 14, name = "B 모자", price = Price(2000), brand = 브랜드B, category = 모자)
    val B_양말 = Product(id = 15, name = "B 양말", price = Price(2000), brand = 브랜드B, category = 양말)
    val B_액세서리 = Product(id = 16, name = "B 액세서리", price = Price(2200), brand = 브랜드B, category = 액세서리)

    // Brand C 제품
    val C_상의 = Product(id = 17, name = "C 상의", price = Price(10000), brand = 브랜드C, category = 상의)
    val C_아우터 = Product(id = 18, name = "C 아우터", price = Price(6200), brand = 브랜드C, category = 아우터)
    val C_바지 = Product(id = 19, name = "C 바지", price = Price(3300), brand = 브랜드C, category = 바지)
    val C_스니커즈 = Product(id = 20, name = "C 스니커즈", price = Price(9200), brand = 브랜드C, category = 스니커즈)
    val C_가방 = Product(id = 21, name = "C 가방", price = Price(2200), brand = 브랜드C, category = 가방)
    val C_모자 = Product(id = 22, name = "C 모자", price = Price(1900), brand = 브랜드C, category = 모자)
    val C_양말 = Product(id = 23, name = "C 양말", price = Price(2200), brand = 브랜드C, category = 양말)
    val C_액세서리 = Product(id = 24, name = "C 액세서리", price = Price(2100), brand = 브랜드C, category = 액세서리)

    // Brand D 제품
    val D_상의 = Product(id = 25, name = "D 상의", price = Price(10100), brand = 브랜드D, category = 상의)
    val D_아우터 = Product(id = 26, name = "D 아우터", price = Price(5100), brand = 브랜드D, category = 아우터)
    val D_바지 = Product(id = 27, name = "D 바지", price = Price(3000), brand = 브랜드D, category = 바지)
    val D_스니커즈 = Product(id = 28, name = "D 스니커즈", price = Price(9500), brand = 브랜드D, category = 스니커즈)
    val D_가방 = Product(id = 29, name = "D 가방", price = Price(2500), brand = 브랜드D, category = 가방)
    val D_모자 = Product(id = 30, name = "D 모자", price = Price(1500), brand = 브랜드D, category = 모자)
    val D_양말 = Product(id = 31, name = "D 양말", price = Price(2400), brand = 브랜드D, category = 양말)
    val D_액세서리 = Product(id = 32, name = "D 액세서리", price = Price(2000), brand = 브랜드D, category = 액세서리)

    // Brand E 제품
    val E_상의 = Product(id = 33, name = "E 상의", price = Price(10700), brand = 브랜드E, category = 상의)
    val E_아우터 = Product(id = 34, name = "E 아우터", price = Price(5000), brand = 브랜드E, category = 아우터)
    val E_바지 = Product(id = 35, name = "E 바지", price = Price(3800), brand = 브랜드E, category = 바지)
    val E_스니커즈 = Product(id = 36, name = "E 스니커즈", price = Price(9900), brand = 브랜드E, category = 스니커즈)
    val E_가방 = Product(id = 37, name = "E 가방", price = Price(2300), brand = 브랜드E, category = 가방)
    val E_모자 = Product(id = 38, name = "E 모자", price = Price(1800), brand = 브랜드E, category = 모자)
    val E_양말 = Product(id = 39, name = "E 양말", price = Price(2100), brand = 브랜드E, category = 양말)
    val E_액세서리 = Product(id = 40, name = "E 액세서리", price = Price(2100), brand = 브랜드E, category = 액세서리)

    // Brand F 제품
    val F_상의 = Product(id = 41, name = "F 상의", price = Price(11200), brand = 브랜드F, category = 상의)
    val F_아우터 = Product(id = 42, name = "F 아우터", price = Price(7200), brand = 브랜드F, category = 아우터)
    val F_바지 = Product(id = 43, name = "F 바지", price = Price(4000), brand = 브랜드F, category = 바지)
    val F_스니커즈 = Product(id = 44, name = "F 스니커즈", price = Price(9300), brand = 브랜드F, category = 스니커즈)
    val F_가방 = Product(id = 45, name = "F 가방", price = Price(2100), brand = 브랜드F, category = 가방)
    val F_모자 = Product(id = 46, name = "F 모자", price = Price(1600), brand = 브랜드F, category = 모자)
    val F_양말 = Product(id = 47, name = "F 양말", price = Price(2300), brand = 브랜드F, category = 양말)
    val F_액세서리 = Product(id = 48, name = "F 액세서리", price = Price(1900), brand = 브랜드F, category = 액세서리)

    // Brand G 제품
    val G_상의 = Product(id = 49, name = "G 상의", price = Price(10500), brand = 브랜드G, category = 상의)
    val G_아우터 = Product(id = 50, name = "G 아우터", price = Price(5800), brand = 브랜드G, category = 아우터)
    val G_바지 = Product(id = 51, name = "G 바지", price = Price(3900), brand = 브랜드G, category = 바지)
    val G_스니커즈 = Product(id = 52, name = "G 스니커즈", price = Price(9000), brand = 브랜드G, category = 스니커즈)
    val G_가방 = Product(id = 53, name = "G 가방", price = Price(2200), brand = 브랜드G, category = 가방)
    val G_모자 = Product(id = 54, name = "G 모자", price = Price(1700), brand = 브랜드G, category = 모자)
    val G_양말 = Product(id = 55, name = "G 양말", price = Price(2100), brand = 브랜드G, category = 양말)
    val G_액세서리 = Product(id = 56, name = "G 액세서리", price = Price(2000), brand = 브랜드G, category = 액세서리)

    // Brand H 제품
    val H_상의 = Product(id = 57, name = "H 상의", price = Price(10800), brand = 브랜드H, category = 상의)
    val H_아우터 = Product(id = 58, name = "H 아우터", price = Price(6300), brand = 브랜드H, category = 아우터)
    val H_바지 = Product(id = 59, name = "H 바지", price = Price(3100), brand = 브랜드H, category = 바지)
    val H_스니커즈 = Product(id = 60, name = "H 스니커즈", price = Price(9700), brand = 브랜드H, category = 스니커즈)
    val H_가방 = Product(id = 61, name = "H 가방", price = Price(2100), brand = 브랜드H, category = 가방)
    val H_모자 = Product(id = 62, name = "H 모자", price = Price(1600), brand = 브랜드H, category = 모자)
    val H_양말 = Product(id = 63, name = "H 양말", price = Price(2000), brand = 브랜드H, category = 양말)
    val H_액세서리 = Product(id = 64, name = "H 액세서리", price = Price(2000), brand = 브랜드H, category = 액세서리)

    // Brand I 제품
    val I_상의 = Product(id = 65, name = "I 상의", price = Price(11400), brand = 브랜드I, category = 상의)
    val I_아우터 = Product(id = 66, name = "I 아우터", price = Price(6700), brand = 브랜드I, category = 아우터)
    val I_바지 = Product(id = 67, name = "I 바지", price = Price(3200), brand = 브랜드I, category = 바지)
    val I_스니커즈 = Product(id = 68, name = "I 스니커즈", price = Price(9500), brand = 브랜드I, category = 스니커즈)
    val I_가방 = Product(id = 69, name = "I 가방", price = Price(2400), brand = 브랜드I, category = 가방)
    val I_모자 = Product(id = 70, name = "I 모자", price = Price(1700), brand = 브랜드I, category = 모자)
    val I_양말 = Product(id = 71, name = "I 양말", price = Price(1700), brand = 브랜드I, category = 양말)
    val I_액세서리 = Product(id = 72, name = "I 액세서리", price = Price(2400), brand = 브랜드I, category = 액세서리)

    // Product 리스트
    val products = listOf(
        // Brand A 제품
        A_상의, A_아우터, A_바지, A_스니커즈,
        A_가방, A_모자, A_양말, A_액세서리,

        // Brand B 제품
        B_상의, B_아우터, B_바지, B_스니커즈,
        B_가방, B_모자, B_양말, B_액세서리,

        // Brand C 제품
        C_상의, C_아우터, C_바지, C_스니커즈,
        C_가방, C_모자, C_양말, C_액세서리,

        // Brand D 제품
        D_상의, D_아우터, D_바지, D_스니커즈,
        D_가방, D_모자, D_양말, D_액세서리,

        // Brand E 제품
        E_상의, E_아우터, E_바지, E_스니커즈,
        E_가방, E_모자, E_양말, E_액세서리,

        // Brand F 제품
        F_상의, F_아우터, F_바지, F_스니커즈,
        F_가방, F_모자, F_양말, F_액세서리,

        // Brand G 제품
        G_상의, G_아우터, G_바지, G_스니커즈,
        G_가방, G_모자, G_양말, G_액세서리,

        // Brand H 제품
        H_상의, H_아우터, H_바지, H_스니커즈,
        H_가방, H_모자, H_양말, H_액세서리,

        // Brand I 제품
        I_상의, I_아우터, I_바지, I_스니커즈,
        I_가방, I_모자, I_양말, I_액세서리
    )
}