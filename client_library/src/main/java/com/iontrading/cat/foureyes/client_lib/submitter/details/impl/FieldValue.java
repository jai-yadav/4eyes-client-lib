package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

public abstract class FieldValue {

    private final ValueRank rank;
    private boolean highlight;

    protected FieldValue(final ValueRank r) {
        this.rank = r;
    }

    public ValueRank getRank() {
        return rank;
    }

    public void highlight() {
        highlight = true;
    }

    public boolean isHighlight() {
        return highlight;
    }
}
