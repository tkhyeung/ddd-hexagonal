package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return all products."
    request {
        method(GET())
        urlPath("/products")
        headers {
            contentType(applicationJson())
            accept(applicationJson())
        }
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                "products": [
                        [
                                "id"         : 9527,
                                "productId"  : 'myUniqueId123',
                                "description": "description123",
                                "price"      : 999
                        ],
                        [
                                "id"         : 9528,
                                "productId"  : 'id2',
                                "description": "desc2",
                                "price"      : 1000
                        ],
                        [
                                "id"         : 9529,
                                "productId"  : 'id3',
                                "description": "d3",
                                "price"      : 1001
                        ]
                ]
        )

    }
}
