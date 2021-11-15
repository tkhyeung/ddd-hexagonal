package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return one product."
    request {
        method(GET())
        urlPath("/products/9530")
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
            "id": 9530,
            "productId": '9530',
            "description": "d9530",
            "price": 9530

        )


    }
}
