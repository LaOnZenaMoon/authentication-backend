package me.lozm.global.code;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SearchType {

    NONE("NONE", "NONE"),
    IDENTIFIER("IDENTIFIER", "사용자 로그인 ID"),

    ;

    private String code;
    private String description;

    SearchType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static SearchType findCode(String code) {
        return Arrays.stream(SearchType.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

}
