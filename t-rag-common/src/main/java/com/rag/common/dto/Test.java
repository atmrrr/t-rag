package com.rag.common.dto;

import lombok.Data;

@Data
public class Test {

    private String name;

    public void test(){
        String name1 = this.getName();
    }

}
