package com.sot.thing.stack;

import java.math.BigDecimal;

/**
 * Created by kevin on 10/3/2015.
 */
public class FillQty {

    private BigDecimal qtyNumeric;
    private String qtyString;

    public FillQty(BigDecimal qtyNumeric, String qtyString) {
        this.qtyNumeric = qtyNumeric;
        this.qtyString = qtyString;
    }

    public FillQty() {
    }

    public String getQtyString() {
        return qtyString;
    }

    public void setQtyString(String qtyString) {
        this.qtyString = qtyString;
    }

    public BigDecimal getQtyNumeric() {

        return qtyNumeric;
    }

    public void setQtyNumeric(BigDecimal qtyNumeric) {
        this.qtyNumeric = qtyNumeric;
    }
}
