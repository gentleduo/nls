package org.duo.nls.business.req;

import lombok.Data;

@Data
public class ProductInfoQueryReq extends PageReq {

    private String name;
}
