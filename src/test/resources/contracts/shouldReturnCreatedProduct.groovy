package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return created product."
    request {
        method(POST())
        urlPath("/products")
        headers {
            contentType(applicationJson())
            accept(applicationJson())
        }
        body(
                productId: 'myUniqueId123',
                description: 'description123',
                price: 999
        )
    }
    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
                "id": anyAlphaNumeric(),
                "productId": 'myUniqueId123',
                "description": "description123",
                "price": 999
        )
    }
}
