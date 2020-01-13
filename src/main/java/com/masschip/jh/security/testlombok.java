package com.masschip.jh.security;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class testlombok {
   @NonNull
   private int id;
    private String name;
    private Date inhere;
}
