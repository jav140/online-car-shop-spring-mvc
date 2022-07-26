package uz.ji.url.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlCreateDto {
    private String originalUrl;
    private String description;
    private String validTill;
}
