package uz.ji.url;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlDomain {

    private Long id;
    private String originalUrl;
    private String shortenedUrl;
    private String description;
    private Timestamp createdAt;
    private Timestamp validTill;

    // during building, value of createdBy = -1
    @Builder .Default
    private Long createdBy = -1L;


}
